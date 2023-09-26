// 진행 중 챌린지리스트.map
// ChalContentListItem

// 리액트
import React, { useEffect, useState } from "react";

// 컴포넌트
import ChalContentListItem from "../daycommon/ChalContentListItem";
import tokenHttp from "api/tokenHttp";
import InfiniteScroll from "components/common/InfiniteScroll";

// 스타일
export interface DayChallengeObjType {
  challengeTitle :string,
  period :string,
  challengeId :number,
  challengeParticipateId ?:number,
  participationCount ?:number
}

export interface DayChallengeListType extends Array<DayChallengeObjType> {}

const DayProfileOngoingTab = () => {
  const [allChalList, setAllChalList] = useState<DayChallengeListType>([]);
  const [lastItemId, setLastItemId] = useState<number>(-1); // 마지막 아이템 번호
  let size :number = 6;
  
  // api 요청
  const getAxios = () => {
    let apiAddress :string = "";

    // 처음 요청 받을 때 : lastItemId 없음
    if (lastItemId === -1) {apiAddress = `/day/mychallange/list?size=${size}`}
    // 두번째부터 요청 할 때
    else {apiAddress = `/day/mychallange/list?lastItemId=${lastItemId}&size=${size}`}
    
    tokenHttp.get(apiAddress)
    .then((res)=> {
      console.log(res)
      if (typeof res.data.data.challengeList === "object") {
        setAllChalList([...allChalList, ...res.data.data.challengeList]);
        setLastItemId(res.data.data.challengeList[res.data.data.challengeList.length - 1].challengeId)
      }
    })
    .catch(err=>console.log("===", err))
  };

  useEffect(() => {
    getAxios();
  }, [])

  // // lastItemId 업데이트
  // useEffect(() => {
  //   allChalList[allChalList.length - 1] && 
  //   setLastItemId(allChalList[allChalList.length - 1].challengeId)
  // }, [setAllChalList, allChalList])

  // infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(false); // 바닥에 다다름을 알려주는 변수

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

export default DayProfileOngoingTab