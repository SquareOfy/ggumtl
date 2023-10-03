# main.py

from fastapi import FastAPI, UploadFile, File
from fastapi.middleware.cors import CORSMiddleware

from typing import List, IO
import httpx 
import asyncio # 비동기 통신
import requests
import json

from pydantic import BaseModel

# 내가 만든 함수
from wordExtractService import getDreamKeywords
from emotionAnalysisService import getEmotionScore
from getKarlo import getKarloImgPath

from tempfile import NamedTemporaryFile

# 꿈 데이터
class DreamModel(BaseModel):
    dreamCardContent: str
    dreamCardAuthor: float
    isShow: str

app = FastAPI()

headers = {
    "Content-Type": "application/json"
}
file_headers = {
    "Content-Type": "multipart/form-data"
}


# 모든 도메인 허용
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # 모든 도메인에서의 요청을 허용하려면 ["*"]로 설정
    allow_credentials=True,
    allow_methods=["*"],  # 모든 HTTP 메서드를 허용하려면 ["*"]로 설정
    allow_headers=["*"],  # 모든 헤더를 허용하려면 ["*"]로 설정
)

# 자바 URL
URL = "https://j9b301.p.ssafy.io/api"

# 홈
@app.get("/")
def root():
    return "Hello FastAPI"

# 테스트 용도
@app.get("/data")
def root():
    return "Hello FastAPI"

# exit()




@app.post("/data/night/dream/create")
def dreamProcessing(data: DreamModel):
    # 받은 데이터 처리
    print(data, "널 좀 알고 싶다...!")
    dreamCardContent = data.dreamCardContent
    dreamCardAuthor = data.dreamCardAuthor
    isShow = data.isShow
    wordKeywords = getDreamKeywords(dreamCardContent)
    positivePoint, negativePoint = getEmotionScore(dreamCardContent)
    
    # 임시로 넣음. 원래는 번역한 텍스트를 넣어야 해.
    prompt = "A cat with white fur, floating balloon, by Renoir"

    img_path = getKarloImgPath(prompt)
    print("files 앞", img_path)
    # files = {'file': ("karloImage.png", open(img_path, 'rb'), "image/png")} #

    # 여긴 내 가정. (파일 전용 경로 필요)
    # 내거 마지막

    # 이걸 대신하기.
    # print("files 뒤", files)

    toJavaData = {
        "dreamCardDetail": {        
            "dreamCardContent": dreamCardContent,
            "dreamCardAuthor": dreamCardAuthor,
            "isShow": isShow,
            "positivePoint": positivePoint,
            "negativePoint": negativePoint,
            "keywords": ["학업", "재물"],
            "wordKeywords": wordKeywords
        }
    }
    print(toJavaData, "자바로 갈 데이터")
    response = requests.post('https://j9b301.p.ssafy.io/api/s3/dream/new', data=toJavaData, headers=headers)
    print(response["data"], "응답!")
    print(response["data"]["dreamCardId"], "번호..?!")
    dreamCardId = response["data"]["dreamCardId"]

    with open(img_path, "rb") as file:
        files = {"file": (img_path, file)}
    requests.post(f"https://j9b301.p.ssafy.io/api/s3/dream/image/{dreamCardId}", files=files, headers=file_headers)
    print(files, "files#########")
    
    return response

