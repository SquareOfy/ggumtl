// <ChalCapsuleList></ChalCapsuleList>

// 리액트
import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import ChalCapsuleList from "components/day/capsule/ChalCapsuleList";

// 스타일

const ChalCapsuleListPage = () => {

  return (
    <>
    <NavTitleBar>타임 캡슐</NavTitleBar>
    <ChalCapsuleList/>
    </>
  )
}

export default ChalCapsuleListPage