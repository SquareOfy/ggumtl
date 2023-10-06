//<ChalCreateCert></ChalCreateCert>

// 리액트
import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import ChalCreateCert from "components/day/challenge/manage/ChalCreateCert";

// 스타일

const ChalCreateCertPage = () => {

  return (
    <>
    <NavTitleBar>인증하기</NavTitleBar>
    <ChalCreateCert/>
    </>
  )
}

export default ChalCreateCertPage