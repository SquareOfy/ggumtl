# -*- coding: utf-8 -*-
# # 한국어 분석기

from konlpy.tag import Kkma, Okt, Komoran, Hannanum

import re
kkma = Kkma()
okt = Okt()
komoran = Komoran()
hannanum = Hannanum()

# .,!?

# text = "안녕 클레오파트라 세상에서 제일 가는 포테이토 칩입니다."
# text = "집에 들어와보니청소를 깨끗이 한듯 집이 너무 깨끗한거에요.집이 좁아서 짐정리를 해야겠다고 생각해왔거든요.좀 이상한느낌이 들었지만 첫느낌은 꽤 깔끔해져서 좋다고 생각하다꿈에서 뭔가 없어진거 같다고 막 두리번거리고 애써서 뭐가 없어졌는지 칮으려고 보니방에 책상이랑주방에 싱크대가 없어졌더라구요.근데 사실 깨서 생각해보니 살림살이를 다 털린거였어여.가구 몇개없어진게 아니라 책이랑 화장품이랑 옷이랑 다요 ㅠㅠ그래서 좁은 원룸이 넓어보여서 좋다고 느끼고 있었어요몸이 아파서 사직서를 냈는데흉몽일까요? ㅠ"
text = "집에 들어갔다가 방에 들어간다 그리고 학교에 들어갈 거다...!!"
text2 = "저는 지금 공부하고 싶습니다. 과연 나는 어떤 데이터가 추출될까요?"

# .,!? 제거
# newText = re.sub(",|.|!|?", " ", text)
# print(newText)
# exit()

# 불용어 처리
input_path = ".\\stopwords.txt"
txt = open(input_path, 'r', encoding='utf-8')
stopWords = set()

# 불용어 목록
lines = txt.readlines()
for line in lines:
    line = line.strip()
    stopWords.add(line)

# print(stopWords)

my_words = okt.morphs(text, stem=True)

rlt_words = [word for word in my_words if not word in stopWords]
print(rlt_words, ": 불용어 처리")
print('############')

# print("[Kkma 함수]")
# print(kkma.nouns(text))
# print("okt 어절 추출")
# print(okt.phrases(text))
# print("okt 명사")
# print(okt.nouns(text))
print("okt stem 사용해서 어간 추출")
print(okt.morphs(text, stem=True), "어간 추출 불용어 미처리")