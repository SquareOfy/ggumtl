// 닉네임
// FollowButton (남의 프로필 경우) , 자신의 프로필 경우 -> 안보임

// wrapping 4칸
// CircleImage
// ProfileText 꿈머니(자신의 프로필 경우) 완료 챌린지 수(남의 프로필 경우)
// ProfileText 팔로워
// ProfileText 팔로잉

// 리액트
import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { RootState } from "store";

// 컴포넌트

// 스타일
import Button from "./Button";
import Wrap from "style/Wrap";
import Image from "style/Image";
import Text from "style/Text";
import styled from "styled-components";
import { FaStar } from "react-icons/fa6";

// progress 속성을 정의
interface ProgressBarProps {
  progress: number;
}

const ProgressBar = styled.div<ProgressBarProps>`
  height: 0.8rem;
  margin-right: 0.75rem;
  border-radius: 1rem;
  color: #997ad8;
  position: relative;
  background-color: #F9F9F9;
  
  &::before {
    content: "";
    position: absolute;
    inset: 0 calc(${props => 100 -props.progress}%) 0 0;
    border-radius: inherit;
    background: currentColor;
    animation: p6 2s;
  }
  @keyframes p6 {
    /* 100% {inset:0} */
    0% {
      width: 0;
    }
    100% {
      width: ${props => props.progress}%; // 원하는 최대 너비
    }
  }
`

const ProfileHeader = () => {
  const themeMode = useSelector((state: RootState) => state.themeModeReducer.themeMode);
  const [isNight, setIsNight] = useState<boolean>(false);
  const [isStarClicked, setIsStarClicked] = useState<boolean>(true);
  const [isMyProfile, setIsMyProfile] = useState<boolean>(false); // 내 프로필인지 유저 확인

  const [progress, setProgress] = useState<number>(70); // 꿈틀도 추후 변경하기

  useEffect(() => {
    if (themeMode.mode === 'night') {
      setIsNight(true);
    } else {
      setIsNight(false);
    }
  }, [themeMode.mode])
  
  const handleStarClicked = () => {
    setIsStarClicked(!isStarClicked)
  }

  return (
    <>
    <Wrap $profileHeaderWrap>
      <div>
        <Image
        $smallProfileImage
        >
          <img src="https://mblogthumb-phinf.pstatic.net/MjAyMjAxMjVfMjAy/MDAxNjQzMTAyOTk2NjE0.gw_H_jjBM64svaftcnheR6-mHHlmGOyrr6htAuxPETsg.8JJSQNEA5HX2WmrshjZ-VjmJWqhmgE40Qm5csIud9VUg.JPEG.minziminzi128/IMG_7374.JPG?type=w800"/>
        </Image>
        <Text
        $nightWhite={isNight}
        >
          <div>
            <p>나는프론트엔드</p>
            <Button
            $follow
            $nightPalePurple
            >{isMyProfile ? "팔로우" : "팔로잉"}</Button>
          </div>
          <div>
            <div>
              {
                isMyProfile ? <p>꿈 머니</p>
                : <p>꿈 카드</p>
              }
              {
                isMyProfile ? <p>5.5k</p>
                : <p>5</p>
              }
            </div>
            <div>
              <p>팔로워</p>
              <p>1.3k</p>
            </div>
            <div>
              <p>팔로잉</p>
              <p>70</p>
            </div>
          </div>
        </Text>
      </div>
      <div>
        <ProgressBar progress={progress}></ProgressBar>  
        <span>
          <FaStar 
          onClick={handleStarClicked}
          style={{width: "2.5rem", height: "1.5rem", color: isStarClicked ? "#F9F9F9" : "#997ad8"}}/>
          </span>
      </div>
    </Wrap>
    </>
  )
}

export default ProfileHeader