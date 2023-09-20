// 이미지
// 뒤집을 때 나옴

// Image

// 내용

// 리액트
import React, {useState} from "react";

// 컴포넌트
import Button from "components/common/Button";
import DreamCardGrade from "./DreamCardGrade";

// 스타일
import styled, {css} from "styled-components";
import "./NightFlipCard.css"
import Image from "style/Image";
import Container from "style/Container";
import { Box } from "style/Box";
import Text from "style/Text";

const CustomKeywordBox = styled.div`
  background-color: #3b3170;
  color: white;
  padding: 0.25rem;
  text-align: center;
  border-radius: 0.25rem;
`

const NightFlipCardKeyBtnWrap = styled.div`
  display: grid;
  grid-template-columns: 0.7fr 0.3fr;
  margin: inherit;
`
const NightFlipCardKeywordWrap = styled.div`
  display: flex;
  justify-content: start;
  align-items: center;
  padding: inherit;
  gap: 3vw;
`

const ButtonDiv = styled.div`
  text-align: right;
`
// 플립 카드 닉네임과 일자 용도
const NightFlipCardBotWrap = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 0;
`

const NightFlipCard = () => {

  const [isFlipCard, setIsFlipCard] = useState(false)

  const dreamCardFlipStyle = {
    transform: isFlipCard ? 'translateY(0)' : "translateY(100%)"
  }

  return (
    <>
    <Container $baseContainer>
      <div className="card" onClick={(e:any) => {
        if (!e.target.className.includes("blockClickEvent")) {
          console.log(e.target.className, e.target.class)
          setIsFlipCard(!isFlipCard)
          return
        }
      }}>
  {/* <div className="card-image"></div> */}
      <Image $mainImage $nightImageBorder>
        <img src="https://s3.ap-northeast-2.amazonaws.com/b301.s3test.bucket/userProfile/1283c65e-ba59-4a67-8852-b4a20abf6500%EC%8B%9C%EC%8A%A4%ED%85%9C%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98.png
"/>
      </Image>
        <div className="card-description" style={dreamCardFlipStyle}>
          <NightFlipCardKeyBtnWrap >
            <NightFlipCardKeywordWrap>
              <CustomKeywordBox className="blockClickEvent"> 
                <Text $nightKeword className="blockClickEvent">
                키워드1
                </Text>
              </CustomKeywordBox >
              <CustomKeywordBox className="blockClickEvent">
                <Text $nightKeword className="blockClickEvent">
                  키워드2
                </Text>          
              </CustomKeywordBox>
              <CustomKeywordBox className="blockClickEvent">
                <Text $nightKeword className="blockClickEvent">
                    키워드3
                </Text>          
              </CustomKeywordBox>
            </NightFlipCardKeywordWrap>
            {/* d */}
            <ButtonDiv >
              <Button $nightMiddlePurple className="blockClickEvent">꿈 경매</Button>
            </ButtonDiv>
          </NightFlipCardKeyBtnWrap>

          <DreamCardGrade positiveGrade="S" rareGrade="A"/>
          <NightFlipCardBotWrap >
            <Text className="blockClickEvent">유저 닉네임</Text>
            <Text>시간 </Text>
          </NightFlipCardBotWrap>
        </div>
      </div>

    </Container>
    </>
  )
}

export default NightFlipCard