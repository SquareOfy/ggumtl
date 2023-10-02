# -*- coding: utf-8 -*-
# 한국어 키워드 추출(wordsKeywords)

# 여긴 필요
from konlpy.tag import Kkma, Okt, Komoran, Hannanum
import re

def remove_special_charaters(strs):
    # 정규표현식 사용해 특수문자를 띄어쓰기로
    after_txt = re.sub(r'[!@#$%^&*()\-_=+\\|{}\[\];:\'"<>,./?]', ' ', strs)
    return after_txt

okt = Okt()


# 불용어 처리
input_path = "./stopwords.txt"
stopTxtFile = open(input_path, 'r', encoding='utf-8')
stopWords = set()

# 불용어 목록
lines = stopTxtFile.readlines()
for line in lines:
    line = line.strip()
    stopWords.add(line)
def getDreamKeywords(text):
    preText = remove_special_charaters(text)
    oktText = okt.morphs(preText, stem=True)
    postOktText = [word for word in oktText if not word in stopWords]
    dreamKeywords = list(set(postOktText))
    return dreamKeywords
