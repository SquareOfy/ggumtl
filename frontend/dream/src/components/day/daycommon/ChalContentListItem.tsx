
// 챌린지 이미지 CircleImage

// 제목
// 참여자 수

// Day 일 수

// 리액트
import React from "react";

// 컴포넌트
import { Box } from "style/Box";
import { DayChallengeObjType } from "../home/DayChallengeList";

// 스타일

interface ChalContentListItemProps {
  key: number
  chal: DayChallengeObjType;
}

const ChalContentListItem = ({chal}:ChalContentListItemProps) => {
  return (
    <>
      <Box
      $challengeContentBox
      >
        <img></img>
        <div><p>{chal.title}</p><p>{chal.title}</p></div>
        <div><p>Day</p><p>{chal.period}</p></div>
      </Box>
    </>
  )
}

export default ChalContentListItem