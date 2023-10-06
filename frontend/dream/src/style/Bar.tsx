// NavBar, FooterBar
import React from 'react'

import styled, {css} from 'styled-components';

// 타입
interface NavProps {
  children ?: React.ReactNode;

  $day ?: boolean;
  $night ?: boolean;
  $nightFooter ?: boolean;
  $footer ? : boolean;
  $nav ? : boolean;
  $navTitle ? : boolean;
  $navCommentTitle ? : boolean;
  $footerCommentBar ?: boolean;

}

const BarContainer = styled.div<NavProps>`
  width: 100vw;
  height:3rem;
  position: fixed;
  z-index: 100;
  
  // footer
  ${(props) => props.$footer &&
    css`
      left: 0;
      bottom: 0;
      display: flex;
      justify-content: space-around;
      /* position: sticky; */
      `
  }
  // navBar
  ${(props) => props.$nav &&
    css`
      left: 0;
      top: 0;
      display: flex;
      justify-content: space-between;
      align-items: center;

    // 좌측 박스
    & > div {
      display: flex;
      justify-content: center;
      align-items: center;
    }  
    // 로고 사이즈
    & > div > img {
      width:3rem;
      height:3rem;
    }
    `
  }

  // navTitleBar 
  // <NavTitleBar>제목</NavTitleBar> 이 방식으로 사용
  ${(props) => props.$navTitle && 
    css`
      left: 0;
      top: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      
    // 타이틀
    & > div {

    }

    // 아이콘
    & > Button {
      position: absolute;
      right: 0;
  
    }
    `
  }
  // 댓글 제목 바
  ${(props) => props.$navCommentTitle && 
    css`
      background-color: white;
      left: 0;
      top: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      border-bottom: 1px solid gray;
    // 타이틀
    & > div {
      font-size: 1.3rem;
      font-weight: bold;
    }

    // 아이콘
    & > Button {
      position: absolute;
      right: 0;
    }
    `
  }
  // 댓글 하단 바
  ${(props) => props.$footerCommentBar && 
    css`
      height: 4rem;
      left: 0;
      bottom: -1px;
      display: flex;
      align-items: center;
      justify-content: center;
      border-top: 1px solid gray;
      background-color: white;
    // 프로필 사진
    & > img {

    }
    & > div {
  
    }
    & > Button {

    }
    `
  }



  // 낮 모드
  ${(props) => props.$day &&
    css `
      // background-color: rgba(160, 190, 210);
      background-color: #95BAD1;
      `
    }
    // 밤 모드
    ${(props) => props.$night &&
      css `
      // background-color: rgba(157, 142, 196);
      background-color: #585576;
    `
  }
  // 밤 모드 - footer
  ${(props) => props.$nightFooter &&
    css `
    background-color: #9687BE;
  `
  }
`



const Bar = (props:NavProps) => {
  return <BarContainer {...props}>{props.children}</BarContainer>
}

export {Bar};
