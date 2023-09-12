// 공통 Image 컴포넌트
import React from 'react'

import styled, {css} from "styled-components"

interface ImageProps {
    children?: React.ReactNode;
    // styles?: string;
    onClick?: () => void;
    disabled?: boolean;    
    src ?: string;

    // 별개 스타일링
    // 프로필
    $circleImage ?: boolean;
    $smallProfileImage ?: boolean;
    $badge ?: boolean;
    $largeBadge ?: boolean;

    // 뱃지 색
    $gold ?: boolean;
    $silver ?: boolean;
    $bronze ?: boolean;
    $default ?: boolean;


    // 메인 이미지
    $mainImage ?: boolean;

    // 밤 꿈 카드
    $nightImageBorder ?: boolean;
    $profileCard ?: boolean;
    $auctionCard ?: boolean;


}

const StyledImage = styled.div<ImageProps>`
    cursor: pointer;
    background-color: gray;
    border-radius: 50%;
    object-fit: cover;
    
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
      `
    }

    // badge
    ${(props) =>
      props.$badge &&
      css`
        width: 6rem;
        height: 6rem;
        margin: 1rem;
        /* border: 0.5rem solid black; */
      `
    }
    
    // largeBadge
    ${(props) =>
      props.$largeBadge &&
      css`
        width: 14rem;
        height: 14rem;
        margin: 1rem;
        border: 0.5rem solid black;
        cursor: default;
      `
    }
    
    // 뱃지 색 - gold
    ${(props) =>
        props.$gold && 
        css`
          border: 2rem solid #B68A16;
          text-align: center;
        `
    }

    // 뱃지 색 - silver
    ${(props) =>
        props.$silver && 
        css`
          border: 2rem solid #8C8C8C;
          text-align: center;
        `
    }

    // 뱃지 색 - bronze
    ${(props) =>
        props.$bronze && 
        css`
          border: 2rem solid #6B4E43;
          text-align: center;
        `
    }

    // 뱃지 색 - default
    ${(props) =>
        props.$default && 
        css`
          border: 2rem solid black;
          text-align: center;
        `
    }


    // mainImage 공통 스타일
    ${(props) =>
      props.$mainImage &&
      css`
        width: 100%;
        background-color: #656565;
        &:after { // 정사각형 만들기
          display: block;
          padding-bottom: 100%;
          content: "";
          cursor: pointer;
        }
      `
    }
    

    // nightImageBorder - 테두리 둥글게
    ${(props) =>
      props.$nightImageBorder &&
      css`
        border-radius: 10px;  
      `
    }

    // profileCard - 밤 프로필 카드탭
    ${(props) =>
      props.$profileCard &&
      css`
        width: 7rem;
        height: 7rem;
        margin: 1rem;
      `
    }

    // auctionCard - 두 개 있는 밤 꿈 카드
    ${(props) =>
      props.$auctionCard &&
      css`
        width: 10rem;
        height: 10rem;
        margin: 1rem;
      `
    }


`

const Image = (props:ImageProps) => {
    return <StyledImage {...props}>{props.children}</StyledImage>
}

export default Image