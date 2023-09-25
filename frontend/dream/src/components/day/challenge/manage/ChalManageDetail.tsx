
// TitleBox

//  TextBox :제목
// 미션 내용

// Wrapping
// TextBox :제목  카테고리
// TextBox :제목  챌린지 기간

// FullwidthBar //성취도

// LongButton 챌린지 인증하기 // 하루 1회 했으면 "비활성"
{/*  이동: <ChalCreateCertPage></ChalCreateCertPage> */}

// 다른 사람들의 응원메시지를 확인해보세요 //  인증기간에 따른 활성//비활성
// LongButton 타임 캡슐 + 타임캡슐 이미지 // 클릭 시 ChalCapsulePage

// 리액트
import React, {useEffect,useState} from "react";

// 컴포넌트
import Button from "components/common/Button";
import Container from "style/Container";
import { Box, BoxTitle } from "style/Box";
import { useParams } from "react-router";
import basicHttp from "api/basicHttp";
import { ChalDetailDataType, ChalDetailInfoProps } from "../ChalDetail";
import Wrap from "style/Wrap";
import styled from "styled-components";
import tokenHttp from "api/tokenHttp";
// 스타일


// progress 속성을 정의
interface ProgressBarProps {
  progress :number;
}

const ProgressBar = styled.div<ProgressBarProps>`
  height: 0.8rem;
  margin-right: 0.75rem;
  border-radius: 1rem;
  color: #997ad8;
  position: relative;
  background-color: #F9F9F9;

  & > p {
    font-size: 0.8rem;
    z-index: 10;
    color: black;
  }
  
  &::before {
    content: "";
    position: absolute;
    inset: 0 calc(${props => 100 -props.progress}%) 0 0;
    border-radius: inherit;
    background: currentColor;
    animation: p6 2s;
  }
  @keyframes p6 {
    /* 100% {inset:0} */
    0% {
      width: 0;
    }
    100% {
      width: ${props => props.progress}%; // 원하는 최대 너비
    }
  }
`


// 챌린지 디테일 초기값
const initialChalDetail:ChalDetailInfoProps = {
  chalDetailData:{
    challengeContent :'',
    challengeId : 0,
    challengeTitle :'',
    keyword :'',
    participationCount :0,
    badgeUrl :'',
    period :'',
    ranking: [
      {
        ranking : 0,
        nickName :'',
      },
    ]
  },
} 

const ChalManageDetail = () => {
  const params = useParams()
  const currentChallengeId = Number(params.challengeId)
  const [chalData, setChalData] = useState<ChalDetailDataType>()

  const [progress, setProgress] = useState<number>(70); // 꿈틀도 추후 변경하기
  
  useEffect(()=>{
    // 렌더링되었을 때 참여
    tokenHttp.get(`day/challenge/item/${currentChallengeId}`)
      .then((response)=>{
        const res = response.data.data
        setChalData(res.detail)
        console.log(chalData)
        // 성취도 계산
        
        // 현재 시작 후 몇일인지 계산

      })
      .catch((e)=>{console.log(e)})
  })

  return (
    <Container $dayBaseContainer>
      <Box $mainTitleBox>
        {/* 뱃지 이미지 아직 안됨 */}
        <img src={`${chalData?.badgeUrl}`}></img> 
        {chalData?.challengeTitle}
      </Box>
      <Box $wideTextBox $day>
        <BoxTitle $boxTitle>미션</BoxTitle>
        {chalData?.challengeContent}
      </Box>

      {/* 중간 2단 박스 */}
      <Container $spaceBetweenContainer>
        <Box $doubleWidth $day $chalManageBox>
          <BoxTitle $boxTitle>카테고리</BoxTitle>
          <p>{chalData?.keyword}</p>
        </Box>

        <Box $doubleWidth $day $chalManageBox>
          <BoxTitle $boxTitle>챌린지 기간</BoxTitle>
          <p>{chalData?.period}</p>
        </Box>
      </Container>

      {/* 성취도 바 */}
      <ProgressBar progress={progress}>
        {/* <BoxTitle $boxTistle>성취도</BoxTitle> */}
        <p>{progress}</p>
      </ProgressBar>  

    </Container>
  )
}

export default ChalManageDetail