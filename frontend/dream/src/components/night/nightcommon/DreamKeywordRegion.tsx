// 옥션디테일, (주인만 볼 수 있는) 개별 꿈 디테일 페이지 키워드 모음

// 리액트
import React from "react";

// 컴포넌트

// 스타일
import { Box, GradeWrappingBox } from "style/Box";
import Container from "style/Container";
import Text from "style/Text";

interface DreamKeywordProps {
  keywords :string[] |undefined
}


const DreamKeywordRegion = ({keywords}:DreamKeywordProps) => {
  return (
    <>
    <Container $baseContainer>
      <Container $nightKeyword>
        {
          Array.isArray(keywords) && keywords.map((keyword, idx)=> (
            <Box $keywordBoxNight key={idx}> 
              <Text $nightKeword $nightWhite>
                {keyword}
              </Text> 
            </Box>
          ))
        }
      </Container>
    </Container>
    </>
  )
}

export default DreamKeywordRegion