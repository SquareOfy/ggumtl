// 공통 Button 컴포넌트
import React from 'react'

import styled, { css } from 'styled-components'

interface ButtonProps {
  children?: React.ReactNode
  // styles?: string;
  onClick?: () => void
  style?: {}
  disabled?: boolean
  type?: 'submit' | 'button' | 'reset'
  className?: string

  // 별개 스타일링
  // 너비
  $fullWidth?: boolean
  $halfWidth?: boolean

  $biddingBtn?: boolean

  // 폰트 굵게
  $isBold?: boolean

  // 개별 스타일링
  $kakao?: boolean
  $nightVoice?: boolean
  $isRecording?: boolean
  $icon?: boolean
  $follow?: boolean
  $halfWidthImeBuy?: boolean
  $dayCreate?: boolean
  $moreButton?: boolean
  $category?: boolean
  $chalCertButton?: boolean
  $goHomeButton?: boolean
  $isSelected?: boolean
  $addCommentButton?: boolean
  $nightSearchModalXButton?: boolean
  $dreamDetail?: boolean;

  // 색상 지정
  $nightPurple?: boolean
  $nightPalePurple?: boolean
  $nightPalePurpleSelected?: boolean
  $nightMiddlePurple?: boolean
  $dayBlue?: boolean
  $dayGrey?: boolean
  $dayYellow?: boolean
  $transparent?: boolean
  $sunsetPurple?: boolean
  $sunsetPink?: boolean
}

