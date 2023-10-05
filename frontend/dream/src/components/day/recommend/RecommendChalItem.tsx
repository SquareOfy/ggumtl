// 리액트
import React from "react";
import { RecommendObjType } from "./RecommendChalMain";
import { Box } from "style/Box";
import Container from "style/Container";
import Image from "style/Image";
import Text from "style/Text";

interface RecommendChalItemProps {
  chal : RecommendObjType
}

const RecommendChalItem = ({chal}: RecommendChalItemProps) => {
  return (
    <Box $recommendChalBox>
      <Image $badge $largeBadge $recommendBadge>
        <img src={chal.badgeUrl} alt="뱃지" />
      </Image>
      <Text $recommendCardTitle>{chal.title}</Text>
      <p>{chal.period}</p>
    </Box>
  )
}

export default RecommendChalItem