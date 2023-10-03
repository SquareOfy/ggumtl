# 카카오 API
from PyKakao import Karlo

# env 파일 불러오기
import os
from dotenv import load_dotenv
load_dotenv()

# 네이버 번역
import sys
import requests
import io
import base64
import json
import urllib.request
import urllib.parse
import urllib
from PIL import Image

# karlo 세팅
KAKAO_KEY = os.getenv("KAKAO_API_KEY")
karlo = Karlo(service_key=KAKAO_KEY)


# 번역 세팅
papago_URL = os.getenv("PAPAGO_URL")
CLIENT_ID = os.getenv("NAVER_CLIENT_ID")
CLIENT_SECRET = os.getenv("NAVER_CLIENT_SECRET")

# 번역 파트
def translateDreamKeywords(kewords):
    encText = urllib.parse.quote(str(kewords))
    data= "source=ko&target=en&text="+encText
    request = urllib.request.Request(papago_URL)
    request.add_header("X-Naver-Client-Id", CLIENT_ID)
    request.add_header("X-Naver-Client-Secret", CLIENT_SECRET)
    response = urllib.request.urlopen(request, data=data.encode("utf-8"))
    rescode = response.getcode()

    if (rescode==200):
        response_body = response.read()
    else: 
        print("Error", rescode)
        return

    json_response = response_body.decode("utf-8")
    response_dict = json.loads(json_response)

    translated_text = response_dict["message"]["result"]["translatedText"]

    return translated_text



# karlo
# def postKarloRequest(text):
#     translated_text = translateDreamKeywords(text)
#     img_dict = karlo.text_to_image(translated_text, 1)

#     print(img_dict, "사전입니다!")

#     # 생성된 이미지 정보
#     img_to_string = img_dict.get("images")[0].get("image")
#     print(img_to_string, "이미지 문자열을 바꾸었습니다.")

# 성적 의미 제거
def nsrf(images):
    readData = requests.post(
        "https://api.kakaobrain.com/v2/inference/karlo/nsfw_checker",
        json={
            "images":images
        },
        headers={
            'Authorization': f'KakaoAK {KAKAO_KEY}',
            'Content-Type': 'application/json'
        }
    )

    response = json.loads(readData.content)
    return response

# 1. 인코딩 및 디코딩

# Base64 인코딩
def imageToString(img):
    img_byte_arr = io.BytesIO()
    img.save(img_byte_arr, format="PNG")
    my_encoded_img = base64.encodebytes(img_byte_arr.getvalue()).decode('ascii')
    return my_encoded_img

# Base64 디코딩 및 이미지 변환 함수
def stringToImage(base64_string, mode="RGBA"):
    imgdata = base64.b64decode(str(base64_string))
    img = Image.open(io.BytesIO(imgdata)).convert(mode)
    return img

# # 이미지 파일 불러오기
# img = Image.open("my_image.png")

# # 이미지를 Base64 인코딩하기
# img_base64 = imageToString(img)

# # Base64 인코딩 값을 디코딩해 이미지로 변환
# image = stringToImage(img_base64, mode="RGB")

# 2. 이미지 생성
createImgLink = 'https://api.kakaobrain.com/v2/inference/karlo/t2i'
# 이미지 생성 요청
def textToImage(prompt, negative_prompt):
    r = requests.post(
        'https://api.kakaobrain.com/v2/inference/karlo/t2i',        
        json={
            'prompt': prompt,
            'negative_prompt': negative_prompt
        },
        headers= {
            'Authorization': f'KakaoAK {KAKAO_KEY}',
            'Content-Type': 'application/json'
        }
    )


    # 응답 JSON 형식으로 변환
    response = json.loads(r.content)
    return response

# 프롬프트에 사용할 제시어
prompt = "A cat with white fur, girl with son, floating balloon, by Renoir"
negative_prompt = "sleeping cat, dog, human, ugly face, text, letter, signature, watermark, out of frame"

# 이미지 생성 API 호출
# response = textToImage(prompt, negative_prompt)
# 응답의 첫번째 이미지 생성 결과 출력

# print(result, "###")

# result.save("createImg4.png", format="PNG")
# result.show()

# 임시 파일 만들기 라이브러리
import tempfile

# # tmp_file_path에 저장하기 ############## 한 뭉탱이

# result = Image.open(urllib.request.urlopen(response.get("images")[0].get('image')))
# with tempfile.NamedTemporaryFile(delete=False, suffix=".png") as tmp_file:
#     result.save(tmp_file, format="PNG")
#     tmp_file_path = tmp_file.name

# files = {'file': ('createdImage.png', open(tmp_file_path, 'rb'), "image/png")}
# java_response = requests.post("자바", files=files)

########### 뭉탱이 끌

def getKarloImgPath(text):
    negative_prompt = "ugly face, text, letter, signature, watermark, out of frame"
    r = textToImage(text, negative_prompt)

    rlt = Image.open(urllib.request.urlopen(r.get("images")[0].get('image')))
    rlt.save("mmm.png", format="PNG") # 이건 불필요

    with tempfile.NamedTemporaryFile(delete=False, suffix=".png", dir="./tmp") as tmp_file:
        rlt.save(tmp_file, format="PNG")
        tmp_file_path = tmp_file.name
        tmp_file.close()
    print(tmp_file_path, "경로")
    return tmp_file_path

# getKarloImgPath(prompt)