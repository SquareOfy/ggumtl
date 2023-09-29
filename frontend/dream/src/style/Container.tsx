import React from 'react'

// 스타일
import styled, {css} from 'styled-components'

interface ContainerProps {
  children?: React.ReactNode;
  $baseContainer ?: boolean
  $centerContainer ?: boolean
  $spaceBetweenContainer ?: boolean
  $columnCenterContainer ?: boolean

  // 5개 넘어가면 한줄 띄어가는 keyword
  $nightKeyword ?: boolean

  $dayCreate ?: boolean
  $certCreate ?: boolean
  $dayBaseContainer ?: boolean
  // 챌린지 디테일 내용 컨테이너
  $chalDetail ?: boolean
  
  // 댓글 모달 컨테이너
  $commentContainer ?: boolean
  $commentListContainer ?: boolean
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
    props.$columnCenterContainer &&
    css`
    
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    `
  }
  
  // div 가운데 spacebetween 버전
  ${(props) =>
    props.$spaceBetweenContainer &&
    css`
      display: flex;
      justify-content: space-between;
      align-content: center;
      /* margin: 1rem auto; */
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

  // 챌린지 생성 컨테이너
  ${(props) =>
    props.$dayCreate &&
    css`
      margin-top: 5rem;
    `
  }
  // 인증글 생성 컨테이너
  ${(props) =>
    props.$certCreate &&
    css`
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      margin-top: 2rem;
    `
  }

  // 챌린지 디테일 컨테이너
  ${(props) =>
    props.$chalDetail &&
    css`
      margin: 1.5rem 0;
      padding:1rem;
      background-color: rgba(249, 249, 249, 0.3);
      border-radius: 1rem;
      display: flex;
      flex-direction: column;
    `
  }

  // 댓글 모달창 컨테이너
  ${(props) =>
    props.$commentContainer &&
    css`
      background-color: white;
      position: fixed;
      overflow: auto;
      top: 0;
      left: 0;
      height: 100vh;
      width: 100vw;
      z-index: 100;
      // 트랜지션으로 올라오게하면 좋지 않을까..?
    `
  }
  // 댓글 내용 리스트 컨테이너
  ${(props) =>
    props.$commentListContainer &&
    css`
      margin-top: 3rem;
      margin-bottom: 4rem;
      // 트랜지션으로 올라오게하면 좋지 않을까..?
    `
  }

`

const Container = (props:ContainerProps) => {
  return <StyledContainer {...props}>{props.children}</StyledContainer>
}

export default Container