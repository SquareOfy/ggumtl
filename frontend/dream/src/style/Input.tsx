// 공통 Input 컴포넌트
import React from 'react'

import styled, {css} from "styled-components"

export interface InputProps {
    children?: React.ReactNode;
    // styles?: string;
    onChange ?: (e :any) => void;
    onBlur ?: (e: any) => void
    disabled?: boolean;    
    type?: 'text' | 'search' | 'number';
    placeholder ?: string;
    id ?: string;
    value ?: string | number

    // 별개 스타일링
    // 캡슐
    $capsuleValue ?: boolean;
    
    // 경매 Input
    $biddingValue ?: boolean;

    // 경매 생성 Input
    $auctionInput ?: boolean

    // 일반
    // 챌린지
    $chalCreateInput ?: boolean;
    $chalCategoryValue ?: boolean;
    $chalDateValue ?: boolean;
    $chalTitleValue ?: boolean;
    $chalCreateCategoryInput ?: boolean;

    // thin
    $searchBar ?: boolean;
    $auctionInputBar ?: boolean;
    $nicknameInputBar ?: boolean;
    
    // color
    $nightColor ?: boolean;
    $dayColor ?: boolean;
    $daySearchColor ?: boolean

    // 굵기
    $textInput ?: boolean;
    $thinTextInput ?: boolean;

    // 챌린지 내용 input
    $chalContentInput ? :boolean;
}

const StyledInput = styled.input<InputProps>`   
   outline: none;
   border: none;
   border-radius: 0.75rem;    
   padding-left: 0.75rem;
   // value (입력값이 지정되는 것) => box로 옮기기

    // disabled가 아닐때(활성화), hover
    &:not(:disabled):hover {

    }

    // 비활성화
    &:disabled {

    }

    // 굵기 - 일반
    // input 공통 적용
    ${(props) =>
      props.$textInput && 
      css`
        padding-left: 1rem;
        padding-right: 1rem;
        background-color: rgb(249, 249, 249, 100%);     
      `
    }

    // 굵기 - thin
    ${(props) =>
      props.$thinTextInput && 
      css`
        border-radius: 1rem;
        padding-left: 1rem;
        padding-right: 1rem;        
      `
    }
    
    // capsuleValue
    ${(props) =>
      props.$capsuleValue && 
      css`
        width: 100%;
        height: 3rem;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;
      `
    }

    // biddingValue
    ${(props) =>
      props.$biddingValue && 
      css`
        border-radius: 1rem;
        text-align: right;
        padding-right: 1rem;
      `
    }

    /* auctionInput */
    ${(props)=>
      props.$auctionInput &&
      css`
        display: block;
        border-radius: 1rem;
        padding-left: 1rem;
        height: 2rem;
        /* width: 100%; */
      `
    }

    // chalTitleValue
    ${(props) =>
      props.$chalTitleValue &&
      css`
        width: 70%;
        height: 3.5rem;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;
        color: #374151;
      `
    }


    // chalCategoryValue
    ${(props) =>
      props.$chalCategoryValue &&
      css`
        width: 35%;
        height: 4rem;
        margin: 0.5rem;
      `
    }

    // chalDateValue
    ${(props) =>
      props.$chalDateValue &&
      css`
        width: 35%;
        height: 4rem;
        margin: 0.5rem;
      `
    }

    // chalCreateInputDefault - 제목, 타이틀, 챌린지 기간
    ${(props) =>
      props.$chalCreateInput &&
      css`
        width: 100%;
        height: 3.5rem;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;  
      `
    }
    // 챌린지 생성 input 큰 사이즈
    ${(props) =>
      props.$chalContentInput &&
      css`
        width: 100%;
        height: 7rem;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;  
      `
    }

    // Thin

    // night color
    ${(props) =>
      props.$nightColor &&
      css`
        background-color: rgb(190, 169, 215, 50%);
        color: #F9FAFB;
      `
    }

    // day color
    ${(props) =>
      props.$dayColor &&
      css`
        background-color: rgb(249, 249, 249, 50%);
        color: #374151;
      `
    }

    // day Search color
    ${(props) =>
      props.$daySearchColor &&
      css`
        background-color: rgba(117, 168, 199, 0.8);
        color: #ffffff      
      `
    }
    
    
    // searchBar
    ${(props) =>
      props.$searchBar &&
      css`
        width: 100%;
        height: 3rem;
        margin-top: 1rem;
        margin-bottom: 1rem;
        padding-left: 0.75rem;
        padding-right: 2rem;
        overflow: auto;
        box-sizing: border-box;
      `
    }

    // auctionInputBar - 옥션 생성하기에 사용됨, 옥션 참여하기에서 구매값 입력
    ${(props) =>
      props.$auctionInputBar &&
      css`
        width: 30%;
        height: 2.5rem;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;
      `
    }

    // nicknameInputBar
    ${(props) =>
      props.$nicknameInputBar &&
      css`
        background-color: rgb(241, 227, 230, 50%);
        color: #374151;
        width: 100%;
        height: 3rem;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;
      `
    }
`

const Input = (props:InputProps) => {
    return <StyledInput {...props}></StyledInput>
}


export default Input