{/* <FlipCard></FlipCard> */}

// 좋아요

{/* <DreamRecordContentsTab></DreamRecordContentsTab> */}

// 공개 // 버튼 2개

// 리액트
import React, { useState } from "react";

// 컴포넌트
import DreamRecordContentsTab from "./DreamRecordContentsTab";
import NightFlipCard from "../nightcommon/NightFlipCard";
import { ReverseCardType } from "../home/NightHomeItem";

// 스타일

const DreamDetail = () => {
  const [reverseCardData, setReverseCardData] = useState<ReverseCardType>()

  return (
    <>
    <NightFlipCard reverseCardData={reverseCardData}/>
    <DreamRecordContentsTab setReverseCardData={setReverseCardData}/>
    </>
  )
}

export default DreamDetail
