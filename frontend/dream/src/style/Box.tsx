// Box, BoxTitle, WrappingBox
import React from 'react'

import styled, {css} from 'styled-components';

interface BoxProps {
  children ?: React.ReactNode;
  onClick ?: (e:any) => void;

  // 사이즈 관련
  $fullWidth ?: boolean

  $wideTextBox ?: boolean;
  $keywordBoxNight ?: boolean;
  $keywordBoxDay ?: boolean;
  $challengeContentBox ?: boolean;
  $mainTitleBox ?: boolean;
  $storyContentsBox ?: boolean;

  // 모드 관련
  $night ?: boolean;
  $day ?: boolean;

  // 박스에 타이틀 관련
  $boxTitle ?: boolean;
}

const StyledBox = styled.div<BoxProps>`

  /* 최대 너비 */
  ${(props) => props.$fullWidth &&
    css`
      width: 100%;
    `
  }


  // 큰 텍스트 박스
  ${(props) => props.$wideTextBox &&
    css`
      padding : 1rem;
      margin: 1rem;
      min-height: 10rem;
      border-radius: 1rem;
    `
  }

  // 큰 텍스트 박스 밤 모드
  ${(props) => props.$night && 
    css`
      background-color: rgba(190, 169, 215, 0.5);
      color: white;
  `}

  // 큰 텍스트 박스 낮 모드
  ${(props) => props.$day && 
    css`
      background-color: rgba(249, 249, 249, 0.5);
      color: black;
  `}

  // 밤 키워드 박스
  ${(props) => props.$keywordBoxNight &&
    css`
      width: 3rem;
      display: inline;
      font-size: 0.5rem;
      padding : 1vw;
      /* margin : 1rem; */
      border-radius: 1rem;
      /* width: 5rem; */
      text-align: center;
      background-color: rgba(190, 169, 215, 0.5);
      color: black;
    `
  }

  // 낮 키워드 박스
  ${(props) => props.$keywordBoxDay &&
    css`
      font-size: 1rem;
      padding : 0.5rem;
      margin : 1rem;
      border-radius: 1rem;
      width: 5rem;
      text-align: center;
      background-color: white;
      color: black;
    `
  }

  // 챌린지 컨텐츠 박스
  ${(props) => props.$challengeContentBox &&
    css`
      /* height: 3rem; */
      display: grid;
      grid-template-columns: 30% 40% 30%;
      justify-items: center;
      align-items: center;
      background-color: rgba(249, 249, 249, 0.5);
      margin: 1rem;
      /* padding: 2.5rem; */
      border-radius : 1.5rem;
      
      & > img {
        width: 3rem;
        height: 3rem;
        border-radius: 50%;
        background-color: red;
        // object fit 적용 -> css 적용
        object-fit: cover;
      }

      & > div {
        /* margin: 0 1rem; */
        padding: 1rem;
        /* justify-items: center; */
        display: inherit;
        align-items: center;
      }
      
      & > div > p {
        margin: 0.5rem 0;
        justify-items: center;
      }
       
      & :nth-child(2) :first-child {
        // 제목 두께 설정
      }
    `
  }

  // 제목 박스 (내 챌린지)
  ${(props) => props.$mainTitleBox &&
    css`
      padding: 0.5rem 2rem;
      margin: 1rem 3rem;
      margin-bottom: 0;
      border-radius: 1rem;
      background-color: rgba(249, 249, 249, 0.5);
      color: black;
      display:flex;
      justify-content:center;
      align-items:center;

      & > img {
        width:2.5rem;
        height:2.5rem;
        border-radius: 50%;
        margin-right: 1rem;
        background-color: red;
        // object fit 적용 -> css 적용
        object-fit: cover;
      }
    `
  }

  // 스토리 컨텐츠 박스
  ${(props) => props.$storyContentsBox &&
    css`
      padding: 1rem;
      margin-bottom: 3rem;
      border-radius: 1rem;
      display:flex;
      justify-content:center;
      align-items:center;
    `
  }
`;



// 텍스트 박스 제목 관련
const StyledTitle = styled.p<BoxProps>`
  
  ${(props) => props.$boxTitle && 
    css`
      font-size : 1rem;
      /* margin : 0; */
      position : relative;
      padding: 0 0.5rem;
      top: -1.6rem;
      font-weight: 800;
    `
  }
`;


// 래핑 시키는 박스
const GradeWrapping = styled.div<BoxProps>`
  // 가운데 간격 줄이기
  display: flex;
  justify-content: space-around;
  padding: 0 3rem;

  & > div > p {
    display :flex;
    justify-content : center;
    align-items : center;

    &:first-child {
      margin: 0px;
      font-size : 3rem;
    }
  }
`



const Box = (props:BoxProps) => {
  return <StyledBox {...props}>{props.children}</StyledBox>
}

const BoxTitle = (props:BoxProps) => {
  return <StyledTitle {...props}>{props.children}</StyledTitle>
}

const GradeWrappingBox = (props:BoxProps) => {
  return <GradeWrapping {...props}>{props.children}</GradeWrapping>
}


export {Box, BoxTitle, GradeWrappingBox};
