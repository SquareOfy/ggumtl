
// map
// <ChallengeContentListItem></ChallengeContentListItem> ./daycommon 

// 리액트
import React, { useEffect, useState } from "react";

// 컴포넌트
import ChalContentListItem from "../daycommon/ChalContentListItem";
import InfiniteScroll from "components/common/InfiniteScroll";

// 스타일
export interface DayChallengeObj {
  title :string,
  period :string,
  challengeId :number
}

export interface DayChallengeList extends Array<DayChallengeObj> {}


const DayChallengeList = () => {
  // 처음에 받아온 리스트 (이후 여기에 새 항목을 추가하게 됨)
  const [allChalList, setAllChalList] = useState<DayChallengeList>([]);
  // 스크롤 내리면서 받아올 새 리스트 
  const [newChalList, setNewChalList] = useState<DayChallengeList>([]);
  const [lastItemId, setLastItemId] = useState<number>(0); // 마지막 아이템 번호
  // let size :number = 6; // 받아올 리스트 사이즈 - axios 연결 후 주석 해제하기
  
  // .axios 연결 전 임의의 값을 allChalList에 넣어두기
  let newObj :DayChallengeObj = {
    title : "111",
    period : "1일",
    challengeId : 111,
  }
  let newObj2 :DayChallengeObj = {
    title : "222",
    period : "2일",
    challengeId : 222,
  }

  // 처음 렌더링 시 Challenge List axios 요청
  useEffect(() => {
    // axios로 받아오면 setAllChalList로 기존 배열에 새 배열 추가하기
    setAllChalList([
      newObj, newObj2, newObj2, newObj2, 
      newObj2, newObj2, newObj2, newObj2,
      newObj2, newObj2, newObj2, newObj2
    ])
  }, [])
  
  //  DCL.tsx에서 초기 axios 요청 -> 데이터 불러옴 -> Infinite에서 스크롤 이벤트 감지
  //  -> 바닥에 다다르면 신호를 보냄 -> DCL.tsx에서 다음 axios 요청 
  
  const [arriveEnd, setArriveEnd] = useState<boolean>(false); // 바닥에 다다름을 알려주는 변수

  useEffect(() => {
    // 바닥에 다다랐으면 axios 요청
    if (arriveEnd) {
      // axios 요청하는 동작 추가
      setAllChalList([...allChalList, newObj])
      setArriveEnd(false);
      // setLastItemId(newChalList[-1]["challengeId"]); // 마지막 item id 변경
    }
  }, [arriveEnd])


  return (
    <>
    <InfiniteScroll 
    setArriveEnd={setArriveEnd} 
    // lastItemId={lastItemId}
    component={
      allChalList?.map((chal :DayChallengeObj) => (
      <ChalContentListItem key={chal.challengeId} chal={chal} />))
    }
    >
    </InfiniteScroll>
    </>
  )
}

export default DayChallengeList