// 공통 Image 컴포넌트
import React from 'react'

import styled, { css } from 'styled-components'

interface ImageProps {
  children?: React.ReactNode
  // styles?: string;
  onClick?: () => void
  disabled?: boolean
  src?: string

  // 별개 스타일링
  // 프로필
  // $circleImage ?: boolean;
  $smallProfileImage?: boolean
  $tinyProfileImage?: boolean
  $badge?: boolean
  $largeBadge?: boolean
  $recommendBadge?: boolean
  $timeCapsuleImage?: boolean
  $timeCapsuleLoading?: boolean

  // 뱃지 색
  $gold?: boolean
  $silver?: boolean
  $bronze?: boolean
  $default?: boolean

  // 메인 이미지
  $mainImage?: boolean
  $SunsetLogo?: boolean

  // 밤 꿈 카드
  $nightImageBorder?: boolean
  $dayImageBorder?: boolean
  $profileCard?: boolean
  $auctionCard?: boolean

  $signupImage?: boolean
  $certImage?: boolean
}

const StyledImage = styled.div<ImageProps>`
  cursor: pointer;
  /* background-color: gray; */
  border-radius: 50%;

  // disabled가 아닐때(활성화), hover
  &:not(:disabled):hover {
  }

  // 비활성화
  &:disabled {
  }

  // 작은 프로필 이미지
  ${(props) =>
    props.$smallProfileImage &&
    css`
      width: 4rem;
      height: 4rem;
      margin: 1rem;
      background-color: transparent;
      & > img {
        width: 100%;
        object-fit: cover;
        aspect-ratio: 1/1;
        object-position: center;
        border-radius: 50%;
      }
    `}

  // 매우 작은 프로필 이미지
    ${(props) =>
    props.$tinyProfileImage &&
    css`
      width: 2.5rem;
      height: 2.5rem;
      margin: 1rem;
      background-color: red;
      & > img {
        width: 100%;
        object-fit: cover;
        aspect-ratio: 1/1;
        object-position: center;
        border-radius: 50%;
      }
    `}

    // 댓글 프로필 이미지
    ${(props) =>
    props.$tinyProfileImage &&
    css`
      width: 2.5rem;
      height: 2.5rem;
      margin: 0.7rem 0.5rem;
      background-color: blue;
      & > img {
        width: 100%;
        object-fit: cover;
        aspect-ratio: 1/1;
        object-position: center;
        border-radius: 50%;
      }
    `}

    // badge
    ${(props) =>
    props.$badge &&
    css`
      width: 5rem;
      height: 5rem;
      margin: 0.5rem;
      /* border: 0.5rem solid black; */
      & > img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        content: '';
        aspect-ratio: 1/1;
        object-position: center;
        border-radius: 1rem;
      }
    `}

    // largeBadge
    ${(props) =>
    props.$largeBadge &&
    css`
      width: 14rem;
      height: 14rem;
      margin: 1rem;
      border: 0.5rem solid black;
      cursor: default;
    `}

    // 추천챌린지 뱃지
    ${(props) =>
    props.$recommendBadge &&
    css`
      border: none;

      & > img {
        border: 0.5rem solid black;
      }
    `}

    // 뱃지 색 - gold
    ${(props) =>
    props.$gold &&
    css`
      border: 2rem solid #b68a16;
      text-align: center;
    `}

    // 뱃지 색 - silver
    ${(props) =>
    props.$silver &&
    css`
      border: 2rem solid #8c8c8c;
      text-align: center;
    `}

    // 뱃지 색 - bronze
    ${(props) =>
    props.$bronze &&
    css`
      border: 2rem solid #6b4e43;
      text-align: center;
    `}

    // 뱃지 색 - default
    ${(props) =>
    props.$default &&
    css`
      border: 2rem solid black;
      text-align: center;
    `}


    // mainImage 공통 스타일
    ${(props) =>
    props.$mainImage &&
    css`
      width: 100%;
      aspect-ratio: 1/1;
      /* background-color: #656565; */
      box-sizing: border-box;
      position: relative;
      overflow: hidden;

      & > img {
        // 정사각형 만들기
        width: 100%;
        /* height: 100%; */
        object-fit: cover;
        aspect-ratio: 1/1;
        object-position: center;
        border-radius: 1rem;
      }
    `}


    // nightImageBorder - 테두리 둥글게
    ${(props) =>
    props.$nightImageBorder &&
    css`
      border-radius: 1rem;
    `}
    // day image는 직각
    ${(props) =>
    props.$dayImageBorder &&
    css`
      border-radius: 0rem;
    `}

    // profileCard - 밤 프로필 카드탭
    ${(props) =>
    props.$profileCard &&
    css`
      margin-left: 0.5rem;
      margin-bottom: 0.5rem;
      background-color: transparent;

      & > img {
        // 정사각형 만들기
        width: 100%;
        /* height: 100%; */
        object-fit: cover;
        aspect-ratio: 1/1;
        object-position: center;
        border-radius: 1rem;
      }
    `}

    // auctionCard - 두 개 있는 밤 꿈 카드
    ${(props) =>
    props.$auctionCard &&
    css`
      /* width: 10rem;
        height: 10rem; */
      width: 100%;
      height: 100%;
      /* margin: 0.5rem 1rem 1rem; */
      margin: 0;
      padding: 0 1rem;
      background-color: inherit;
      border-radius: 0;
      & > img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        content: '';
        aspect-ratio: 1/1;
        object-position: center;
        border-radius: 1rem;
      }
    `}

${(props) =>
    props.$signupImage &&
    css`
      width: 100%;
      aspect-ratio: 1/1;
      background-size: cover;
      border-radius: 1rem;
      margin: 2rem 0;
      background-color: #666666;

      & > img {
        // 정사각형 만들기
        width: 100%;
        height: 100%;
        object-fit: cover;
        aspect-ratio: 1/1;
        object-position: center;
        border-radius: 1rem;
        object-fit: cover;
      }
    `}

// 타임 캡슐 이미지
${(props) =>
    props.$timeCapsuleImage &&
    css`
      width: 8rem;
      aspect-ratio: 1/1;
      background-size: cover;
      border-radius: 1rem;
      background-color: none;
      margin-top: 2rem;
      & > img {
        // 정사각형 만들기
        width: 100%;
        height: 100%;
        object-fit: cover;
        aspect-ratio: 1/1;
        object-position: center;
        border-radius: 1rem;
        object-fit: cover;
      }
    `}
// 타임 캡슐 로딩
${(props) =>
    props.$timeCapsuleLoading &&
    css`
      width: 100vw;
      height: 100vh;
      background-size: cover;
      border-radius: 1rem;
      background-color: none;
      margin-top: 2rem;
      & > img {
        // 정사각형 만들기
        width: 100%;
        object-fit: cover;
        object-position: center;
        border-radius: 1rem;
        object-fit: cover;
      }
    `}

${(props) =>
    props.$certImage &&
    css`
      display: flex;
      justify-content: center;
      align-items: center;
      width: 100%;
      aspect-ratio: 1/1;
      background-size: cover;
      border-radius: 1rem;
      background-color: #666666;

      & > svg {
        font-size: 5rem;
        color: white;
      }

      & > img {
        // 정사각형 만들기
        width: 100%;
        height: 100%;
        object-fit: cover;
        aspect-ratio: 1/1;
        object-position: center;
        border-radius: 1rem;
        object-fit: cover;
      }
    `}

// 타임 캡슐 이미지
${(props) =>
    props.$SunsetLogo &&
    css`
      width: 15rem;
      aspect-ratio: 1/1;
      background-size: cover;
      border-radius: 1rem;
      background-color: none;
      margin-top: 2rem;
      & > img {
        // 정사각형 만들기
        width: 100%;
        height: 100%;
        object-fit: cover;
        aspect-ratio: 1/1;
        object-position: center;
        border-radius: 1rem;
        object-fit: cover;
      }
    `}
`

const Image = (props: ImageProps) => {
  return <StyledImage {...props}>{props.children}</StyledImage>
}

export default Image
