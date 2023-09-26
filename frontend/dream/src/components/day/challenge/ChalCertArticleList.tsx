
// 게시물 상단
// CircleImage - 프로필
// 닉네임

// 작성 일자

// MainImage - 인증글 이미지

// 게시글 하단
// 좋아요, 댓글, ShortWithBar

// TextBox 
// 챌린지 내용

// 리액트
import Heart from "components/common/Heart";
import React, {useState, useEffect} from "react";

// 컴포넌트
import Text from "style/Text";
import Container from "style/Container";
import { changeDate } from "utils/dateForm";
import { useNavigate, useParams } from "react-router-dom";
import basicHttp from "api/basicHttp";
import ChalCertArticleItem from "./ChalCertArticleItem";
import tokenHttp from "api/tokenHttp";
import InfiniteScroll from "components/common/InfiniteScroll";

// 스타일

// 타입
export interface CertArticleItemType {
  challengeDetailContent:string;
  challengeDetailCount:number;
  challengeDetailId:number;
  commentCount:number;
  like:boolean
  likeCount:number;
  nickname:string;
  photoUrl:string;
  userId:number;
  createdAt:string;
}

export interface CertArticleListType extends Array<CertArticleItemType>{}

const ChalCertArticleList = () => {

  const params = useParams()
  const currentChallengeId = params.challangeId
  const navigate = useNavigate()
  const [certArticleList, setCertArticleList] = useState<CertArticleListType>([])
  const [arriveEnd, setArriveEnd] = useState<boolean>(true); // 바닥에 다다름을 알려주는 변수
  const [lastItemId, setLastItemId] = useState<number>(0); // db엔 0번이 없음
  const [hasNext, setHasNext] = useState<boolean>(true); 

  useEffect(()=>{
    let axiosUrl :string = ''

    if (lastItemId === 0) {
      axiosUrl = `/day/challange/detail/${currentChallengeId}/list?size=6`
    } else {
      axiosUrl = `/day/challange/detail/${currentChallengeId}/list?lastItemId=${lastItemId}&size=6`
    }
    
    if (arriveEnd && hasNext) {  // 끝에 도달하고 다음이 있을 때 다음 데이터 호출
      tokenHttp.get(axiosUrl)
        .then((response)=>{
          const res = response.data.data
          const resultList = res.resultList
          // console.log('챌린지 인증 리스트',resultList)
          // console.log('마지막 아이템 아이디', resultList[resultList.length-1].challengeDetailId)
          setCertArticleList([...certArticleList, ...resultList])
          setArriveEnd(false);
          setHasNext(res.hasNext)
          setLastItemId(resultList[resultList.length-1].challengeDetailId); // 마지막 item id 변경
        })
        .catch((e)=>{console.log(e)})
      }
  },[arriveEnd])

  return (
    <>
      <Container $baseContainer>
        <InfiniteScroll 
          setArriveEnd={setArriveEnd} 
          component = {
            certArticleList.map((item: CertArticleItemType, idx: number)=>(
              <ChalCertArticleItem certData={item} key={idx}/>
            )) 
          }
        >
        </InfiniteScroll>
      </Container>
    </>
  )
}

export default ChalCertArticleList