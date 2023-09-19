// 이미지
// 뒤집을 때 나옴

// Image

// 내용

// 리액트
import React from "react";

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
  grid-template-columns: 70%, 30%;
  margin: inherit;
`
const NightFlipCardKeywordWrap = styled.div`
  display: flex;
  justify-content: space-between;
`

const ButtonDiv = styled.div`
  right: auto;
`

const NightFlipCard = () => {

  return (
    <>
    <Container $baseContainer>
      <div className="card">
  {/* <div className="card-image"></div> */}
      <Image $mainImage $nightImageBorder>
        <img src={`${process.env.PUBLIC_URL}/image/samsung.png`}/>
      </Image>
        <div className="card-description">
          <NightFlipCardKeyBtnWrap>
            <NightFlipCardKeywordWrap>
              <CustomKeywordBox > 
                <Text $nightKeword>
                키워드1
                </Text>
              </CustomKeywordBox>
              <CustomKeywordBox >
                <Text $nightKeword>
                  키워드2
                </Text>          
              </CustomKeywordBox>
              <CustomKeywordBox >
                <Text $nightKeword>
                    키워드3
                </Text>          
              </CustomKeywordBox>
            </NightFlipCardKeywordWrap>
            {/* d */}
            <ButtonDiv>
              <Button $nightMiddlePurple>꿈 경매</Button>
            </ButtonDiv>
          </NightFlipCardKeyBtnWrap>

          <DreamCardGrade positiveGrade="S" rareGrade="A"/>
          <p className="text-body">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.</p>
        </div>
      </div>

    </Container>
    </>
  )
}

export default NightFlipCard