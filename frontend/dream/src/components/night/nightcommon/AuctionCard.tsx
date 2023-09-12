// 마감시간 : 3시간 전만 보이기. 
// 1시간 전 색깔 변경은 보고 생각하기.

{/* <SmallNightImage></SmallNightImage> */}
// 키워드들 - <KeywordRegion></KeywordRegion>
// 등급 - <GradeRegion></GradeRegion> 

// 리액트
import React from "react";

// 컴포넌트

// 스타일
import styled, {css} from "styled-components";
import Image from "style/Image";

const AuctionCardFrame = styled.div`
  background: rgba(190, 169, 215, 0.5);
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  width: 40vw;
  height: 75vw;
`

const AuctionCard = () => {

  return (
    <>
    <AuctionCardFrame>
    

    </AuctionCardFrame>
    </>
  )
}

export default AuctionCard