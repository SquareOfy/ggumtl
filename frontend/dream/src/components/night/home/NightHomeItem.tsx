// 제목
{/* <NightFlipCard></NightFlipCard> */}
// 좋아요

// 리액트
import React from "react";

// 컴포넌트
import NightFlipCard from "../nightcommon/NightFlipCard";
import Heart from "components/common/Heart";

// 스타일
import styled from "styled-components";
import Container from "style/Container";
import Text from "style/Text";
import Image from "style/Image";

const ProfileDateWrap = styled.div`
  display: flex;
  justify-content: space-between;
  margin-left: 0.5rem;
  margin-right: 1rem;
`

const ProfileWrap = styled.div`
  display: flex;
  justify-content: start;
  /* line-height: 100%; */
  & > div:nth-child(2) {
    & > div{
      text-align: center;
    }
  }
`
const CustomImage = styled(Image)`
    width: 2.5rem;
    height: 2.5rem;
    margin-left: 0.5rem;
    margin-right: 0.5rem;
    & > img {
      width: 100%;
      object-fit: cover;
      aspect-ratio: 1/1;
      object-position: center;
      border-radius: 50%;
    }
`

const MarginBot = styled.div`
  margin-bottom: 2rem;
`


// const HeartMarginLeft = styled.div`
//   margin-left: 1rem;
// `

const NightHomeItem = () => {

  return (
    <>
    <Container $baseContainer>

      <ProfileDateWrap>
        <ProfileWrap>
          <CustomImage 
          onClick={()=>console.log("짠")}
          ><img src={`${`${process.env.PUBLIC_URL}/image/iu.png`}`}/></CustomImage>
          <Text $verticalAlign $nightWhite>닉네임</Text>
        </ProfileWrap>
        <Text $verticalAlign $nightWhite>23/09/04</Text>
      </ProfileDateWrap>

      {/* 뒤집히는 카드 */}
      <NightFlipCard />

      {/* 좋아요 버튼 */}
        <Heart />
    </Container>
    <MarginBot/>
    </>
  )
}

export default NightHomeItem