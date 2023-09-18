{/* FlipCard 크기에 맞는 일반 이미지 쓰세요. (Flip 하지 마세요.) */} 

// 키워드들
// 등급 어쩌구
// 3개의 Input

{/* <DreamRecordContentsTab></DreamRecordContentsTab> */}

// 2개의 버튼

// 리액트
import React, {useState} from "react";

// 컴포넌트
import DreamRecordContentsTab from "../dream/DreamRecordContentsTab";
import DreamKeywordRegion from "../nightcommon/DreamKeywordRegion";
import DreamCardGrade from "../nightcommon/DreamCardGrade";
import Button from "components/common/Button";

// 스타일
import styled, {css} from "styled-components";
import Container from "style/Container";
import Input, {InputProps} from "style/Input";
import Image from "style/Image";
import Text from "style/Text";
import Wrap from "style/Wrap";

const AuctionInputWrap = styled.div`
  display: grid;
  margin-left: 1rem;
  margin-right: 1rem;
  grid-template-columns: 40% 60%;
  ;
`

const CustomInput = styled(Input)`
  color: #3E3E3E;
  background-color: rgb(190, 169, 215, 50%);
`

const CustomText = styled(Text)`
  padding-left: 1rem;
  line-height: 2rem;
`
const WarnText = styled(Text)`
  padding-left: 1rem;
  margin-top: 0.125rem;
  margin-bottom: 0.125rem;
  font-size: 0.5rem;
  color: #423535;
`

const MarginBot = styled.div`
  margin-bottom: 0.75rem;
`

export interface AuctionCreateType {
  startAuctionMoney :number // 경매 초기가
  immediatelyBuyMoney: number // 즉시구매가
}

const AuctionCreate = () => {
  const [startAuctionMoney , setStartAuctionMoney] = useState(1000)
  const [immediatelyBuyMoney , setImmediatelyBuyMoney] = useState(1000)

  // 메시지 바꾸기 용도
  const [isSMBaseMSG, setIsSMBaseMSG] = useState(false)
  const [isIMBaseMSG, setIsIMBaseMSG] = useState(false)

  // const endDate = today.setDate(today.getDate()+1)
  const endDate = () :string => {
    const today = new Date()
    const nextDay = new Date(today.setDate(today.getDate()+1))
    const year = nextDay.getFullYear()
    const month = nextDay.getMonth() +1
    const day = nextDay.getDate()
    const hour = nextDay.getHours()
    const miniutes = nextDay.getMinutes()
    
    return [year, month, day].join(".") + " " + [hour, miniutes].join(":")
  }

  
  // 추후 호가 몫만 들고가는 유효성 검사
  const changeStartMoney = () => {
    setIsSMBaseMSG(false)
  }

  const changeImmediatelyBuyMoney = () => {
    setIsIMBaseMSG(false)
  }

  return (
    <>
    <Container $baseContainer>
    {/* 이미지 */}
    <Image $mainImage $nightImageBorder><img src={`${process.env.PUBLIC_URL}/image/iu.png`}/></Image>
    <DreamKeywordRegion keywords={["2", "33"]}/>
    <DreamCardGrade   
    positiveGrade="S"  
    rareGrade="A"
    />

  {/* 옥션 전용 Input */}
    <AuctionInputWrap>
      <CustomText $isBold $black>마감시간</CustomText>
      <CustomInput $auctionInput $nightColor disabled value={endDate()}/>
      </AuctionInputWrap>
    <MarginBot/>
    <AuctionInputWrap>
    <CustomText $isBold $black>시작가</CustomText>
    <CustomInput $auctionInput $nightColor
    type="number"
    onChange={(e) => setStartAuctionMoney(e.currentTarget.value)}
    onBlur={changeStartMoney}
    value={startAuctionMoney}
    />
    </AuctionInputWrap>
    <AuctionInputWrap>
      <CustomText />
    { !isSMBaseMSG && <WarnText>1000단위 금액을 입력해주세요.</WarnText>}    
    { isSMBaseMSG && <WarnText $danger>숫자만 입력해주세요.</WarnText>}    
    </AuctionInputWrap>
    <AuctionInputWrap>
    <CustomText $isBold $black>즉시 판매</CustomText>
    <CustomInput $auctionInput $nightColor
        type="number"
        onChange={(e) => setImmediatelyBuyMoney(e.currentTarget.value)}
        onBlur={changeImmediatelyBuyMoney}
        value={immediatelyBuyMoney}
    />
    </AuctionInputWrap>
    <AuctionInputWrap>
      <CustomText />
    <WarnText>1000단위 금액을 입력해주세요.</WarnText>
    </AuctionInputWrap>
    <MarginBot/>

    {/* 꿈 기록과 해몽 바뀌는 곳 ~ props 필요 */}
    <DreamRecordContentsTab />

    <Wrap $nightBotButtonWrap>
      <div>
      </div>
      <div>
        <Button $nightPalePurple>삭제</Button>
        <Button $nightPurple>경매</Button>
      </div>
    </Wrap>
    </Container>
    </>
  )
}

export default AuctionCreate;

