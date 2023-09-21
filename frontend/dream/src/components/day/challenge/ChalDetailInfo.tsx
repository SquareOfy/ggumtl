// 상단 박스
// titleText
// Wrapping
// 기간, 참여자, 카테고리
// /Wrapping

// #더보기 전
// 더보기 버튼

// #더보기 후
// 미션 ContentBoxBox
// 획득 뱃지 ContentBox
// 연속 일수 랭킹 ContentBox
// 리액트
import React from "react";

// 컴포넌트
import Button from "components/common/Button";
import Container from "style/Container";
import { Box, BoxTitle } from "style/Box";
import Text from "style/Text";

// 스타일



const ChalDetailInfo = () => {
  // 가능하면 map으로 돌릴수 있도록 다시 제작
  return (
    <Container $dayBaseContainer $chalDetail>
      <Text>챌린지 제목</Text>
      {/* 기간 참여자 카테고리 */}
      <Container $spaceBetweenContainer>
        <Box $tripleWidth  $chalDetailBox>
          <BoxTitle $boxTitle>기간</BoxTitle>
          <Text $chalBoxInnerText>30일</Text>
        </Box>

        <Box $tripleWidth  $chalDetailBox>
          <BoxTitle $boxTitle>참여자</BoxTitle>
          <Text $chalBoxInnerText>1,100명</Text>
        </Box>

        <Box $tripleWidth  $chalDetailBox>
          <BoxTitle $boxTitle>카테고리</BoxTitle>
          <Text $chalBoxInnerText>공부</Text>
        </Box>
      </Container>

      <Box $fullWidth $chalDetailBox>
        <BoxTitle $boxTitle>미션</BoxTitle>
        <Text $chalBoxInnerText>1알 1커밋 남기기</Text>
      </Box>
      <Box $fullWidth $chalDetailBox>
        <BoxTitle $boxTitle>획득 뱃지</BoxTitle>
        <Text $chalBoxInnerText>뱃지 이미지</Text>
      </Box>
      <Box $fullWidth $chalDetailBox>
        <BoxTitle $boxTitle>랭킹</BoxTitle>
        <Text $chalBoxInnerText>랭킹</Text>
      </Box>


    </Container>
  )
}

export default ChalDetailInfo