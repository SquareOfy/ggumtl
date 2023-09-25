// <ChalManageDetail></ChalManageDetail>

// 리액트
import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import ChalManageDetail from "components/day/challenge/manage/ChalManageDetail";

// 스타일

const ChalManageDetailPage = () => {

  return (
    <>
    <NavTitleBar>챌린지 관리</NavTitleBar>
    <ChalManageDetail/>
    </>
  )
}
export default ChalManageDetailPage