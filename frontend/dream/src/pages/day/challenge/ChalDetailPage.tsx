//<ChalDetail></ChalDetail>

// 리액트
import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import ChalDetail from "components/day/challenge/ChalDetail";

// 스타일

const ChalDetailPage = () => {

  return (
    <>
    <NavTitleBar>챌린지</NavTitleBar>
    <ChalDetail />
    </>
  )
}

export default ChalDetailPage