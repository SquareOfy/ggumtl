import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import DreamCreate from "components/night/dream/DreamCreate";

const DreamCreatePage = () => {

  return (
    <>
    <NavTitleBar>꿈 기록하기</NavTitleBar>
    <DreamCreate/>
    </>
  )
}

export default DreamCreatePage