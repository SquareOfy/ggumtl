// <DayProfile></DayProfile>

// 리액트
import React from "react";

// 컴포넌트
import NavBar from "components/common/NavBar";
import DayProfileMain from "components/day/profile/DayProfileMain";

// 스타일

const DayProfilePage = () => {
  const period = 365 // 서버에서 주겠죠.
  const periodObj = {365: "gold", 100: 'silver'}
  const periodColor = periodObj[period]
  return (
    <>
    <NavBar/>
    <DayProfileMain />
    </>
  )
}

export default DayProfilePage