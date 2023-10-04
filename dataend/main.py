# main.py

from fastapi import FastAPI 

app = FastAPI()

@app.get("/")
def root():
    return "Hello FastAPI"

# @app.get("/json")
# def printJson():
#     return {
#         "Number": 12345
#     }

# class Post(BaseModel):
#     title: str
#     content: str

# @app.post("/posts")
# def createContents(post: Post):
#     title: post.title
#     content = post.content