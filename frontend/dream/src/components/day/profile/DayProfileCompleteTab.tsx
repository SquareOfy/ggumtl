// 완료 챌린지리스트.map
// ChalContentListItem

// 리액트
import React, { useEffect, useState } from "react";

// 컴포넌트
import ChalContentListItem from "../daycommon/ChalContentListItem";
import { DayChallengeListType, DayChallengeObjType } from "./DayProfileOngoingTab";
import tokenHttp from "api/tokenHttp";
import InfiniteScroll from "components/common/InfiniteScroll";
import { useParams } from "react-router-dom";

// 스타일

const DayProfileCompleteTab = () => {
  const [allChalList, setAllChalList] = useState<DayChallengeListType>([]);
  const [lastItemId, setLastItemId] = useState<number>(-1); // 마지막 아이템 번호
  // const profileUserId = useParams();
  const profileUserId :number = 20;
  let size :number = 10;
  
  // api 요청
  const getAxios = () => {
    let apiAddress :string = "";

    // 처음 요청 받을 때 : lastItemId 없음
    if (lastItemId === -1) {apiAddress = `/profile/day/mychallenge/end/list/${profileUserId}?size=${size}`}
    // 두번째부터 요청 할 때
    else if (lastItemId > -1) {apiAddress = `/profile/day/mychallenge/end/list/${profileUserId}?lastItemId=${lastItemId}&size=${size}`}
    
    tokenHttp.get(apiAddress)
    .then((res)=> {
      console.log(res)
      if (typeof res.data.data.challengeList === "object") {
        setAllChalList([...allChalList, ...res.data.data.challengeList]);
        setLastItemId(res.data.data.challengeList[res.data.data.challengeList.length - 1].challengeId)
      }
    })
    .catch(err=>console.log("DayProfileCompleteTab", err))
  };

  useEffect(() => {
    getAxios();
  }, [])


  // infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(false); // 바닥에 다다름을 알려주는 변수

  useEffect(() => {
    // 바닥에 다다랐으면 axios 요청
    console.log("arrive End : ", arriveEnd)
    if (arriveEnd) {
      getAxios();
    }
    setArriveEnd(false);
  }, [arriveEnd])


  return (
    <>
    {
      allChalList &&
      <InfiniteScroll
      setArriveEnd={setArriveEnd} 
      // lastItemId={lastItemId}
      component={
        allChalList?.map((chal :DayChallengeObjType) => (
          <ChalContentListItem key={chal.challengeId} chal={chal} />))
        }
        >
      </InfiniteScroll>
    }
    </>
  )
  }

export default DayProfileCompleteTab