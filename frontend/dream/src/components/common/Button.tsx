// 공통 Button 컴포넌트
import React from 'react'

import styled, {css} from "styled-components"

interface ButtonProps {
    children?: React.ReactNode;
    // styles?: string;
    onClick?: () => void;

    disabled?: boolean;    
    type?: 'submit' | 'button' | 'reset';

    // 별개 스타일링
    // 너비
    $fullWidth ?: boolean;
    $halfWidth ?: boolean

    // 폰트 굵게
    $isBold ?: boolean

    // 개별 스타일링
    $kakao ?: boolean;
    $nightVoice ?:boolean;
    $icon ?: boolean;
    $follow ?:boolean

    // 색상 지정
    $nightPurple ?:boolean
    $nightPalePurple ?:boolean
    $dayBlue ?: boolean
    $dayYellow ?: boolean
    $transparent ?: boolean
    $sunsetPurple ?: boolean
    $sunsetPink ?: boolean

}

const StyledButton = styled.button<ButtonProps>`
    width: auto;
    border : none;
    font-size: 1rem;
    padding: 0.5rem 1rem;
    border-radius: 0.75rem;

    // disabled가 아닐때(활성화), hover
    &:not(:disabled):hover {

    }

    // 비활성화
    &:disabled {

    }

    // 꽉 찬 버튼
    ${(props) =>
        props.$fullWidth && 
        css`
            width:100%;
        `
    }
    // 절반만 차지
    ${(props) =>
        props.$halfWidth && 
        css`
            padding: 1.5rem;
            width:45%;
        `
    }

    // 폰트 굵게
    ${(props) =>
    props.$isBold &&
    css`
        font-weight: bold;
    `}

    //icon 전용(X, 종, 각종 아이콘)
    ${(props) => 
        props.$icon &&
        css`
        padding: 0.5rem;
        margin: 0.5rem;
        border: none;
        background-color: transparent;
        `
    }

    // kakao
    ${(props) =>
        props.$kakao &&
        css`
            background-color: #fee500;
            height: 3rem;
            border: 1px solid #fee500;
            border-radius: 0.5rem;
            color: #191919;
            &:not(:disabled):hover {
                background: #ffec3e;
                color: #191919;
            }
        `}

    // 음성 기록
    ${(props) =>
        props.$nightVoice &&
        css`
            background-color: rgb(190, 169, 215, 0.5);
            border: 1px solid rgb(190, 169, 215, 0.5);
            border-radius: 1rem;
            height: 3rem;
            &:not(:disabled):hover {
                background-color: rgba(190, 169, 215, 0.8);
            }
        `
    }

    // 색상 지정
    // 투명한 버튼(배경색과 동일하게)
    ${(props) =>
        props.$transparent &&
        css`
            background-color: transparent;
            border: 1px solid #000000;
        `
    }

    // 짙은 보라색버튼
    ${(props) =>
        props.$nightPurple &&
        css`
            background-color: #6659A5;
            border: 1px solid #6659A5;
            color: #FFFFFF;
            &:not(:disabled):hover {
                opacity: 0.8;
            }
        `   
    }
    // 연한 보라색버튼
    ${(props) =>
        props.$nightPalePurple &&
        css`
            background-color: #BEA9D7;
            border: 1px solid #BEA9D7;
            color: #FFFFFF;
            &:not(:disabled):hover {
                opacity: 0.8;
            }
        `   
    }

    // 짙은 하늘색
    ${(props) =>
        props.$dayBlue &&
        css`
            background-color: #75A8C7;
            border: 1px solid #75A8C7;
            color: #000000;
            &:not(:disabled):hover {
                opacity: 0.8;
            }
        `   
    }
    // 타임캡슐 yellow
    ${(props) =>
        props.$dayYellow &&
        css`
            background-color: #FFE177;
            border: 1px solid #FFE177;
            color: #000000;
            &:not(:disabled):hover {
                opacity: 0.8;
            }
        `   
    }
    // 초기 프로필사진 업로드
    ${(props) =>
        props.$sunsetPurple &&
        css`
            background-color: #C3BED1;
            border: 1px solid #C3BED1;
            color: #000000;
            &:not(:disabled):hover {
                opacity: 0.8;
            }
        `   
    }
    // 초기 다음 버튼 
    ${(props) =>
        props.$sunsetPink &&
        css`
            background-color: #F1E3E6;
            border: 1px solid #F1E3E6;
            color: #000000;
            &:not(:disabled):hover {
                opacity: 0.8;
            }
        `   
    }

    //팔로우
    ${(props) => 
        props.$follow &&
        css`
            font-size: 1rem;
            padding: 0.1rem 1.5rem;
            /* font-weight: 400; */
            border-radius: 1.5rem;
        `
    }

`

const Button = (props:ButtonProps) => {
    return <StyledButton {...props}> {props.children} </StyledButton>
}

export default Button