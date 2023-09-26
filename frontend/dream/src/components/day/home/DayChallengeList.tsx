
// map
// <ChallengeContentListItem></ChallengeContentListItem> ./daycommon 

// 리액트
import React, { useEffect, useState } from "react";

// 컴포넌트
import ChalContentListItem from "../daycommon/ChalContentListItem";
import InfiniteScroll from "components/common/InfiniteScroll";
import Text from "style/Text";
import tokenHttp from "api/tokenHttp";

// 스타일
export interface DayChallengeObjType {
  title ?:string,
  period :string,
  challengeId :number,
  challengeTitle ?:string,
  challengeParticipateId ?:number,
  participationCount ?:number
}

export interface DayChallengeListType extends Array<DayChallengeObjType> {}


const DayChallengeList = () => {
  const [allChalList, setAllChalList] = useState<DayChallengeListType>([]);
  const [lastItemId, setLastItemId] = useState<number>(-1); // 마지막 아이템 번호
  let size :number = 6;

  // api 요청하는 함수
  const getAxios = () => {
    let apiAddress :string = "";

    // 현재는 api 주소에 슬래시 하나 더 추가된 상태라(/day/?size~) 화면에 나타나지 않음 => 추후 수정
    // 처음 요청 받을 때 : lastItemId 없음
    if (lastItemId === -1) {apiAddress = `/day?size=${size}`}
    // 두번째부터 요청 할 때
    else {apiAddress = `/day?lastItemId=${lastItemId}&size=${size}`}
    
    tokenHttp.get(apiAddress)
    .then((res)=> {
      console.log(res)
      if (typeof res.data.data.list === "object") {
        setAllChalList([...allChalList, ...res.data.data.list]);
      }
    })
    .catch(err=>console.log("DayChallengeList : ", err))
  };

  useEffect(() => {
    getAxios();
  }, []);

  // lastItemId 업데이트
  useEffect(() => {
    allChalList[allChalList.length - 1] && 
    setLastItemId(allChalList[allChalList.length - 1].challengeId)
  }, [setAllChalList, allChalList])

  
  //  DCL.tsx에서 초기 axios 요청 -> 데이터 불러옴 -> Infinite에서 스크롤 이벤트 감지
  //  -> 바닥에 다다르면 신호를 보냄 -> DCL.tsx에서 다음 axios 요청 
  
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
    <div style={{margin: "0.5rem"}}>
      <Text $isBold>Hot Challenge</Text>
    </div>
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

export default DayChallengeList