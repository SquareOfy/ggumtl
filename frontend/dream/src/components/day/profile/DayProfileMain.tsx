 {/* <ProfileHeader day></ProfileHeader> */}

// 탭 3개
// 뱃지, 진행중, 완료

// # 뱃지
{/* <DayProfileBadgeTab></DayProfileBadgeTab> */}


// # 진행중
{/* <DayProfileOngoingTab></DayProfileOngoingTab> */}

// # 완료
{/* <DayProfileCompleteTab></DayProfileCompleteTab> */}

// 리액트
import React from "react";

// 컴포넌트
import ProfileHeader from "components/common/ProfileHeader";
import DayProfileBadgeTab from "./DayProfileBadgeTab";
import DayProfileOngoingTab from "./DayProfileOngoingTab";
import DayProfileCompleteTab from "./DayProfileCompleteTab";

// 스타일

const DayProfileMain = () => {

  return (
    <>
    <ProfileHeader/>
    <DayProfileBadgeTab />
    <DayProfileOngoingTab />
    <DayProfileCompleteTab />
    </>
  )
}

export default DayProfileMain