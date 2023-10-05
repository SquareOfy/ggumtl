// 로고이미지

// 낮의꿈 꾸러가기
{/* =>  <DayHomePage/> */}

// 밤의꿈 꾸러가기
{/* => <NightHomePage/> */}

import Button from "components/common/Button";
import React from "react";
import { useNavigate } from "react-router-dom";

const SunsetMain = () => {

  const navigate = useNavigate()

  return (
    <>
    <Button onClick={()=>{navigate('/day/main')}}>낮 아이콘</Button>
    <Button onClick={()=>{navigate('/night/main')}}>밤 아이콘</Button>
    </>
  )
}

export default SunsetMain