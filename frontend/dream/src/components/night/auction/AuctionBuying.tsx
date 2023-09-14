// 작은 이미지
// 호가 버튼
// 보유 꿈머니
// input - 참여버튼
// 경고문구

// 2개 텍스트 박스(AuctionDetail 복붙)
// 리액트
import React, {useState} from "react";

// 컴포넌트
import Button from "components/common/Button";

// 스타일
import styled, {css} from "styled-components";
import Image from "style/Image";
import Container from "style/Container";
import Text from "style/Text";
import Input from "style/Input";

const AuctionBidContainer = styled.div`
  margin: 1rem 3rem;

 & :first-child {
  padding-left: 1rem;
 }

 /* & :last-child {
  padding-left: 1rem;
 } */
`

const BiddingWrap = styled.div`
  display: grid;
  grid-template-columns: 75% 25%;
  grid-gap: 1vw;
  margin-top: 1vw;
  margin-bottom: 1vw;
`

// 타입
interface AuctionBuyingAxiosType {

}

const AuctionBuying = () => {

  const point :number = 8000 // 서버에서받을 값(내 꿈머니)
  const biddingMoney :number = 5000 // 서버에서 받을 값
  const [myBiddingMoney, setMyBiddingMoney] = useState<number>(biddingMoney)
  const [askingMoney, setAskingMoney] = useState<number>(1000)

  // 숫자만 입력 받는 정규식
  const numberCheck = /^[0-9]+$/;

  const [lowerMoney, setLowerMoney] = useState<boolean>(false)
  const [lackMoney, setLackMoney] = useState<boolean>(false)
  const changeBiddingMoney = (e :any) => {
    if (!numberCheck.test(e.currentTarget.value)) {
      console.log("숫자 아니에요.")
      return
    }
    if (e.currentTarget.value < biddingMoney) {
      console.log("현재 가격보다 낮음")
      setMyBiddingMoney(biddingMoney)
      setLowerMoney(true)
      return
    } else if (e.currentTarget.value > point) {
      console.log("보유액 부족")
      setMyBiddingMoney(biddingMoney) // 여기 최초로
      setLackMoney(true)
      return
    }
    setMyBiddingMoney(e.currentTarget.value)
    setLowerMoney(false)
    setLackMoney(false)
    
  }
  
  const addBiddingMoney = () => {
    setMyBiddingMoney(() => myBiddingMoney+askingMoney)
  }

  return (
    <>
      {/* <Image $mainImage $nightImageBorder>
        <img src={`${process.env.PUBLIC_URL}/image/samsung.png`}
        />
      </Image> */}

      {/* 호가 */}
      <Container $centerContainer>
        <Button $nightPalePurple $biddingBtn
        onClick={addBiddingMoney}
        ><Text $black $isBold>+{askingMoney}</Text></Button>
      </Container>

      <AuctionBidContainer>
        <Text $nightKeword $nightWhite>나의 꿈머니: ${point}</Text>
        <BiddingWrap>
          <Input $nightColor $biddingValue 
          type="number"
          onChange={(e)=>setMyBiddingMoney(e.currentTarget.value)}
          onBlur={changeBiddingMoney}
          value={myBiddingMoney}
          />
          <Button $nightMiddlePurple $biddingBtn>참여</Button>
        </BiddingWrap>
        { lowerMoney && <Text $danger $nightKeword>
          현재 최고가보다 높은 금액을 입력해주세요</Text>}        
        { lackMoney && <Text $danger $nightKeword>
          보유 금액이 부족합니다.</Text>}
      </AuctionBidContainer>
    </>
  )
}

export default AuctionBuying