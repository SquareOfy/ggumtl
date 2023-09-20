import React from 'react'

// 스타일
import styled, {css} from 'styled-components'

interface ContainerProps {
  children?: React.ReactNode;
  $baseContainer ?: boolean
  $centerContainer ?: boolean

  // 5개 넘어가면 한줄 띄어가는 keyword
  $nightKeyword ?: boolean

  $dayCreate ?: boolean
  $dayBaseContainer ?: boolean
  
}
const StyledContainer = styled.div<ContainerProps>`

  ${(props) =>
    props.$baseContainer &&
    css`
      margin: 0.5rem 0.5rem 0;
    `
  }
  ${(props) =>
    props.$dayBaseContainer &&
    css`
      margin: 1rem 1rem 0;
    `
  }

  // div 가운데 넣기
  ${(props) =>
    props.$centerContainer &&
    css`
      display: flex;
      justify-content: center;
      align-content: center;
      margin: 1rem auto;
    `
  }

  ${(props) =>
    props.$nightKeyword &&
    css`
      display: grid;
      /* gap: 0.5rem; */
      grid-template-columns: repeat(5, 20%);
      grid-row-gap: 0.5rem;
      width: 100%;
      margin-top: 0.5rem;
      & > div {
        margin: auto;
        width: 3.5rem;
        border-radius: 0.5rem;
      }
    `
  }

  ${(props) =>
    props.$dayCreate &&
    css`
      margin-top: 5rem;
    `
  }
`

const Container = (props:ContainerProps) => {
  return <StyledContainer {...props}>{props.children}</StyledContainer>
}

export default Container