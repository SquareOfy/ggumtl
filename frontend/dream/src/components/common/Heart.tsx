
// 리액트
import React, {useState} from "react";
import { useLocation } from "react-router-dom";

// 컴포넌트

// 스타일
import "./Heart.css"
import styled from "styled-components";
import Text from "style/Text";
import Container from "style/Container";

const HeartWrap = styled.div`
  display: flex;
  justify-content: start;
  gap: 0.5rem;
  margin: inherit;
`

interface HeartProps {
  isLike ?: boolean;
  likedNumber ?: number; // 좋아요 숫자
}

const Heart = (props: HeartProps) => {
  const location = useLocation()
  const [isLike, setIsLike] = useState(props.isLike)
  const [likeCount, setLikeCount] = useState(props.likedNumber)

  const textColor = () => {
  }

  return (
    <>
    <Container $baseContainer>
      <HeartWrap>
        {/* 좋아요 여부에 따른 색상 다르게 만들기 필요 */}
        <div title="Like" className="heart-container">
                <input id="Give-It-An-Id" className="checkbox" type="checkbox" />
                <div className="svg-container">
                    <svg xmlns="http://www.w3.org/2000/svg" className="svg-outline" viewBox="0 0 24 24">
                        <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z">
                        </path>
                    </svg>
                    <svg xmlns="http://www.w3.org/2000/svg" className="svg-filled" viewBox="0 0 24 24">
                        <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z">
                        </path>
                    </svg>
                    <svg xmlns="http://www.w3.org/2000/svg" height="100" width="100" className="svg-celebrate">
                        <polygon points="10,10 20,20"></polygon>
                        <polygon points="10,50 20,50"></polygon>
                        <polygon points="20,80 30,70"></polygon>
                        <polygon points="90,10 80,20"></polygon>
                        <polygon points="90,50 80,50"></polygon>
                        <polygon points="80,80 70,70"></polygon>
                    </svg>
                </div>
          </div>
          {/* 좋아요 숫자 나올 곳 */}
          <Text 
          // 밤, 낮에 따른 글자 색상 변화
          // 추후 좋아요 수 넘길 필요
          $nightWhite={location.pathname.includes("night")} 
          $black={location.pathname.includes("day")}>33</Text>
      </HeartWrap>
    </Container>
    </>
  )
}

export default Heart