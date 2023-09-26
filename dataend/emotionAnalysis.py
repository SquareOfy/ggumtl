# -*- coding: utf-8 -*-

import asyncio
import json
from google.cloud import language_v2
from google.oauth2 import service_account

# 구글 키 설정
key_path = ".\\googleKey.json"

# 인증 설정
credentials = service_account.Credentials.from_service_account_file(
    key_path, scopes=['https://www.googleapis.com/auth/cloud-platform']
)

# 감정 분석할 텍스트
dream = "비둘기가 방에 들어옵니다."

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

# 비동기 함수 실행
# if __name__ == "__main__":
#     asyncio.run(analyze_text_sentiment(dream))

input_path = ".\\addKeywordsMod\\addKeywordsMod2.json"
output_path = ".\\addEmotion\\addEmotion2.json"
with open(input_path, 'r', encoding='utf-8') as file:
    keyData = json.load(file)

print(len(keyData))

# exit()

idx = 0
for data in keyData:
    idx+=1
    # if idx<5600: continue
    dreamOriginScore = asyncio.run(analyze_text_sentiment(data["dream"]))
    tellingOriginScore = asyncio.run(analyze_text_sentiment(data["analysis"]["dreamTelling"]))

    dreamPositivePoint, dreamNegativePoint = estimateScore(dreamOriginScore)
    dreamTellingPositivePoint, dreamTellingNegativePoint = estimateScore(tellingOriginScore)

    data["analysis"]["dreamPositivePoint"] = dreamPositivePoint
    data["analysis"]["dreamNegativePoint"] = dreamNegativePoint
    data["analysis"]["dreamTellingPositivePoint"] = dreamTellingPositivePoint
    data["analysis"]["dreamTellingNegativePoint"] = dreamTellingNegativePoint

    if idx % 100 == 0:
        with open(output_path, 'w', encoding='utf-8') as out:
            json.dump(keyData[:idx], fp=out, ensure_ascii=False, indent=2)
            print(idx, "번째까지 완료")
    print(idx, "#####")

with open(output_path, 'w', encoding='utf-8') as out:
    json.dump(keyData, fp=out, ensure_ascii=False, indent=2)
    print(idx, "번째까지 완료")

# exit()


exit()
