{/* <ChalDetailInfo></ChalDetailInfo> */}


// 참여하기 -> ChalCapsuleCreatePage, 관리하기 버튼
// LongButton

// 인스타 피드같은 게시물
// map
// <ChalCertArticle></ChalCertArticle>

// 리액트
import React from "react";

// 컴포넌트
import ChalDetailInfo from "./ChalDetailInfo";
import Button from "components/common/Button";
import ChalCertArticle from "./ChalCertArticle";

// 스타일

const ChalDetail = () => {

  return (
    <>
    {/* 공부흔적 남기기 */}
    <ChalDetailInfo />

    {/* 참여하기 버튼 */}

    {/* 인증글 목록 */}
    <ChalCertArticle />
    </>
  )
}
export default ChalDetail