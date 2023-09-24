package dream.card.service;

import dream.card.domain.Grade;
import dream.card.dto.request.RequestDreamCardDetail;
import dream.card.dto.response.ResponseDreamAnalysis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DreamAnalysisService {

    public ResponseDreamAnalysis processAnalysis(RequestDreamCardDetail request) {
        ResponseDreamAnalysis response = new ResponseDreamAnalysis();

        // 긍정도, 부정도 수치를 토대로 긍정도 등급 매기기
//        response.setPositiveGrade(DreamAnalysisService.getPositiveGrade(request.getPositivePoint(), request.getNegativePoint()));
        return response;
    }

    private static Grade getPositiveGrade(int positivePoint) {
        return null;
    }
}
