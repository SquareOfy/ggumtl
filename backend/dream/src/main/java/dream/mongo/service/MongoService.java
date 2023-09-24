package dream.mongo.service;

import dream.common.domain.ResultTemplate;
import dream.mongo.domain.DataDream;
import dream.mongo.domain.RequestDream;
import dream.mongo.domain.ResponseDream;
import dream.mongo.domain.dream;
import dream.mongo.repository.MongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class MongoService {

    private final MongoRepository mongoRepository;
    public List<dream> getAllDream() {

        List<dream> response = mongoRepository.findAll();
        return response;
    }

    public ResultTemplate findBest(String title) {

        RequestDream requestDream = new RequestDream("비둘기가 집에 들어가다.",
                70, 20);

        String regTitle = ".*" + title + ".*";
        List<dream> list = mongoRepository.findByDreamRegex(title);
        double max = Integer.MIN_VALUE;
        int idx = -1;
        for(int i = 0; i < list.size(); i++){
            log.info("내 꿈 내용 : " + requestDream.getSentence());
            log.info("데이터 꿈 내용 : " + list.get(i).getAnalysis().getDreamTelling());
            DataDream dataDream = new DataDream();
            dataDream.setSentence(list.get(i).getDream());
            dataDream.setPositivePoint(list.get(i).getAnalysis().getDreamPositivePoint());
            dataDream.setNegativePoint(list.get(i).getAnalysis().getDreamNegativePoint());
            double analysisPoint = analysis(requestDream, dataDream);
            log.info("두 꿈의 유사도 : " + analysisPoint);
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

        double sentenceSimilarity = jaccardSimilarity(requestDream.getSentence(), dataDream.getSentence()) * 10 * 5;
        log.info("문자열 유사도 : " + sentenceSimilarity);
        double posSimilarity = (double) (positiveSimilarity(requestDream.getPositivePoint(), dataDream.getPositivePoint()) * 2.5) / 10;
        log.info("긍정도 유사도 : " + posSimilarity);
        double negSimilarity = (double) (negativeSimilarity(requestDream.getNegativePoint(), dataDream.getNegativePoint()) * 2.5) / 10;
        log.info("부정도 유사도 : " + negSimilarity);

        result = sentenceSimilarity + posSimilarity + negSimilarity;
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
}