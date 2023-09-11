{/* <ProfileHeader night></ProfileHeader> */}
// 탭 3개(카드, 꿈 받기, 꿈 주기) // 2개 (카드, 꿈주기)

// 리액트
import React from "react";

// 컴포넌트
import ProfileHeader from "components/common/ProfileHeader";
import NightProfileCardTab from "./NightProfileCardTab";
import NightProfileSellingTab from "./NightProfileSellingTab";
import NightProfileBuyingTab from "./NightProfileBuyingTab";


// 스타일

const NightProfileMain = () => {

  return (
    <>
    <ProfileHeader />

    <NightProfileCardTab />
    <NightProfileBuyingTab />
    <NightProfileSellingTab />
    </>
  )
}

export default NightProfileMain