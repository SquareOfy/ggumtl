// 리액트
import React, { useEffect, useState } from "react";

// 컴포넌트
import AuctionCard from "../nightcommon/AuctionCard";
import basicHttp from "api/basicHttp";
import InfiniteScroll from "components/common/InfiniteScroll";
import { AuctionCardType } from "../auction/AuctionMainList";

// 스타일
// export interface AuctionBuyingAxiosType {
//   dreamCardId :number,
//   dreamCardImageUrl :string, 
//   positivePoint :string,
//   rarePoint :string,
//   keywords :string[],
//   endedAt :string,
//   auctionStatus :string,
// }


const NightProfileBuyingTab = () => {

  // axios로 데이터 받기
  // const [auctionBuyingDataList, setAuctionBuyingDataList] = useState<AuctionBuyingAxiosType[]>();
  const [auctionBuyingDataList, setAuctionBuyingDataList] = useState<AuctionCardType[]>();
  const [lastItemId, setLastItemId] = useState<number>(0);
  let size = 12;
  
  const getAxios = () => {
    basicHttp.get(`/profile/night/auction/participation/list?lastItemId=${lastItemId}&$size={size}`)
    .then((res) => {
      console.log("== 꿈 받기 탭 ==", res); 
      setAuctionBuyingDataList(res.data.data.auctionList);
      // setLastItemId(auctionBuyingDataList[-1]["challengeId"]); // 마지막 item id 변경
    })
    .catch((err) => console.log("== 꿈 받기 탭 ==", err))
  }

  useEffect(() => {
    getAxios();
  }, []);

  // infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(false); // 바닥에 다다름을 알려주는 변수

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
      auctionBuyingDataList &&
      <InfiniteScroll 
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