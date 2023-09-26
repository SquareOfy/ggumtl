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
import { LiaCoinsSolid } from "react-icons/lia";
import tokenHttp from "api/tokenHttp";

// progress 속성을 정의
interface ProgressBarProps {
  progress :number;
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

// profile 정보
interface ProfileHeaderAxiosType {
  dreamCardCount :number,
  followerCount :number,
  followingCount :number,
  nickname :string,
  profileImageName :string,
  profileImageUrl :string,
  userId :number,
  wrigglePoint :number,
  point ?:number,
}

const ProfileHeader = () => {
  const themeMode = useSelector((state: RootState) => state.themeMode.themeMode);
  const [isNight, setIsNight] = useState<boolean>(false);
  const [isStarClicked, setIsStarClicked] = useState<boolean>(true);
  const [isMyProfile, setIsMyProfile] = useState<boolean>(true); // 내 프로필인지 유저 확인
  const [isFollowing, setIsFollowing] = useState<boolean>(false); // 팔로우 했는지 여부
  const [progress, setProgress] = useState<number>(0); // 꿈틀도 추후 변경하기
  
  // 프로필 axios 통신 데이터들
  const [userData, setUserData] = useState<ProfileHeaderAxiosType>();
  let profileUserId = 3; // 추후 바꾸기

  // axios 요청
  useEffect(() => {
    let mode :string
    // console.log(themeMode.mode)
    if (isNight) {mode = "night"}
    else {mode = "day"}

    tokenHttp.get(`/profile/${mode}/header/${profileUserId}`)
    .then((res) => {
      // console.log(res);
      setUserData(res.data.data);
    })
    .catch((err) => console.log("ProfileHeader 오류 : ", err))
  }, [themeMode.mode])

  // 꿈틀도 값 변경
  useEffect(() => {
    if (userData) {setProgress(userData?.wrigglePoint)}
  }, [userData])

  // 테마 확인
  useEffect(() => {
    console.log("테마 확인 : ", themeMode.mode)
    if (themeMode.mode === 'night') {
      setIsNight(true);
    } else {
      setIsNight(false);
    }
  }, [themeMode.mode])
  
  // 꿈틀도 별 클릭
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
          <img src={userData?.profileImageUrl} alt={userData?.profileImageName}/>
        </Image>
        <Text
        $nightWhite={isNight}
        >
          <div>
            <p>{userData?.nickname}</p>
            {
              !isMyProfile &&
              <Button
              $follow
              $nightPalePurple
              >{!isFollowing ? "팔로우" : "팔로잉"}</Button>
            }
            {
              isMyProfile &&
              <Text
              $nightMoney={isNight}
              $dayMoney={!isNight}
              ><LiaCoinsSolid style={{width: "1.3rem", height: "1.3rem", marginRight: "0.4rem"}}></LiaCoinsSolid> {userData?.point && (userData?.point/100).toFixed(1)}k</Text>
              // 포인트 출력 부분은 로그인 구현 완료 후 추가 필요
            }
          </div>
          <div>
            <div>
              { isNight ? <p>꿈 카드</p> : <p>완료 챌린지</p>}
              <p>{userData?.dreamCardCount}</p>
            </div>
            <div>
              <p>팔로워</p>
              <p>{userData?.followerCount}</p>
            </div>
            <div>
              <p>팔로잉</p>
              <p>{userData?.followingCount}</p>
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