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

`

const Wrap = (props:WrapProps) => {
  return <StyledWrap {...props}>{props.children}</StyledWrap>
}

export default Wrap