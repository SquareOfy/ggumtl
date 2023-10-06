// 리액트
import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import DreamDetail from "components/night/dream/DreamDetail";

// 스타일

const DreamDetailPage = () => {

  return (
    <>
    <NavTitleBar>꿈 해몽</NavTitleBar>
    <DreamDetail />
    </>
  )
}

export default DreamDetailPage