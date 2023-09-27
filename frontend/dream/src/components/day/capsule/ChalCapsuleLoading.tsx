
// 캡슐 생성 로딩 화면

// 캡슐 오픈 로딩 화면 

// 리액트
import React , {useEffect}from "react";
import { useNavigate, useParams } from "react-router";

// 컴포넌트

// 스타일

const ChalCapsuleLoading = () => {

  const navigate = useNavigate() 
  const params = useParams()
  const currentChallengeId = params.challengeId

  const navigateToTimecapsule = () => {
    navigate(`/day/challenge/${currentChallengeId}/timecapsule`)
  }

  useEffect(()=>{
    setTimeout(navigateToTimecapsule,3000)
  },[])

  return (
    <>
    <p>타임캡슐 반갈죽</p>
    </>
  )
}

export default ChalCapsuleLoading