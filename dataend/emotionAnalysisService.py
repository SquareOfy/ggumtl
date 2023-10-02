# -*- coding: utf-8 -*-

import asyncio
import json
from google.cloud import language_v2
from google.oauth2 import service_account

# 구글 키 설정
key_path = "./googleKey.json"

# 인증 설정
credentials = service_account.Credentials.from_service_account_file(
    key_path, scopes=['https://www.googleapis.com/auth/cloud-platform']
)

# 비동기 함수로 변환
async def analyze_text_sentiment(text):

    # NL API 클라이언트
    client = language_v2.LanguageServiceAsyncClient(credentials=credentials)

    # 문서 생성
    document = language_v2.Document(
        content=text, 
        type_=language_v2.Document.Type.PLAIN_TEXT,
        language_code="ko"
    )

    request = {"document": document, "encoding_type": language_v2.EncodingType.UTF8}

    # 감정 분석 요청
    response = await client.analyze_sentiment(request=request)

    # 감정 스코어
    sentiment = response.document_sentiment

    # 감정 점수 return하기
    return sentiment.score


# 긍정 점수, 부정 점수 만들기.
def estimateScore(score):
    positivePoint = 50 + round(score*50)
    negativePoint = 50 - round(score*50)

    return [positivePoint, negativePoint]

def getEmotionScore(text):
    originScore = asyncio.run(analyze_text_sentiment(text))
    positivePoint, negativePoint = estimateScore(originScore)
    return [positivePoint, negativePoint]
