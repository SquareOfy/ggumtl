package dream.card.service;

import dream.card.domain.Grade;
import dream.card.dto.request.RequestDreamCardDetail;
import dream.card.dto.response.ResponseDreamAnalysis;
import dream.mongo.domain.Dream;
import dream.mongo.repository.MongoRepository;
import dream.s3.dto.ProcessGrade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DreamAnalysisService {

    private final MongoRepository mongoRepository;
    private final MongoTemplate mongoTemplate;

    public List<Dream> findDreamsWithKeywords(List<String> keywords) {

        Criteria criteria = Criteria.where("analysis.keywords").in(keywords);

        Query query = new Query(criteria);
        return mongoTemplate.find(query, Dream.class);
    }

    public List<Dream> findDreamsByKeyword(String keyword) {

        String regexKeyword = ".*" + keyword + ".*";
        Criteria criteria = Criteria.where("dream").regex(regexKeyword, "i");

        Query query = new Query(criteria).limit(100);
        return mongoTemplate.find(query, Dream.class);
    }

    public ResponseDreamAnalysis processAnalysis(RequestDreamCardDetail requestDream) {

        for(int i = 0; i < requestDream.getWordKeywords().size(); i++){
            log.info(requestDream.getWordKeywords().get(i));
        }

        // 동적 쿼리 날려서 Mongo에서 해당 ROW 다 가져오기
        List<Dream> list = findDreamsWithKeywords(requestDream.getWordKeywords());
        if (list.isEmpty()) return null;
        log.info("findDreamsWithKeywordsListSize : {}", list.size());
        for (Dream dream : list) {
            log.info("찾은 꿈 내용 : " + dream.getDream());
            log.info("찾은 꿈 해 내용 : " + dream.getAnalysis().getDreamTelling());
        }

        Dream simillarDream = findBestSimillarDream(requestDream, list);

        if(simillarDream.getDream().equals("개꿈")){
            return null;
        }

        ResponseDreamAnalysis response = new ResponseDreamAnalysis();
        response.setDreamTelling(simillarDream.getDream());

        // 데이터가 들어왔을 때, 50000이라는 분모를 조정해 주면서 최적의 분모 값을 찾는 과정이 필요해
        int rarePoint = 30;


        ProcessGrade gradeSet = setGrade(simillarDream.getAnalysis().getDreamTellingPositivePoint(),
                rarePoint);
        response.setPositiveGrade(gradeSet.getPositiveDreamGrade());
        response.setRareGrade(gradeSet.getRareDreamGrade());
        response.setGrade(gradeSet.getFinalGrade());
        response.setRarePoint(rarePoint);


        return response;
    }

    private ProcessGrade setGrade(int dreamTellingPositivePoint, int rarePoint) {
        Grade positiveGrade = Grade.A;
        Grade rareGrade = Grade.A;
        Grade totalGrade = Grade.A;

        if(dreamTellingPositivePoint > 90) positiveGrade = Grade.SS;
        else if(dreamTellingPositivePoint > 80) positiveGrade = Grade.S;
        else if(dreamTellingPositivePoint > 70) positiveGrade = Grade.A;
        else if(dreamTellingPositivePoint > 60) positiveGrade = Grade.B;
        else if(dreamTellingPositivePoint > 50) positiveGrade = Grade.C;
        else positiveGrade = Grade.F;

        if(rarePoint > 90) rareGrade = Grade.SS;
        else if(rarePoint > 80) rareGrade = Grade.S;
        else if(rarePoint > 70) rareGrade = Grade.A;
        else if(rarePoint > 60) rareGrade = Grade.B;
        else if(rarePoint > 50) rareGrade = Grade.C;
        else rareGrade = Grade.F;

        int totalSumDiv2 = (dreamTellingPositivePoint + rarePoint) / 2;
        if(totalSumDiv2 > 90) totalGrade = Grade.SS;
        else if(totalSumDiv2 > 80) totalGrade = Grade.S;
        else if(totalSumDiv2 > 70) totalGrade = Grade.A;
        else if(totalSumDiv2 > 60) totalGrade = Grade.B;
        else if(totalSumDiv2 > 50) totalGrade = Grade.C;
        else totalGrade = Grade.F;

        ProcessGrade response = new ProcessGrade();
        response.setPositiveDreamGrade(positiveGrade);
        response.setRareDreamGrade(rareGrade);
        response.setFinalGrade(totalGrade);

        return response;
    }

    private Dream findBestSimillarDream(RequestDreamCardDetail requestDream, List<Dream> list) {

        double maxSimillarPoint = Integer.MIN_VALUE;
        int maxSimillarDreamIndex = -1;
        for(int i = 0; i < list.size(); i++){
            log.info("내 꿈 내용 : " + requestDream.getDreamCardContent());
            log.info("데이터 꿈 내용 : " + list.get(i).getAnalysis().getDreamTelling());
            double analysisPoint = analysis(requestDream, list.get(i));
            log.info("두 꿈의 최종 유사도 : " + analysisPoint);
            log.info("--------------------------------");
            if (maxSimillarPoint < analysisPoint) {
                maxSimillarPoint = analysisPoint;
                maxSimillarDreamIndex = i;
            }
        }

        if(maxSimillarDreamIndex == -1){
            return new Dream("개꿈");
        }

        Dream mostSimmilarDream = list.get(maxSimillarDreamIndex);
        return mostSimmilarDream;
    }

    public static double analysis(RequestDreamCardDetail requestDream, Dream dataDream) {

        double result = 0;
        double sentenceSimilarity = jaccardSimilarity(requestDream.getDreamCardContent(), dataDream.getDream()) * 10 * 5;
        log.info("문자열 유사도 : " + sentenceSimilarity * 2);
        log.info("가중치 반영된 문자열 유사도 : " + sentenceSimilarity);
        double posSimilarity = (double) (positiveSimilarity(requestDream.getPositivePoint(), dataDream.getAnalysis().getDreamPositivePoint()) * 2.5) / 10;
        log.info("긍정도 유사도 : " + posSimilarity * 4);
        log.info("가중치 반영된 긍정도 유사도 : " + posSimilarity);
        double negSimilarity = (double) (negativeSimilarity(requestDream.getNegativePoint(), dataDream.getAnalysis().getDreamNegativePoint()) * 2.5) / 10;
        log.info("가중치 반영된 부정도 유사도 : " + negSimilarity * 4);
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
