// 진행 중 챌린지리스트.map
// ChalContentListItem

// 리액트
import React, { useEffect, useState } from "react";

// 컴포넌트
import ChalContentListItem from "../daycommon/ChalContentListItem";
import tokenHttp from "api/tokenHttp";
import InfiniteScroll from "components/common/InfiniteScroll";
import { useParams } from "react-router-dom";
import styled from "styled-components";
import Text from "style/Text";

// 스타일
export interface DayChallengeObjType {
  challengeTitle :string,
  period :string,
  challengeId :number,
  challengeParticipateId ?:number,
  participationCount ?:number
}

const NoChalMsgWrap = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 2rem;
`

export interface DayChallengeListType extends Array<DayChallengeObjType> {}

const DayProfileOngoingTab = () => {
  const params = useParams();

  const [allChalList, setAllChalList] = useState<DayChallengeListType>([]);
  const [lastItemId, setLastItemId] = useState<number>(0); // 마지막 아이템 번호
  const [hasNext, setHasNext] = useState<boolean>(true); 
  const [noChalMsg, setNoChalMsg] = useState<string>("");
  let size :number = 6;
  
  // infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(true); // 바닥에 다다름을 알려주는 변수

  // api 요청
  const getAxios = () => {
    let apiAddress :string = "";

    // 처음 요청 받을 때 : lastItemId 없음
    if (lastItemId === -1) {apiAddress = `/day/mychallenge/list?size=${size}`}
    // 두번째부터 요청 할 때
    else {apiAddress = `/day/mychallenge/list?lastItemId=${lastItemId}&size=${size}`}
    
    if (arriveEnd && hasNext) {
      tokenHttp.get(apiAddress)
      .then((res)=> {
        const response = res.data

        if (response.status === 400) {
          setNoChalMsg(response.data)
        } else {
          const challengeList = response.challengeList
          setAllChalList([...allChalList, ...challengeList]);
          setLastItemId(challengeList[challengeList.length - 1]?.challengeId);
          setHasNext(response.data.hasNext);
        }

        console.log("진행 중 탭 : ", res)
      })
      .catch(err=>console.log("===", err))
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
      allChalList.length === 0
      ?
      <NoChalMsgWrap>
        <Text $black>{noChalMsg}</Text>
      </NoChalMsgWrap>
      :
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