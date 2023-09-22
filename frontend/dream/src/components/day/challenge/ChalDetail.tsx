{/* <ChalDetailInfo></ChalDetailInfo> */}


// 참여하기 -> ChalCapsuleCreatePage, 관리하기 버튼
// LongButton

// 인스타 피드같은 게시물
// map
// <ChalCertArticle></ChalCertArticle>

// 리액트
import React, {useEffect, useState}from "react";
import basicHttp from "api/basicHttp";

// 컴포넌트
import ChalDetailInfo from "./ChalDetailInfo";
import Button from "components/common/Button";
import ChalCertArticleList from "./ChalCertArticleList";
import Container from "style/Container";
import { useNavigate } from "react-router-dom";

// 스타일

export interface ChalDetailInfoProps {
  chalDetailData: ChalDetailDataType
}

export interface ChalDetailDataType {
  challengeContent :string,
  challengeId :number,
  challengeTitle :string,
  keyword :string,
  participationCount :number,
  // badgeUrl :string,
  period :string,
  ranking: [
    {
      ranking :number,
      nickName :string,
    }
  ];
}

// 챌린지 디테일 초기값
const initialChalDetail:ChalDetailInfoProps = {
  chalDetailData:{
    challengeContent :'',
    challengeId : 0,
    challengeTitle :'',
    keyword :'',
    participationCount :0,
    // badgeUrl :'',
    period :'',
    ranking: [
      {
        ranking : 0,
        nickName :'',
      },
    ]
  },
} 

const ChalDetail = () => {

  const navigate = useNavigate()

  const [chalDetailData,setChalDetailData] = useState<ChalDetailDataType>(initialChalDetail.chalDetailData)
  const [chalParticipate, setChalParticipate] = useState<boolean>(true)
  const [chalParticipateDay,setChalParticipateDay] = useState<number>(0)

  useEffect(()=>{
    basicHttp.get('/day/challenge/item/2')
      .then((response)=>{
        const res = response.data.data
        setChalDetailData(res.detail)
        setChalParticipate(res.participate)
        setChalParticipateDay(res.participateDay)
        // console.log(res)
      })
      .catch((e)=>{console.log(e)})
  },[])

  return (
    <Container $dayBaseContainer>
    {/* 공부흔적 남기기 */}
    <ChalDetailInfo chalDetailData={chalDetailData}/>

    {/* 참여하기 버튼 */}
    {
      chalDetailData ? (
        <Button 
          $fullWidth 
          $dayBlue 
          onClick={()=>{navigate('/day/mychallenge/:challengeId')}}
        >관리하기</Button>
        ):(
        <Button 
          $fullWidth 
          $dayBlue 
          onClick={()=>{navigate('/day/challenge/create')}}
        >참여하기</Button>
      )
    }
    {/* 인증글 목록 */}
    <ChalCertArticleList />
    </Container>
  )
}
export default ChalDetail