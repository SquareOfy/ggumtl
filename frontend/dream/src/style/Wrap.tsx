import React from 'react'

// 스타일
import styled, {css} from 'styled-components'

interface WrapProps {
  children?: React.ReactNode;
  $baseWrap ?: boolean
  $auctionCardWrap ?: boolean;

  // 경매장 전용 Box Wrap
  $spaceBetweenWrap ?: boolean
  $biddingPriceWrap ?: boolean

  // 밤 - 버튼 2개 용도
  $nightBotButtonWrap ?: boolean
  $nightButtonCheckWrap ?: boolean

  // 프로필
  $profileHeaderWrap ?: boolean
  
  // 스토리
  $storyWrap ?: boolean
  
}
const StyledWrap = styled.div<WrapProps>`
  ${(props) =>
    props.$baseWrap &&
    css`
      margin: 0 0.5rem;
    `
  }

  //양 끝단으로 보내기
  ${(props) =>
    props.$spaceBetweenWrap &&
    css`
      display: flex;
      justify-content: space-between;
    `
  }

  // 공개 - 버튼 2개 용 (밤)
  ${(props) =>
    props.$nightBotButtonWrap &&
    css`
      display: grid;
      grid-template-columns: repeat(2, 1fr);
      margin: inherit;
      margin-bottom: 0.5rem;
      
      & > div:nth-child(1) {
        margin: inherit;
      }
      & > div:nth-child(2) {
        display: flex;
        margin: inherit;
        justify-content: right;

        & > button:nth-child(1) {
          margin-right: 0.5rem;
        }
      }
    `
  }

  // 공개 - 버튼 2개 + 체크박스 용 (밤)
  ${(props) =>
    props.$nightButtonCheckWrap &&
    css`
      margin-top: 0.25rem;
      align-items: center;
    `
  }

  ${(props) =>
    props.$auctionCardWrap &&
    css`
      /* padding-top: 1rem; */
      padding-bottom: 1rem;
      /* margin: 0.5rem; */
      height: 90%;
      /* display: flex;
      flex-wrap: wrap;
      justify-content: space-evenly; */
      &:focus {
        outline: 0;
      } 
    `}
  
  // 
  ${(props) =>
    props.$profileHeaderWrap &&
    css`
      padding: 0 1rem;

      /* 꿈틀도 윗부분 */
      & > div:nth-child(1) {
        display: grid;
        grid-template-columns: 1fr 2fr;
        justify-items: center;
        align-items: center;

        /* 프로필 사진 */
        & > img {
          justify-content: end;
        }
        
        /* 닉네임 + 팔로잉 팔로워 부분 */
        & > div {
          width: 100%;
          padding: 1rem;
          display: flex;
          flex-direction: column;
          justify-content: center;

          /* 닉네임 + 버튼 부분 */
          & > div:nth-child(1) {
            display: grid;
            grid-template-columns: 4fr 2fr;
            align-items: center;

            & > div:nth-child(2){
              align-items: center;
              display: flex;
              justify-content: end;
              text-align: right;
              font-size: 0.7rem;
            }
          }
          
          /* 팔로잉 + 팔로워 부분 세 개 */
          & > div:nth-child(2) {
            display: flex;
            justify-content: space-between;
            
            /* 팔로잉 부분 한 개 */
            & > div {
              display: flex;
              flex-direction: column;
              align-items: center;

              /* 팔로잉 부분 단어 하나하나 */
              & > p {
                margin: 0.5rem 0;
              }
            }
          }
        } 
      }      

      /* 꿈틀도 + 별 */
      & > div:nth-child(2) {
        display: grid;
        grid-template-columns: 9fr 1fr;
        align-items: center;
        margin-bottom: 1rem;

        /* 별 컨테이너 */
        & > span {
          width: 2.5rem;
          height: 1.5rem;
          color: #F9F9F9;          
        }
      }
    `
  }
  // 스토리
  ${(props) =>
    props.$storyWrap &&
    css`
      width: 100%;
      height: 100%;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: space-between;
      /* padding:  0 0.5rem; */
      position: fixed;
      top: 0;
      left: 0;
      overflow: scroll;
      background-image: url("/image/background-image/day-background.jpg");
      background-size: cover;
      z-index: 300;

      & > .story {
        width: 100%;
        height: 100%;
        padding: 0 0.5rem;

        /* 터치 이벤트 오른쪽 영역 */
        & > .storyRight {
          position: absolute;
          width: 50%;
          height: 90%;
          bottom: 0;
          right: 0;
          /* background-color: aliceblue; */
          z-index: 999;
        }

        /* 터치 이벤트 왼쪽 영역 */
        & > .storyLeft {
          position: absolute;
          width: 50%;
          height: 90%;
          bottom: 0;
          left: 0;
          /* background-color: aliceblue; */
          z-index: 999;
        }

        /* 상단바 */
        & > .storyBarBox {
          height: 0.5rem;
          width: 98%;
          margin: 1rem auto;
          display: flex;
          border-radius: 1rem;
          align-items: center;
        }

        /* contents 부분 */
        & > div:nth-child(3) > div:nth-child(2) > .contents {
          
          & > img {
            width: 100%;
            height: 100%;
            margin: 1rem 0;
            padding: 0.5rem;
            border-radius: 1rem;
            object-fit: cover;
            aspect-ratio: 1/1;
          }
        }
      }
    `
  }



`

const Wrap = (props:WrapProps) => {
  return <StyledWrap {...props}>{props.children}</StyledWrap>
}

export default Wrap