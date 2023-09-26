package dream.mongo.service;

import dream.card.service.DreamAnalysisService;
import dream.common.domain.ResultTemplate;
import dream.mongo.domain.DataDream;
import dream.mongo.domain.RequestDream;
import dream.mongo.domain.ResponseDream;
import dream.mongo.domain.Dream;
import dream.mongo.repository.MongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MongoService {

    private final MongoRepository mongoRepository;
    private final DreamAnalysisService dreamAnalysisService;
    public List<Dream> getAllDream() {

        List<Dream> response = mongoRepository.findAll();
        return response;
    }

    public ResultTemplate saveDream(List<Dream> dreams){

        mongoRepository.saveAll(dreams);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    public ResultTemplate findBest(String title) {

        RequestDream requestDream = new RequestDream("비둘기가 방에 들어갑니다.",
                54, 23);

        String regTitle = ".*" + title + ".*";
        List<Dream> list = mongoRepository.findByDreamRegex(title);
        List<String> strList = new ArrayList<>();
        strList.add("비둘기");
        strList.add("방");
        strList.add("들어가다");
        List<Dream> list2 = dreamAnalysisService.findDreamsWithKeywords(strList);
        log.info("list size2 : {}", list2.size());
        double max = Integer.MIN_VALUE;
        int idx = -1;
        for(int i = 0; i < list.size(); i++){
            log.info("   입력 꿈 내용 : " + requestDream.getSentence());
            log.info("   비교 꿈 내용 : " + list.get(i).getDream());
            log.info("입력 꿈의 긍정도 : {}, 입력 꿈의 부정도 : {}"
                    , requestDream.getPositivePoint(), requestDream.getNegativePoint());
            log.info("비교 꿈의 긍정도 : {}, 비교 꿈의 부정도 : {}"
                    , list.get(i).getAnalysis().getDreamPositivePoint(), list.get(i).getAnalysis().getDreamNegativePoint());
            DataDream dataDream = new DataDream();
            dataDream.setSentence(list.get(i).getDream());
            dataDream.setPositivePoint(list.get(i).getAnalysis().getDreamPositivePoint());
            dataDream.setNegativePoint(list.get(i).getAnalysis().getDreamNegativePoint());
            double analysisPoint = analysis(requestDream, dataDream);
//            log.info("두 꿈의 유사도 : " + analysisPoint);
            log.info("--------------------------------");
            if (max < analysisPoint) {
                max = analysisPoint;
                idx = i;
            }
        }

        ResponseDream response = ResponseDream.from(requestDream.getSentence(), list.get(idx).getDream(),
                list.get(idx).getAnalysis().getDreamTelling(), list.get(idx).getAnalysis().getDreamTellingPositivePoint());

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public static double analysis(RequestDream requestDream, DataDream dataDream) {

        double result = 0;
//        double sentenceSimilarity1 = levenshtein(requestDream.getSentence(), dataDream.getSentence()) * 10 * 5;
//        double sentenceSimilarity2 = cosineSimilarity(requestDream.getSentence(), dataDream.getSentence()) * 10 * 5;
        double sentenceSimilarity3 = jaccardSimilarity(requestDream.getSentence(), dataDream.getSentence()) * 10 * 5;

//        log.info("Levenshtein : " + sentenceSimilarity1 * 2);
//        log.info("     Cosine : " + sentenceSimilarity2 * 2);
//        log.info("    Jaccard : " + sentenceSimilarity3 * 2);
        log.info("   문자열 유사도 : " + sentenceSimilarity3);
        double posSimilarity = (double) (positiveSimilarity(requestDream.getPositivePoint(), dataDream.getPositivePoint()) * 2.5) / 10;
        log.info("   긍정도 유사도 : " + posSimilarity);
        double negSimilarity = (double) (negativeSimilarity(requestDream.getNegativePoint(), dataDream.getNegativePoint()) * 2.5) / 10;
        log.info("   부정도 유사도 : " + negSimilarity);

        result = sentenceSimilarity3 + posSimilarity + negSimilarity;
        log.info("     최종 유사도 : " + result);
        return result;
    }

    private static int negativeSimilarity(int dreamNegativePoint, int dataNegativePoint) {

        int result = Math.abs(dreamNegativePoint - dataNegativePoint);
        return 100 - result;
    }

    private static int positiveSimilarity(int dreamPositivePoint, int dataPositivePoint) {

        int result = Math.abs(dreamPositivePoint - dataPositivePoint);
        return 100 - result;
    }

    public static double jaccardSimilarity(String s1, String s2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        // 문자열을 집합으로 변환
        for (char c : s1.toCharArray()) {
            set1.add(c);
        }

        for (char c : s2.toCharArray()) {
            set2.add(c);
        }

        // 교집합 크기 계산
        Set<Character> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        // 합집합 크기 계산
        Set<Character> union = new HashSet<>(set1);
        union.addAll(set2);

        // Jaccard 유사도 계산
        return (double) intersection.size() / union.size();
    }

    public static double cosineSimilarity(String s1, String s2) {
        Map<String, Integer> vector1 = buildTermFrequencyVector(s1);
        Map<String, Integer> vector2 = buildTermFrequencyVector(s2);

        // 벡터의 내적 계산
        double dotProduct = calculateDotProduct(vector1, vector2);

        // 벡터의 크기 계산
        double magnitude1 = calculateMagnitude(vector1);
        double magnitude2 = calculateMagnitude(vector2);

        // 코사인 유사도 계산
        return dotProduct / (magnitude1 * magnitude2);
    }

    private static Map<String, Integer> buildTermFrequencyVector(String text) {
        Map<String, Integer> vector = new HashMap<>();
        String[] terms = text.split(" ");

        for (String term : terms) {
            vector.put(term, vector.getOrDefault(term, 0) + 1);
        }

        return vector;
    }

    private static double calculateDotProduct(Map<String, Integer> vector1, Map<String, Integer> vector2) {
        double dotProduct = 0.0;

        for (String term : vector1.keySet()) {
            if (vector2.containsKey(term)) {
                dotProduct += vector1.get(term) * vector2.get(term);
            }
        }

        return dotProduct;
    }

    private static double calculateMagnitude(Map<String, Integer> vector) {
        double magnitude = 0.0;

        for (int value : vector.values()) {
            magnitude += Math.pow(value, 2);
        }

        return Math.sqrt(magnitude);
    }

    private static double levenshtein(String s1, String s2) {
        String longer = s1, shorter = s2;

        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }

        int longerLength = longer.length();
        if (longerLength == 0) return 1.0;
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    private static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int[] costs = new int[s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];

                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        }

                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }

            if (i > 0) costs[s2.length()] = lastValue;
        }

        return costs[s2.length()];
    }
}