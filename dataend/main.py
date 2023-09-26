# main.py

from fastapi import FastAPI 
import httpx
import asyncio

from pydantic import BaseModel

app = FastAPI()
URL = "https://j9b301.p.ssafy.io/api/datatest/a"

# class Item(BaseModel):


@app.get("/data")
def root():
    return "Hello FastAPI"

async def request(client):
    response = await client.get(URL)
    return response.text

@app.get("/data/test")
async def testReq():
    async with httpx.AsyncClient() as client:
        response = client.get(URL)
        return response
    return response
