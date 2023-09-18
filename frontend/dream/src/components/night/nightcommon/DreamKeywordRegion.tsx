// 옥션디테일, (주인만 볼 수 있는) 개별 꿈 디테일 페이지 키워드 모음

// 리액트
import React from "react";

// 컴포넌트

// 스타일
import { Box, GradeWrappingBox } from "style/Box";
import Container from "style/Container";
import Text from "style/Text";

interface DreamKeywordProps {
  keywords :string[]
}


const DreamKeywordRegion = (props:DreamKeywordProps) => {
  const dreamKeywordDataset = props.keywords
  return (
    <>
    <Container $baseContainer>
      <Container $nightKeyword>
        <Box $keywordBoxNight> <Text $nightKeword $nightWhite>안녕</Text> </Box>
        <Box $keywordBoxNight> <Text $nightKeword $nightWhite>삼전</Text> </Box>
        <Box $keywordBoxNight> <Text $nightKeword $nightWhite>가즈아</Text> </Box>
        <Box $keywordBoxNight> <Text $nightKeword $nightWhite>우리</Text> </Box>
        <Box $keywordBoxNight> <Text $nightKeword $nightWhite>모두</Text> </Box>
        <Box $keywordBoxNight> <Text $nightKeword $nightWhite>까즈아</Text> </Box>
        <Box $keywordBoxNight> <Text $nightKeword $nightWhite>제발</Text> </Box>
        <Box $keywordBoxNight> <Text $nightKeword $nightWhite>가는거야</Text> </Box>
        <Box $keywordBoxNight> <Text $nightKeword $nightWhite>구뤠!!</Text> </Box>
      </Container>
    </Container>
    </>
  )
}

export default DreamKeywordRegion