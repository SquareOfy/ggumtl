// 리액트
import React, { useEffect, useState } from "react";
import basicHttp from "api/basicHttp";

// 컴포넌트
import AuctionCard from "../nightcommon/AuctionCard";
import InfiniteScroll from "components/common/InfiniteScroll";

// 스타일
import { AuctionCardType } from "../auction/AuctionMainList";
import Text from "style/Text";
import styled from "styled-components";
import tokenHttp from "api/tokenHttp";

const NoCardMsgWrap = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 2rem;
`

const NightProfileSellingTab = () => {

  // axios로 데이터 받기
  const [auctionSellingDataList, setAuctionSellingDataList] = useState<AuctionCardType[]>([]);
  const [lastItemId, setLastItemId] = useState<number>(0);
  const [hasNext, setHasNext] = useState<boolean>(true); 
  const [noAuctionMsg, setNoAuctionMsg] = useState<string>("참여 중인 경매가 없습니다.");
  let size = 12;
  
  // infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(true); // 바닥에 다다름을 알려주는 변수

  const getAxios = () => {
    let apiAddress :string = "";

    // 처음 요청 받을 때
    if (lastItemId === 0) {apiAddress = `/profile/night/auction/list?size=${size}`}
    // 두번째부터 요청 할 때
    else {apiAddress = `/profile/night/auction/list?lastItemId=${lastItemId}&size=${size}`}
    
    if (arriveEnd && hasNext) {  // 끝에 도달하고 다음이 있을 때 다음 데이터 호출
      tokenHttp.get(apiAddress)
      .then((res)=>{
        const response = res.data.data
        const auctionList = response.auctionList
        setAuctionSellingDataList([...auctionSellingDataList, ...auctionList]);
        setLastItemId(auctionList[auctionList.length - 1]?.dreamCardId);
        setHasNext(response.hasNext);
        console.log("== 꿈 팔기 탭 ==", res); 
      })
      .catch((err) => console.log("== 꿈 팔기 탭 ==", err))
    }
  }

  useEffect(() => {
    getAxios();
  }, []);

  // 바닥에 다다랐으면 axios 요청
  useEffect(() => {
    if (arriveEnd) {
      getAxios();
      setArriveEnd(false);
    }
  }, [arriveEnd])

  return (
    <>
    {
      auctionSellingDataList.length === 0
      ? <NoCardMsgWrap>
          <Text $nightWhite>{noAuctionMsg}</Text>
        </NoCardMsgWrap>
      : <InfiniteScroll 
        setArriveEnd={setArriveEnd} 
        // lastItemId={lastItemId}
        component={
          auctionSellingDataList?.map((data, i) => (
            <AuctionCard auctionCard={data} key={i}/>
            ))
          }
        />
    }
    </>
  )
}

export default NightProfileSellingTab