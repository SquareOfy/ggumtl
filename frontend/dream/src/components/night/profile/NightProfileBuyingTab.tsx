// 리액트
import React, { useEffect, useState } from "react";

// 컴포넌트
import AuctionCard from "../nightcommon/AuctionCard";
import basicHttp from "api/basicHttp";
import InfiniteScroll from "components/common/InfiniteScroll";
import { AuctionCardType } from "../auction/AuctionMainList";
import tokenHttp from "api/tokenHttp";
import Text from "style/Text";
import styled from "styled-components";

// 스타일
// export interface AuctionBuyingAxiosType {
//   auctionStatus :string,
//   dreamCardId :number,
//   dreamCardImageUrl :string, 
//   endedAt :string,
//   keywords :string[],
//   positivePoint :string,
//   rarePoint :string,
// }

const NoAuctionMsgWrap = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 2rem;
`


const NightProfileBuyingTab = () => {

  // axios로 데이터 받기
  const [auctionBuyingDataList, setAuctionBuyingDataList] = useState<AuctionCardType[]>([]);
  const [lastItemId, setLastItemId] = useState<number>(0);
  const [noAuctionMsg, setNoAuctionMsg] = useState<string>("참여 중인 경매가 없습니다.");
  const [hasNext, setHasNext] = useState<boolean>(true); 
  let size = 12;

  // infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(true); // 바닥에 다다름을 알려주는 변수

  const getAxios = () => {
    let apiAddress :string = "";

    // 처음 요청 받을 때
    if (lastItemId === 0) {apiAddress = `/profile/night/auction/participation/list?size=${size}`}
    // 두번째부터 요청 할 때
    else {apiAddress = `/profile/night/auction/participation/list?lastItemId=${lastItemId}&size=${size}`}
    
    if (arriveEnd && hasNext) {  // 끝에 도달하고 다음이 있을 때 다음 데이터 호출
      tokenHttp.get(apiAddress)
      .then((res)=>{
        const response = res.data.data
        const auctionList = response.auctionList

        setAuctionBuyingDataList([...auctionBuyingDataList, ...auctionList]);
        setLastItemId(auctionList[auctionList.length - 1]?.dreamCardId);
        setHasNext(response.hasNext);
        
        console.log("== 꿈 받기 탭 ==", res); 
      })
      .catch((err) => console.log("== 꿈 받기 탭 에러 ==", err))
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
      auctionBuyingDataList.length === 0
      ? <NoAuctionMsgWrap>
          <Text $nightWhite>{noAuctionMsg}</Text>
        </NoAuctionMsgWrap>
      : <InfiniteScroll 
        setArriveEnd={setArriveEnd} 
        // lastItemId={lastItemId}
        component={
          auctionBuyingDataList?.map((data, i) => (
            <AuctionCard auctionCard={data} key={i}/>
          ))
        }
        />
    }
    </>
  )
}

export default NightProfileBuyingTab