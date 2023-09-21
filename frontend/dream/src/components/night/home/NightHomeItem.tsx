// 리액트
import React, {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";

// 외부 파일
import axios from "axios";
import { changeDate } from "utils/dateForm";
import basicHttp from "api/basicHttp";

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
  dreamCardImageUrl: string;
  isShow: string;
  keywords: string[];
}

const NightHomeItem = ({cardData}:NightHomeItemProps) => {
  const navigation = useNavigate()
  const [reverseCard, setReverseCard] = useState<ReverseCardType | null>(null)
  useEffect(()=> {
    basicHttp(`/night/dream/detail/${cardData.dreamCardId}`)
    .then(res=> {
      setReverseCard(res.data.data)
      console.log(res.data.data)
    })
    .catch(err => console.log(err, "아이템 에러"))
  }, [])

  
  // 꿈 주인 유저 프로필로 이동
  const moveUserProfile = () => {
    navigation(`/night/profile/${cardData?.dreamCardOwner}`)
  }
  
  return (
    <>
    <Container $baseContainer>

      <ProfileDateWrap>
        <ProfileWrap>
          <CustomImage 
          // 여기에 이동하는 곳
          onClick={moveUserProfile}
          >
            {/* 에러나서 주석처리 */}
            {/* <img src={cardData?.ownerProfileUrl} alt="없음"/> */}
          </CustomImage>
          <Text $verticalAlign $nightWhite
          onClick={moveUserProfile}
          > {reverseCard?.ownerNickname} </Text>
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