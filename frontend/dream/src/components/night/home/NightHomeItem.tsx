// 제목
{/* <NightFlipCard></NightFlipCard> */}
// 좋아요

// 리액트
import React, {useEffect, useState} from "react";

// 외부 파일
import axios from "axios";
import { baseUrl } from "api/api";
import { changeDate } from "utils/dateForm";

// 컴포넌트
import NightFlipCard from "../nightcommon/NightFlipCard";
import Heart from "components/common/Heart";

// 스타일
import styled from "styled-components";
import Container from "style/Container";
import Text from "style/Text";
import Image from "style/Image";

// 타입
import { NightHomeItemType } from "./NightHomeList";

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

// 타입
export interface NightHomeItemProps {
  cardData : NightHomeItemType
}

export interface ReverseCardType {
  dreamCardId: number;
  dreamCardOwner: number;
  ownerNickname: string;
  dreamCardAuthor: number;
  grade: string;
  createdAt: string;
  positiveGrade: string;
  rareGrade: string;
  auctionStatus: string;
  isShow: string;
  keywords: string[];
}

const NightHomeItem = ({cardData}:NightHomeItemProps) => {
  console.log("왔니", cardData.ownerProfileUrl)
  const [reverseCard, setReverseCard] = useState<ReverseCardType | null>(null)

  useEffect(()=> {
    axios(`${baseUrl}/night/dream/detail/${cardData.dreamCardId}`)
    .then(res=> {
      setReverseCard(res.data.data)
      console.log(res.data, "아이템")
    })
    .catch(err => console.log(err, "아이템 에러"))
  }, [])
  return (
    <>
    <Container $baseContainer>

      <ProfileDateWrap>
        <ProfileWrap>
          <CustomImage 
          // 여기에 이동하는 곳
          onClick={()=>console.log("짠")}
          ><img src={cardData.ownerProfileUrl}/></CustomImage>
          <Text $verticalAlign $nightWhite> {reverseCard?.ownerNickname} </Text>
        </ProfileWrap>
        <Text $verticalAlign $nightWhite>{changeDate(cardData.createAt)}</Text>
      </ProfileDateWrap>

      {/* 뒤집히는 카드 */}
      <NightFlipCard reverseCardData={reverseCard}/>

      {/* 좋아요 버튼 */}
        <Heart 
        isLike={cardData.like}
        likedNumber={cardData.likedNumber}
        />
    </Container>
    <MarginBot/>
    </>
  )
}

export default NightHomeItem