# -*- coding: utf-8 -*-

import json

input_path = ".\\rawData\\crawling_data_4.json"
with open(input_path, 'r', encoding='utf-8') as file:
    rawData = json.load(file)

print(rawData[3])


data_list = []

dataLength = len(rawData)

n=2
# for idx in range(0, dataLength-1, n):
#     if n == 1: n = 2
#     if (rawData[idx]['title'] == rawData[idx+1]['title'] and rawData[idx+1]['writer'] == "해몽이") or (rawData[idx+1]['title']=="답변" and rawData[idx+1]['writer'] == "꿈풀이"):
#         push_data = {rawData[idx]['content']: {'dreamTelling': rawData[idx+1]['content']}}
#         data_list.append(push_data)
#     else: 
#         n = 1
for idx in range(0, dataLength-1, n):
    if n == 1: n = 2
    if (rawData[idx+1]['writer'] == "해몽이") or (rawData[idx+1]['writer'] == "꿈풀이"):
        push_data = { 'dream': rawData[idx]['content'],
                     "analysis": {'dreamTelling': rawData[idx+1]['content']}}
        data_list.append(push_data)
    else: 
        n = 1

# pprint(data_list)

output_path = ".\\newData\\output4.json"

with open(output_path, 'w', encoding='utf-8') as out:
    json.dump(data_list, fp=out, ensure_ascii=False, indent=2)
