import React from 'react'

// 스타일
import styled, {css} from 'styled-components'

interface TextProps {
  children?: React.ReactNode;
  onClick ?: () => void;
  className ?: string;

  $nightKeword ?: boolean;

  // 강조
  $isBold ?: boolean;

  // 마진
  $MBHalf ?: boolean

  // 수직정렬
  $verticalAlign ?: boolean

  // 색상
  $nightWhite ?: boolean;
  $nightBlue ?: boolean;
  $black ?: boolean;
  $danger ?: boolean;
  $nightMoney ?: boolean;
  $dayMoney ?: boolean;
  $dayWhite ?: boolean;

  //인풋 에러 메세지
  $wrongMessage ?: boolean;
  $successMessage ?: boolean;

  //챌린지 디테일 정보 박스 내부
  $chalBoxInnerText ?: boolean;

}
const StyledText = styled.div<TextProps>`

  ${(props) =>
    props.$nightKeword &&
    css`
      font-size: 0.75rem;
    `
  }
  ${(props) =>
    props.$isBold &&
    css`
      font-weight: 700;
    `
  }
  ${(props) =>
    props.$MBHalf &&
    css`
      margin-bottom: 0.5rem;
    `
  }

  // 입력 오류 경고 메세지
  ${(props) => 
    props.$wrongMessage &&
    css`
      font-size: 0.7rem;
      padding: 0.5rem;
      color: #dc3545;
    `
  }
  ${(props) => 
    props.$successMessage &&
    css`
      font-size: 0.7rem;
      padding: 0.5rem;
      color: #198754;
    `
  }
  /* 수직정렬 */
  ${(props) => 
    props.$verticalAlign &&
    css`
      display: flex;
      flex-direction: column;
      justify-content: center;
    `
  }
  ${(props) =>
    props.$nightWhite &&
    css`
      color: #F2F2F0;
    `
  }

  ${(props) =>
    props.$nightBlue &&
    css`
      color: #1F4078;
    `
  }
  ${(props) =>
    props.$black &&
    css`
      color: black;
    `
  }
  ${(props) =>
    props.$danger &&
    css`
      color: #C70000;
    `
  }
  ${(props) =>
    props.$nightMoney &&
    css`
      color: #a5a5a5;
    `
  }
  ${(props) =>
    props.$dayMoney &&
    css`
      color: #424242;
    `
  }
  ${(props) =>
    props.$dayWhite &&
    css`
      color: #e8e8e8;
      line-height: 1.5rem;
      font-size: 1rem;
      margin-top: 8rem;
    `
  }

  ${(props) => 
    props.$chalBoxInnerText && 
    css`
      line-height: 3.5rem;
    `
  }

`

const Text = (props:TextProps) => {
  return <StyledText {...props}>{props.children}</StyledText>
}

export default Text