const StyledButton = styled.button<ButtonProps>`
  width: auto;
  border: none;
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
      width: 100%;
    `}
  // 절반만 차지
    ${(props) =>
    props.$halfWidth &&
    css`
      padding: 0.5rem;
      width: 49%;
      height: 15vw;
    `}
    // 절반만 차지 & 즉시구매
    ${(props) =>
    props.$halfWidthImeBuy &&
    css`
      background-color: rgba(190, 169, 215, 0.5);
      color: black;
      padding: 0.5rem;
      width: 49%;
      height: 15vw;
      &:active {
        opacity: 0.5;
      }
    `}

    // 폰트 굵게
    ${(props) =>
    props.$isBold &&
    css`
      font-weight: bold;
    `}

    // 경매 - 참여 버튼 전용
    ${(props) =>
    props.$biddingBtn &&
    css`
      border-radius: 1rem;
    `}

    //icon 전용(X, 종, 각종 아이콘)
    ${(props) =>
    props.$icon &&
    css`
      line-height: 1.5rem;
      font-size: 1.5rem;
      padding: 0.5rem;
      padding-top: 0.7rem;
      margin: 0.5rem;
      border: none;
      background-color: transparent;
    `}

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
      height: 5rem;
      &:not(:disabled):active {
        background-color: rgba(190, 169, 215);
      }
    `}

    // 음성 기록 중일 때
    ${(props) =>
    props.$isRecording &&
    css`
      background-color: rgba(190, 169, 215, 0.8);
    `}
    /* 꿈 주인만 조회 */
    ${(props) =>
    props.$dreamDetail &&
    css`
      margin: 0.25rem 0 1.5rem;
    `}

    


    // 색상 지정
    // 투명한 버튼(배경색과 동일하게)
    ${(props) =>
    props.$transparent &&
    css`
      background-color: transparent;
      border: 1px solid #000000;
    `}

    // 짙은 보라색버튼
    ${(props) =>
    props.$nightPurple &&
    css`
      background-color: #6659a5;
      // border: 1px solid #6659A5;
      color: #ffffff;
      padding: 0.75rem 1rem;
      &:not(:disabled):active {
        opacity: 0.8;
      }
    `}

    // 중간 보라색버튼
    ${(props) =>
    props.$nightMiddlePurple &&
    css`
      background: rgba(102, 89, 165, 0.5);
      box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.2);
      // border: 1px solid rgba(102, 89, 165, 0.5);
      padding: 0.75rem 1rem;
      color: #ffffff;
      &:not(:disabled):active {
        opacity: 0.8;
      }
    `}

    // 연한 보라색버튼
    ${(props) =>
    props.$nightPalePurple &&
    css`
      background-color: #bea9d7;
      // border: 1px solid #BEA9D7;
      color: #ffffff;
      // &:not(:disabled):hover {
      //     opacity: 0.7;
      // }
      &:not(:disabled):active {
        opacity: 0.5;
      }
    `}

    // 연한 보라색버튼 선택
    ${(props) =>
    props.$nightPalePurpleSelected &&
    css`
      background-color: #bea9d7;
      opacity: 0.8;
    `}

    // 짙은 하늘색
    ${(props) =>
    props.$dayBlue &&
    css`
      background-color: #75a8c7;
      border: 1px solid #75a8c7;
      color: #000000;
      // &:not(:disabled):hover {
      //     opacity: 0.8;
      // }
      &:not(:disabled):active {
        opacity: 0.8;
      }
    `}
    // 짙은 하늘색
    ${(props) =>
    props.$dayGrey &&
    css`
      background-color: rgb(164, 164, 164, 0.8);
      border: 1px solid rgb(164, 164, 164, 0.8);
      color: #000000;
      &:not(:disabled):hover {
        opacity: 0.8;
      }
    `}

    // 타임캡슐 yellow
    ${(props) =>
    props.$dayYellow &&
    css`
      background-color: #ffe177;
      border: 1px solid #ffe177;
      color: #000000;
      &:not(:disabled):hover {
        opacity: 0.8;
      }
    `}
    // 초기 프로필사진 업로드
    ${(props) =>
    props.$sunsetPurple &&
    css`
      background-color: #c3bed1;
      border: 1px solid #c3bed1;
      color: #000000;
      &:not(:disabled):hover {
        opacity: 0.8;
      }
    `}
    // 초기 다음 버튼
    ${(props) =>
    props.$sunsetPink &&
    css`
      background-color: #f1e3e6;
      border: 1px solid #f1e3e6;
      color: #000000;
      box-shadow: 0px 4px 20px 0px rgba(0, 0, 0, 0.2);
      &:not(:disabled):active {
        opacity: 0.8;
      }
    `}

    //팔로우
    ${(props) =>
    props.$follow &&
    css`
      font-size: 0.75rem;
      padding: 0 0.8rem;
      /* font-weight: 400; */
      border-radius: 0.5rem;
      height: 1.5rem;
      margin-left: 0.2rem;

      &:not(:disabled):active {
        opacity: 0.5;
      }
    `}

    // 낮 생성 버튼
    ${(props) =>
    props.$dayCreate &&
    css`
      margin-top: 3rem;
    `}
    // 404 정상화 버튼
    ${(props) =>
    props.$goHomeButton &&
    css`
      background-color: #1c3658;
      color: aliceblue;
      font-size: 1.5rem;
      padding: 1rem 2rem;
    `}

    ${(props) =>
    props.$moreButton &&
    css`
      margin-top: 0.5rem;
      padding: 0;
      background-color: transparent;
      & > p {
        margin: 0;
        font-size: 0.7rem;
      }
    `}

    // 낮 카테고리 버튼
    ${(props) =>
    props.$category &&
    css`
      margin: 0.1rem;
      border: 1px solid #d0d7df;
      // &:not(:disabled):hover {
      //     background-color: #A0BED2;
      // }
      &:not(:disabled):active {
        background-color: #75a8c7;
      }
    `}

    // 낮 카테고리 선택 버튼
    ${(props) =>
    props.$isSelected &&
    css`
      background-color: #a0bed2;
    `}

    // 짙은 하늘색
    ${(props) =>
    props.$chalCertButton &&
    css`
      display: flex;
      justify-content: center;
      align-items: center;
      margin-top: 1.5rem;
      border-radius: 8vw;
      & > img {
        position: absolute;
        left: 4rem;
        width: 3rem;
        height: 3rem;
      }
    `}

     // 댓글 게시 버튼
    ${(props) =>
    props.$addCommentButton &&
    css`
      position: absolute;
      right: 1rem;
      height: 2rem;
      color: blue;
      font-weight: bold;
      background-color: transparent;
      border-radius: 2rem;
      &:active {
        background-color: rgba(184, 184, 184, 0.5);
      }
    `}
     // 댓글 게시 버튼
    ${(props) =>
    props.$nightSearchModalXButton &&
    css`
      position: fixed;
      right: 0;
      top: 0;
      color: white;
    `}
`

const Button = (props: ButtonProps) => {
  return <StyledButton {...props}> {props.children} </StyledButton>
}

export default Button
