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

const NoCardMsgWrap = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 2rem;
`

const NightProfileSellingTab = () => {

  // axios로 데이터 받기
  // const [auctionSellingDataList, setAuctionSellingDataList] = useState<AuctionSellingAxiosType[]>();
  const [auctionSellingDataList, setAuctionSellingDataList] = useState<AuctionCardType[]>();
  const [lastItemId, setLastItemId] = useState<number>(0);
  const [noCardMsg, setNoCardMsg] = useState<string>("");
  let size = 12;
  
  const getAxios = () => {
    basicHttp.get(`/profile/night/auction/list?lastItemId=${lastItemId}&size=${size}`)
    .then((res) => {
      console.log("== 꿈 팔기 탭 ==", res); 
      // 데이터가 있을 때
      if (res.data.data.auctionList) {
        setAuctionSellingDataList(res.data.data.auctionList);
        // setLastItemId(auctionSellingDataList[-1][""]); // 마지막 item id 변경
      } else {
        setNoCardMsg("판매 중인 카드가 없습니다.");
        console.log('여기');
      }
    })
    .catch((err) => console.log("== 꿈 팔기 탭 ==", err))
  }

  useEffect(() => {
    getAxios();
  }, []);

  // infinite scroll
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
    {
      auctionSellingDataList &&
      <InfiniteScroll 
      setArriveEnd={setArriveEnd} 
      // lastItemId={lastItemId}
      component={
        auctionSellingDataList?.map((data, i) => (
          <AuctionCard auctionCard={data} key={i}/>
          ))
        }
      />
    }

    {/* 꿈 카드가 없을 때 */}
    <NoCardMsgWrap>
      <Text $nightWhite>{noCardMsg}</Text>
    </NoCardMsgWrap>
    </>
  )
}

export default NightProfileSellingTab