
// <ChalCreate></ChalCreate>

// 리액트
import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import ChalCreate from "components/day/challenge/ChalCreate";

// 스타일

const ChalCreatePage = () => {

  return (
    <>
    <NavTitleBar>새 챌린지</NavTitleBar>
    <ChalCreate />
    </>
  )
}

export default ChalCreatePage