// 리액트
import React, {useState} from "react";

// 컴포넌트
import Button from "components/common/Button";

// 스타일
import styled, {css} from "styled-components";
import { Box } from "style/Box";
import Text from "style/Text";

const DreamContentsTabWrap = styled.div`
  margin: 2rem 0.5rem;
`

const DreamTabWrap = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  // margin: inherit;
  & > div {
    text-align: center;
  }
`
const TabLine = styled.hr`
  border: 1px solid #D9D9D9;
  opacity: 0.5;
  margin: 0.25rem 1rem
`

const CustomText = styled(Text)<TabStyleType>`  
  color: #999999;
  ${(props) => props.$isActive && 
    css`
      color: #FFFFFF;
      font-weight: 700;
    `
  }
`

export interface TabStyleType {
  $isActive ?: boolean
}



// 2개 색깔에 따라 달라짐
const DreamRecordContentsTab = () => {
  // 꿈 기록 탭
  const [isRecordTab, setIsRecordTab] = useState(true)
  // 해몽 탭
  const [isInterpretTab, setIsInterpretTab] = useState(false)

  // 꿈 기록 탭 보여주기
  const show1RecordTab = () => {
    setIsRecordTab(true)
    setIsInterpretTab(false)
  }

  // 해몽 탭 보여주기
  const showInterpretTab = () => {
    setIsRecordTab(false)
    setIsInterpretTab(true)
  }

  return (
    <>
    <DreamContentsTabWrap>
      <DreamTabWrap>
        <CustomText 
        onClick={show1RecordTab}
        $isActive={isRecordTab}
        >꿈 기록</CustomText>
        <CustomText 
        onClick={showInterpretTab}
        $isActive={isInterpretTab}
        >해몽</CustomText>
      </DreamTabWrap>
      <TabLine/>
      {isRecordTab && <Box $wideTextBox $night>안녕 클레오파트라 난 기d록
        ㄴㅇㅇㄹㄴㄹㅇㄴㄹㄴㄹㅇㄴㄹㅇㅇㄴㄹㅇㄴㄹㅇㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ록
        ㄴㅇㅇㄹㄴㄹㅇㄴㄹㄴㄹㅇㄴㄹㅇㅇㄴㄹㅇㄴㄹㅇㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ록
        ㄴㅇㅇㄹㄴㄹㅇㄴㄹㄴㄹㅇㄴㄹㅇㅇㄴㄹㅇㄴㄹㅇㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ록
        ㄴㅇㅇㄹㄴㄹㅇㄴㄹㄴㄹㅇㄴㄹㅇㅇㄴㄹㅇㄴㄹㅇㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ록
        ㄴㅇㅇㄹㄴㄹㅇㄴㄹㄴㄹㅇㄴㄹㅇㅇㄴㄹㅇㄴㄹㅇㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ록
        ㄴㅇㅇㄹㄴㄹㅇㄴㄹㄴㄹㅇㄴㄹㅇㅇㄴㄹㅇㄴㄹㅇㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ록
        ㄴㅇㅇㄹㄴㄹㅇㄴㄹㄴㄹㅇㄴㄹㅇㅇㄴㄹㅇㄴㄹㅇㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ록
        ㄴㅇㅇㄹㄴㄹㅇㄴㄹㄴㄹㅇㄴㄹㅇㅇㄴㄹㅇㄴㄹㅇㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ록
        ㄴㅇㅇㄹㄴㄹㅇㄴㄹㄴㄹㅇㄴㄹㅇㅇㄴㄹㅇㄴㄹㅇㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ록
        ㄴㅇㅇㄹㄴㄹㅇㄴㄹㄴㄹㅇㄴㄹㅇㅇㄴㄹㅇㄴㄹㅇㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ</Box>}
      {isInterpretTab && <Box $wideTextBox $night>난 해몽</Box>}
    </DreamContentsTabWrap>
    </>
  )
}

export default DreamRecordContentsTab