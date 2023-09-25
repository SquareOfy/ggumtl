// 카드 탭
// 버튼 : 공개, 구매 (본인일 때만 보임. 아니면 안 보여)
// 사진 리스트 (NightImage)

// 리액트
import React, { useEffect, useState } from "react";
import basicHttp from "api/basicHttp";

// 컴포넌트

// 스타일
import Button from "components/common/Button";
import Image from "style/Image";
import styled from "styled-components";
import Text from "style/Text";

const ProfileCardButtonWrap = styled.div`
  margin: 1rem 0.5rem;
`

const ProfileDreamCardWrap = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  margin-right: 0.5rem;
`

const NoCardMsgWrap = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 2rem;
`

export interface ProfileDreamCardAxiosType {
  dreamCardId :number,
  dreamCardImageUrl :string,
  dreamCardAuthorId :number,
  isShow :string
}

const NightProfileCardTab = () => {
  // axios로 보내야 할 데이터
  const [profileId, setProfileId] = useState<number>(3);
  const [lastItemId, setLastItemId] = useState<number>(0);
  const [noCardMsg, setNoCardMsg] = useState<string>("");
  let size = 3;

  // axios로 받아서 업데이트 할 데이터
  const [dreamCardList, setDreamCardList] = useState<ProfileDreamCardAxiosType[]>([]);

  useEffect(() => {
    basicHttp.get(`/profile/night/card/${profileId}?lastItemId=${lastItemId}&size=${size}`)
    .then((res) => {
      // console.log(res.data.data)
      
      // 생성된 꿈카드가 있을 때
      if (res.data.status === 200) {
        setDreamCardList(res.data.data);
      }
      // 생성된 꿈카드가 없을 때
      if (res.data.status === 400) {
        setNoCardMsg(res.data.data);
      }
    })
    .catch((err) => console.log(err))
  }, [])

  return (
    <>
    <ProfileCardButtonWrap>
      {/* 공개, 구매 버튼 */}
      <Button $follow $nightPalePurple>공개</Button>
      <Button $follow $nightPalePurple>구매</Button>
    </ProfileCardButtonWrap>

    <ProfileDreamCardWrap>
      {
        dreamCardList?.map((card, i) => (
          <Image $profileCard $nightImageBorder key={i}>
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQL1FLLXoD2D9jTPPy5nohrnuFBE0RypC2bdJucBEMGTQ&s"></img>
          </Image>
        ))
      }
    </ProfileDreamCardWrap>
    
    {/* 꿈 카드가 없을 때 */}
    <NoCardMsgWrap>
      <Text $nightWhite>{noCardMsg}</Text>
    </NoCardMsgWrap>
    
    </>
  )
}

export default NightProfileCardTab
