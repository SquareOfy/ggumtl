# -*- coding: utf-8 -*-

import json

input_path = ".\\addKeywords\\addKeywords4.json"
with open(input_path, 'r', encoding='utf-8') as file:
    keyData = json.load(file)

for data in keyData:
    if "욕설" in data['analysis']['keywords']:
        # 아래 코드는 꼭 넣어라.
        data['dream'] = data['dream'].replace("음란/욕설이 담긴 글을 등록할 경우 해당 게시물은 경고 없이 삭제됩니다", " ")
        data['analysis']['keywords'].remove("욕설")
        
    if "음란" in data['analysis']['keywords']:
        data['dream'] = data['dream'].replace("음란/욕설이 담긴 글을 등록할 경우 해당 게시물은 경고 없이 삭제됩니다", " ")   
        data['analysis']['keywords'].remove("음란")
    
# exit()

# pprint(data_list)

output_path = ".\\addKeywordsMod\\addKeywordsMod4.json"

with open(output_path, 'w', encoding='utf-8') as out:
    json.dump(keyData, fp=out, ensure_ascii=False, indent=2)