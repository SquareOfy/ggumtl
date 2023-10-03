// Box, BoxTitle, WrappingBox
import React from 'react'

import styled, {css} from 'styled-components';

interface BoxProps {
  children ?: React.ReactNode;
  onClick ?: (e:any) => void;
  style ?:any;

  // 사이즈 관련
  $fullWidth ?: boolean
  $doubleWidth ?: boolean
  $tripleWidth ?: boolean

  $wideTextBox ?: boolean;
  $keywordBoxNight ?: boolean;
  $keywordBoxDay ?: boolean;
  $challengeContentBox ?: boolean;
  $mainTitleBox ?: boolean;
  $storyContentsBox ?: boolean;
  $chalDetailBox ?: boolean;  // 챌린지 정보 박스
  $chalManageBox ?: boolean;  
  $timeCapsuleContentBox ?: boolean;
  $tiemCapsuleChalTitleBox ?: boolean;
  $recommendChalBox ?: boolean;

  // 모드 관련
  $night ?: boolean;
  $day ?: boolean;

  // 박스에 타이틀 관련
  $boxTitle ?: boolean;

  $progressBox ?: boolean;
}

const StyledBox = styled.div<BoxProps>`

  /* 최대 너비 */
  ${(props) => props.$fullWidth &&
    css`
      width: 100%;
    `
  }
  ${(props) => props.$doubleWidth &&
    css`
      width: 48%;
    `
  }



  ${(props) => props.$tripleWidth &&
    css`
      width: 30%;
    `
  }


  // 큰 텍스트 박스
  ${(props) => props.$wideTextBox &&
    css`
      padding : 1rem;
      margin: 0.5rem 0;
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
      margin: 1rem 2rem;
      margin-bottom: 1.5rem;
      border-radius: 1rem;
      background-color: rgba(249, 249, 249, 0.5);
      color: black;
      display:flex;
      justify-content:center;
      align-items:center;

      & > img {
        width:1.5rem;
        height:1.5rem;
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
      justify-content:start;
      align-items:center;
    `
  }

  // 챌린지 디테일 박스
  ${(props) => props.$chalDetailBox && 
   css`
    padding : 1rem;
    margin: 0.7rem 0;
    /* max-height: 5rem; */
    border-radius: 1rem;
    background-color: rgba(249, 249, 249, 0.7);
    // 두번쨰 요소인 내용만 가운데 정렬

    & :nth-child(2) {
      margin: auto;
      text-align: center;
    }
    `
  }  
  // 챌린지 관리 박스
  ${(props) => props.$chalManageBox && 
   css`
    padding : 1rem;
    margin: 0.7rem 0;
    max-height: 5rem;
    border-radius: 2rem;
    background-color: rgba(249, 249, 249, 0.5);
    // 두번쨰 요소인 내용만 가운데 정렬

    & :nth-child(2) {
      margin: 0;
      text-align: center;
    }
    `
  }  
  // progress Bar
  ${(props) => props.$progressBox && 
    css`
      margin: 0.7rem 0;
      & :nth-child(2) {
        padding-left: 1rem;
      }
      & :nth-child(3) {
        text-align: center;
        position: relative;
        top: -1rem;
      }
    `
  }
  
  
// 타임캡슐 타이틀 박스
${(props) => props.$tiemCapsuleChalTitleBox &&
  css`
    padding: 1rem;
    margin: 1.5rem 0;
    width: 70vw;
    text-align: center;
    border-radius: 2rem;
    background-color: #FFE177;
    font-weight: bold;
    font-size: 1.3rem;
  `
}

// 타임캡슐 내용 박스
${(props) => props.$timeCapsuleContentBox &&
  css`
    padding: 1rem;
    margin: 0.5rem 0;
    width: 100%;
    text-align: center;
    border-radius: 1rem;
    background-color: rgb(249, 249, 249, 50%); 
  `
}
// 챌린지 추천 박스
${(props) => props.$recommendChalBox &&
  css`
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    margin: auto;
    margin-top: 4rem;
    width: 18rem;
    height: 50vh;
    border: 1px solid black;
    border-radius: 1rem;
    background-color: rgb(249, 249, 249, 50%); 
  `
}



`;



// 텍스트 박스 제목 관련
const StyledTitle = styled.div<BoxProps>`
  
  ${(props) => props.$boxTitle && 
    css`
      font-size : 0.9rem;
      position : relative;
      height:0; // relative가 그전 것에 영향을 안주기 위해 사용
      padding: 0;
      top: -1.5rem;
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
