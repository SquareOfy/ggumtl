// NavBar, FooterBar
import React from 'react'

import styled, {css} from 'styled-components';

// 타입
interface NavProps {
  children ?: React.ReactNode;

  $day ?: boolean;
  $night ?: boolean;
  $footer ? : boolean;
  $nav ? : boolean;
}

const BarContainer = styled.div<NavProps>`
  width: 100vw;
  height:3rem;
  position: fixed;
  
  // footer
  ${(props) => props.$footer &&
    css`
      left: 0;
      bottom: 0;
      display: flex;
      justify-content: space-around;
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
    `
  }
  // 낮 모드
  ${(props) => props.$day &&
    css `
      background-color: rgba(160, 190, 210, 0.5);
    `
  }
  // 밤 모드
  ${(props) => props.$night &&
    css `
      background-color: rgba(157, 142, 196, 0.5);
    `
  }
`



const Bar = (props:NavProps) => {
  return <BarContainer {...props}>{props.children}</BarContainer>
}

export {Bar};
