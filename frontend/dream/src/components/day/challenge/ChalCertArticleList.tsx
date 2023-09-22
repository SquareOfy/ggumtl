
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
import { useNavigate } from "react-router-dom";
import basicHttp from "api/basicHttp";
import ChalCertArticleItem from "./ChalCertArticleItem";

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

  const navigate = useNavigate()
  const [certArticleList, setCertArticleList] = useState<CertArticleListType>([])

  useEffect(()=>{
    basicHttp.get('/day/challange/detail/2/list?lastItemId=11&size=3')
      .then((response)=>{
        console.log(response.data.data)
        const resultList = response.data.data.resultList
        console.log(resultList)
        setCertArticleList([...certArticleList, ...resultList])
      })
      .catch((e)=>{console.log(e)})
  },[])


  return (
    <>
      <Container $baseContainer>
        {
          certArticleList.map((item: CertArticleItemType, idx: number)=>(
            <ChalCertArticleItem certData={item} key={idx}/>
          )) 
        }
      </Container>
    </>
  )
}

export default ChalCertArticleList