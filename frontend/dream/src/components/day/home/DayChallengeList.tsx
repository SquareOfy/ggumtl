
// map
// <ChallengeContentListItem></ChallengeContentListItem> ./daycommon 

// 리액트
import React, { useEffect, useState } from "react";

// 컴포넌트
import ChalContentListItem from "../daycommon/ChalContentListItem";
import InfiniteScroll from "components/common/InfiniteScroll";
import Text from "style/Text";
import tokenHttp from "api/tokenHttp";
import { CategoryAxiosType } from "../daycommon/DayCategoryList";

// 스타일
export interface DayChallengeObjType {
  title ?:string,
  period :string,
  challengeId :number,
  challengeTitle ?:string,
  challengeParticipateId ?:number,
  participateCount ?:number,
  dreamKeywordId ?:number, // 나중에 chal keywordId로 바꾸기
  badgeUrl ?:string,
}

export interface DayChallengeListType extends Array<DayChallengeObjType> {}

export interface CategoryToChalProps {
  categoryProps :CategoryAxiosType
}

const DayChallengeList = (props :CategoryToChalProps) => {
  const [allChalList, setAllChalList] = useState<DayChallengeListType>([]);
  const [lastItemId, setLastItemId] = useState<number>(0);
  const [hasNext, setHasNext] = useState<boolean>(true); 
  let size :number = 6;

  // infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(true); // 바닥에 다다름을 알려주는 변수


  const getAxios = () => {
    let apiAddress :string = "";

    // 처음 요청 받을 때
    if (lastItemId === 0) {apiAddress = `/day?size=${size}`}
    // 두번째부터 요청 할 때
    else {apiAddress = `/day?lastItemId=${lastItemId}&size=${size}`}
    
    if (arriveEnd && hasNext) {  // 끝에 도달하고 다음이 있을 때 다음 데이터 호출
      tokenHttp.get(apiAddress)
      .then((res)=>{
        const response = res.data.data
        const challengeList = response.challengeList
        setAllChalList([...allChalList, ...challengeList]);
        setLastItemId(challengeList[challengeList.length - 1].challengeId);
        setHasNext(response.hasNext);
        console.log("== 메인 챌린지 컴포넌트 ==", response); 
      })
      .catch((err) => console.log("== 메인 챌린지 컴포넌트 ==", err))
    }
  }

  useEffect(() => {
    getAxios();
  }, []);

  
  //  DCL.tsx에서 초기 axios 요청 -> 데이터 불러옴 -> Infinite에서 스크롤 이벤트 감지
  //  -> 바닥에 다다르면 신호를 보냄 -> DCL.tsx에서 다음 axios 요청 
  
  useEffect(() => {
    // 바닥에 다다랐으면 axios 요청
    if (arriveEnd) {
      getAxios();
      setArriveEnd(false);
    }
  }, [arriveEnd])

  // console.log("chal List 탭 : ", props.categoryProps)

  return (
    <>
    {/* <div style={{margin: "0.5rem"}}>
      <Text $isBold>Hot Challenge</Text>
    </div> */}
    {
      allChalList &&
      <InfiniteScroll 
        setArriveEnd={setArriveEnd} 
        // lastItemId={lastItemId}
        component={
          allChalList
          // 카테고리
          .filter((chal: DayChallengeObjType) => {
            if (props.categoryProps.keywordId !== 0) {
              return chal.dreamKeywordId === props.categoryProps.keywordId
            } else {
              return true
            }
          })
          .map((chal :DayChallengeObjType) => (
            <ChalContentListItem key={chal.challengeId} chal={chal} />))
        }
      ></InfiniteScroll>
    }
    </>
  )
}

export default DayChallengeList