# main.py

from fastapi import FastAPI 
from typing import List
import httpx
import asyncio

from pydantic import BaseModel

# 꿈 데이터
class DreamModel(BaseModel):
    dreamCardContent: str
    dreamCardAuthor: int
    isShow: bool

app = FastAPI()
URL = "https://j9b301.p.ssafy.io/api"

# class Item(BaseModel):

# 테스트 용도
@app.get("/data")
def root():
    return "Hello FastAPI"
@app.get("/")
def root():
    return "Hello FastAPI"

# exit()

async def request(client):
    response = await client.get(URL)
    return response.text

@app.post("/data/night/dream/create")
async def dreamProcessing(data: DreamModel):
    # 받은 데이터 처리
    dreamCardContent = data.dreamCardContent
    dreamCardAuthor: data.dreamCardAuthor
    isShow = "T" if data.isShow else "F"


