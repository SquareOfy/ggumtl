// 리액트
import React, { useEffect, useState } from "react";
import tokenHttp from "api/tokenHttp";

// 컴포넌트
import InfiniteScroll from "components/common/InfiniteScroll";

// 스타일
import Image from "style/Image";
import styled from "styled-components";
import { useParams } from "react-router-dom";
import Text from "style/Text";

export interface ProfileBadgeAxiosType {
  badgeId :number,
  challengeId :number,
  completedDays :string
}

const BadgeWrap = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 3fr);
  flex-direction: column;
  margin: 0 0.5rem;
`
const NoBadgeMsgWrap = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 2rem;
`

const DayProfileBadgeTab = () => {
  const params = useParams();

  const [allBadgeList, setAllBadgeList] = useState<ProfileBadgeAxiosType[]>([]);
  const [lastItemId, setLastItemId] = useState<number>(0); // 마지막 아이템 번호
  const [hasNext, setHasNext] = useState<boolean>(true); 
  const [noBadgeMsg, setNoBadgeMsg] = useState<string>("뱃지가 없습니다.");
  let size :number = 6;

  // infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(true); // 바닥에 다다름을 알려주는 변수

  // api 요청
  const getAxios = () => {
    let apiAddress :string = "";

    // 처음 요청 받을 때 : lastItemId 없음
    if (lastItemId === -1) {apiAddress = `/profile/day/badge/list/${params.userId}?size=${size}`}
    // 두번째부터 요청 할 때
    else {apiAddress = `/profile/day/badge/list/${params.userId}?lastItemId=${lastItemId}&size=${size}`}
    
    if (arriveEnd && hasNext) {
      tokenHttp.get(apiAddress)
      .then((res)=> {
        const response = res.data.data
        const badgeList = response.badgeList
        setAllBadgeList([...allBadgeList, ...badgeList]);
        setLastItemId(badgeList[badgeList.length - 1]?.badgeId);
        setHasNext(response.hasNext);

        console.log("뱃지 탭 : ", res)
      })
      .catch(err=>console.log("뱃지 탭 : ", err))
    }
  };

  useEffect(() => {
    getAxios();
  }, [])

  useEffect(() => {
    // 바닥에 다다랐으면 axios 요청
    if (arriveEnd) {
      getAxios();
      setArriveEnd(false);
    }
  }, [arriveEnd])

  return (
    <>
      {
        allBadgeList.length === 0
        ? 
        <NoBadgeMsgWrap>
          <Text $black>{noBadgeMsg}</Text>
        </NoBadgeMsgWrap>
        :
          <BadgeWrap>
            <InfiniteScroll
            setArriveEnd={setArriveEnd} 
            // lastItemId={lastItemId}
            component={
              allBadgeList?.map((badge :ProfileBadgeAxiosType) => (
                <Image 
                $badge
                key={badge.badgeId}><img src=""></img></Image>
                // <ChalContentListItem key={badge.badgeId} badge={badge} />)
                ))
              }
              >
            </InfiniteScroll>
          </BadgeWrap>
      }
    </>
  )
}

export default DayProfileBadgeTab