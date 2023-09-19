import React from 'react'

// 스타일
import styled, {css} from 'styled-components'

interface WrapProps {
  children?: React.ReactNode;
  $baseWrap ?: boolean
  $auctionCardWrap ?: boolean;

  // 경매장 전용 Box Wrap
  $spaceBetweenWrap ?: boolean
  $biddingPriceWrap ?: boolean

  // 밤 - 버튼 2개 용도
  $nightBotButtonWrap ?: boolean
  $nightButtonCheckWrap ?: boolean

  // 프로필
  $profileHeaderWrap ?: boolean
  
}
const StyledWrap = styled.div<WrapProps>`
  ${(props) =>
    props.$baseWrap &&
    css`
      margin: 0 0.5rem;
    `
  }

  //양 끝단으로 보내기
  ${(props) =>
    props.$spaceBetweenWrap &&
    css`
      display: flex;
      justify-content: space-between;
    `
  }

  // 공개 - 버튼 2개 용 (밤)
  ${(props) =>
    props.$nightBotButtonWrap &&
    css`
      display: grid;
      grid-template-columns: repeat(2, 1fr);
      margin: inherit;
      margin-bottom: 0.5rem;
      
      & > div:nth-child(1) {
        margin: inherit;
      }
      & > div:nth-child(2) {
        display: flex;
        margin: inherit;
        justify-content: right;

        & > button:nth-child(1) {
          margin-right: 0.5rem;
        }
      }
    `
  }

  // 공개 - 버튼 2개 + 체크박스 용 (밤)
  ${(props) =>
    props.$nightButtonCheckWrap &&
    css`
      margin-top: 0.25rem;
      align-items: center;
    `
  }

  ${(props) =>
    props.$auctionCardWrap &&
    css`
      /* padding-top: 1rem; */
      padding-bottom: 1rem;
      /* margin: 0.5rem; */
      height: 90%;
      /* display: flex;
      flex-wrap: wrap;
      justify-content: space-evenly; */
      &:focus {
        outline: 0;
      } 
    `}
  
  // 
  ${(props) =>
    props.$profileHeaderWrap &&
    css`
      padding: 1rem;

      & > div:nth-child(1) {
        display: grid;
        grid-template-columns: 1fr 2fr;
        justify-items: center;
        align-items: center;

        & > img {
          justify-content: end;
        }
        
        & > div {
          padding: 1rem 0;
          width: 100%;
          padding: 1rem;
          display: flex;
          flex-direction: column;
          justify-content: center;
          
          & > div:nth-child(2) {
            display: flex;
            justify-content: space-between;
            /* padding-right: 1rem; */
          }
        } 
      }

      & > div:nth-child(2) {
        margin: 1rem 1rem;
      }
      
    `
  }


`

const Wrap = (props:WrapProps) => {
  return <StyledWrap {...props}>{props.children}</StyledWrap>
}

export default Wrap