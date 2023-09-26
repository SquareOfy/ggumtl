// 카드 탭
// 버튼 : 공개, 구매 (본인일 때만 보임. 아니면 안 보여)
// 사진 리스트 (NightImage)

// 리액트
import React, { useEffect, useState } from "react";
import tokenHttp from "api/tokenHttp";

// 컴포넌트
import InfiniteScroll from "components/common/InfiniteScroll";

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
  const [profileId, setProfileId] = useState<number>(1);
  const [lastItemId, setLastItemId] = useState<number>(-1);
  const [noCardMsg, setNoCardMsg] = useState<string>("");
  let size = 6;
  
  // axios로 받아서 업데이트 할 데이터
  const [dreamCardList, setDreamCardList] = useState<ProfileDreamCardAxiosType[]>([]);
  
  const getAxios = () => {
    let apiAddress :string = "";

    // 처음 요청 받을 때 : lastItemId 없음
    if (lastItemId === -1) {apiAddress = `/profile/night/card/${profileId}?size=${size}`}
    // 두번째부터 요청 할 때
    else {apiAddress = `/profile/night/card/${profileId}?lastItemId=${lastItemId}&size=${size}`}
    
    tokenHttp.get(apiAddress)
    .then((res) => {
      console.log("밤 꿈 카드 탭 : ", res.data)
      
      // 생성된 꿈카드가 있을 때
      if (res.data.status === 200) {
        // let newData = res.data.data.dreamCardList
        setDreamCardList([...dreamCardList, ...res.data.data.dreamCardList]);
        setLastItemId(res.data.data.dreamCardList[res.data.data.dreamCardList.length - 1].dreamCardId) 
      } 
      // 생성된 꿈카드가 없을 때
      if (res.data.status === 400) {
        setNoCardMsg(res.data.data);
      }
    })
    .catch((err) => console.log(err))
  }

  useEffect(() => {
    getAxios();  
  }, [])

  // 공개 카드만 보이도록 
  const [isShowAllCard, setIsShowAllCard] = useState<boolean>(false);

  const handleShowCard = () => {
    setIsShowAllCard(!isShowAllCard)
    console.log(isShowAllCard)
  }

  // 로딩 중
  const [isLoading, setIsLoading] = useState<boolean>(true);

  // 데이터 로딩이 완료되면
  useEffect(() => {
    setIsLoading(false);
  }, [dreamCardList]);


  // Infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(false); // 바닥에 다다름을 알려주는 변수
  
  // 바닥에 다다랐으면 axios 요청
  useEffect(() => {
    if (arriveEnd) {
      // axios 요청
      getAxios();
      setArriveEnd(false);
    }
  }, [arriveEnd])

  return (
    <>
    {/* 프로필 유저 === 본인 일치 시 버튼 보이도록 */}
    {/* 공개, 구매 버튼 */}
    <ProfileCardButtonWrap>
      <Button 
      $follow $nightPalePurple
      onClick={handleShowCard}
      >공개</Button>
      <Button $follow $nightPalePurple>구매</Button>
    </ProfileCardButtonWrap>

    {/* 로딩 중일 때 로딩 메시지 표시 */}
    {isLoading && <div>Loading...</div>}

    {/* 데이터 로딩이 완료된 후에 꿈 카드를 표시 */}
    {!isLoading && (
      <ProfileDreamCardWrap>
        {
          dreamCardList &&
          <InfiniteScroll
          setArriveEnd={setArriveEnd} 
          // lastItemId={lastItemId}
          component={
            dreamCardList
            .filter((card) => (isShowAllCard ? card.isShow === "T" : true))
            .map((card, i) => (
                  <Image $profileCard $nightImageBorder key={i}>
                    <img src={card.dreamCardImageUrl} alt="dreamCard"></img>
                  </Image>
            ))
            }
          > 
          </InfiniteScroll>
        }
      </ProfileDreamCardWrap>
    )}
    {/* 꿈 카드가 없을 때 */}
    <NoCardMsgWrap>
      <Text $nightWhite>{noCardMsg}</Text>
    </NoCardMsgWrap>
    
    </>
  )
}

export default NightProfileCardTab
