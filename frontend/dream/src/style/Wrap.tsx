import React from 'react'

// 스타일
import styled, {css} from 'styled-components'

interface WrapProps {
  children?: React.ReactNode;
  $auctionCardWrap ?: boolean

  
}
const StyledWrap = styled.div<WrapProps>`
  
  ${(props) =>
    props.$auctionCardWrap &&
    css`
      padding-top: 1rem;
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