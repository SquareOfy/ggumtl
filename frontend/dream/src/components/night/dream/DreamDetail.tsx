{/* <FlipCard></FlipCard> */}

// 좋아요

{/* <DreamRecordContentsTab></DreamRecordContentsTab> */}

// 공개 // 버튼 2개

// 리액트
import React from "react";

// 컴포넌트
import DreamRecordContentsTab from "./DreamRecordContentsTab";
import Button from "components/common/Button";
import NightFlipCard from "../nightcommon/NightFlipCard";

// 스타일

const DreamDetail = () => {

  return (
    <>
    <NightFlipCard/>
    <DreamRecordContentsTab />
    </>
  )
}

export default DreamDetail
