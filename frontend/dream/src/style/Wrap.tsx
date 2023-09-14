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