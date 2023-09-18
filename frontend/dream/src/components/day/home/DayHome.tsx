
// 스토리
// <DayStoryList></DayStoryList>

// 카테고리 (textdiv)
//<DayCategoryList></DayCategoryList> in ./daycommon

// Hot Challenge(textdiv)
//<DayChallengeList></DayChallengeList>

// 리액트
import React from "react";

// 컴포넌트
import DayStoryList from "./DayStoryList";
import DayCategoryList from "../daycommon/DayCategoryList";
import DayChallengeList from "./DayChallengeList";

// 스타일

const DayHome = ()=> {

  return (
    <>
    {/* 스토리 */}
    <DayStoryList />

    {/* 카테고리 */}
    <DayCategoryList />

    {/* HotChalllenge Arr */}
    <DayChallengeList />
    
    </>
  )
}

export default DayHome;