
// 챌린지면 챌린지 사진
// 경매면 꿈카드 사진

// 제목
// 알림메시지

// 리액트
import React from "react";
import { AlertListAxiosType } from "./AlertList";
import styled from "styled-components";
import Text from "style/Text";
import Image from "style/Image";
import Wrap from "style/Wrap";

// 컴포넌트

// 스타일
export interface AlertItemTypeProps {
  data :AlertListAxiosType,
  // key :number,
}

const AlertWrap = styled.div`

`

const AlertItem = ({data} :AlertItemTypeProps) => {

  return (
    <>
    <Wrap $alertWrap>
      <Image $tinyProfileImage><img></img></Image>
      <div className="contentarea">
        <Text $black>{data.title}</Text>
        <Text $black>{data.content}</Text>
      </div>
    </Wrap>
    </>
  )
}

export default AlertItem