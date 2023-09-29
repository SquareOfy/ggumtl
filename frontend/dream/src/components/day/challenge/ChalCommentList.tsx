// list.map
// <ChalCommentItem></ChalCommentItem>

// CircleImage :댓글프로필 -> 하단바 사라짐.
// Input // end에 게시 , placeholder

// 리액트
import React, {useEffect, useState, Dispatch, SetStateAction} from "react";

// 컴포넌트
import ChalCommentItem from "./ChalCommentItem";
import Container from "style/Container";
import Input from "style/Input";
import tokenHttp from "api/tokenHttp";
import InfiniteScroll from "components/common/InfiniteScroll";
import { useParams } from "react-router-dom";

// 스타일

// 타입
export interface ChalCommentAxiosType {
  commentId :number,
  userId :number,
  nickname :string,
  content :string,
}

const ChalCommentList = () => {

  const params = useParams()
  const challengeId = params.challengeId
  const challengeDetailId = params.challengeDetailId

  const [commentList, setCommentList] = useState<ChalCommentAxiosType[]>([])
  const [lastItemId, setLastItemId] = useState<number>(0);
  const [hasNext, setHasNext] = useState<boolean>(true); 

  // infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(false); // 바닥에 다다름을 알려주는 변수

  const getAxios = () => {
    let apiAddress :string = "";
    
    if (lastItemId === 0) {
      apiAddress = `/day/challenge/detail/${challengeDetailId}/comment?size=12`
    }
    else {
      apiAddress = `/day/challenge/detail/${challengeDetailId}/comment?lastItemId=${lastItemId}&size=7`
    }

    if (arriveEnd && hasNext) {
      console.log(apiAddress)
      tokenHttp.get(apiAddress)
        .then((response) => {
          const res = response.data.data
          setCommentList([...commentList,...res.resultList])
          setHasNext(res.hasNext)
          console.log("댓글 무한스크롤 성공",res)
        })
        .catch((err)=>{console.log("댓글 무한 스크롤 실패",err)})
    }
  }

  useEffect(()=>{
    if (commentList[commentList.length-1]) {
      setLastItemId(Number(commentList[commentList.length-1].commentId))
    }
  },[setCommentList,commentList])

  useEffect(() => {
    if (arriveEnd) {
      getAxios();
      setArriveEnd(false);
    }
  }, [arriveEnd])

  return (
    <>
      <Container $commentListContainer>

      {/* 댓글 리스트 */}
      {
        commentList &&
        <InfiniteScroll
        setArriveEnd={setArriveEnd} 
        // lastItemId={lastItemId}
        component={
          commentList?.map((item: ChalCommentAxiosType, idx:number) => (
            <ChalCommentItem commentData={item} key={idx}/>
          ))}
        />
      }

      </Container>
    </>
  )
}

export default ChalCommentList