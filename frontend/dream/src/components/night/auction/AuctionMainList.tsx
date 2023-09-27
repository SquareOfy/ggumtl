// 리액트
import React, { useEffect, useState } from "react";

// 컴포넌트
import AuctionCard from "../nightcommon/AuctionCard";
import SearchBar from "components/common/SearchBar";
import Input from "style/Input";

// 외부 라이브러리
import tokenHttp from "api/tokenHttp";

// 스타일
import styled from "styled-components";
import Wrap from "style/Wrap";
import { useNavigate } from "react-router-dom";
import InfiniteScroll from "components/common/InfiniteScroll";

export interface AuctionMainType {
  color : string
  size: number
}

export interface AuctionCardType {
  dreamCardId: number;
  dreamCardImageUrl: string;
  keywords: string[];
  grade: string;
  positiveGrade?: string;   
  positivePoint?: string;
  rareGrade?: string;
  rarePoint?: string;
  endedAt: string;
  auctionStatus: string; 
  auctionId: number;
}

export interface AuctionListType extends Array<AuctionCardType> {}

const MT5 = styled.div`
  margin-top: 1rem;
`

const AuctionMainList = () => {
  const [auctionList, setAuctionList] = useState<AuctionListType>([]);
  const [lastItemId, setLastItemId] = useState<number>(-1);
  const size = 6;

  // axios 요청
  const getAxios = () => {
    let apiAddress :string = "";
    
    // 처음 요청 시
    if (lastItemId === -1) {apiAddress = `/auction/list?size=${size}`}
    // 두번째부터 요청
    else {apiAddress = `/auction/list?lastItemId=${lastItemId}&size=${size}`}
    
    tokenHttp.get(apiAddress)
    .then(res => {
      if (typeof res.data.data.list === "object") {
        setAuctionList([...auctionList, ...res.data.data.list])
        console.log(res.data.data.list, "옥션리스트")
      }
    })
  }

  useEffect(() => {
    getAxios();
  }, [])

  // lastItemId 업데이트
  useEffect(() => {
    auctionList[auctionList.length - 1] &&
    setLastItemId(auctionList[auctionList.length - 1].auctionId)
    console.log('lastItem : ', lastItemId)
  }, [setAuctionList, auctionList])


  // 무한 스크롤 부분
  const [arriveEnd, setArriveEnd] = useState<boolean>(false);

  // 바닥에 다다랐으면 axios 요청
  useEffect(() => {
    if (arriveEnd) {
      getAxios();
      setArriveEnd(false);
    }
  }, [arriveEnd])



  return (
    <>
      {/* <SearchBar onChange={()=>console.log("짠")} /> */}
      <MT5/>
      <Wrap $auctionCardWrap>
        {
          auctionList &&
          <InfiniteScroll 
          setArriveEnd={setArriveEnd}
          component={
            auctionList.map((item, idx) => (
              <AuctionCard auctionCard={item} key={item.auctionId} />
            ))}
          />
        }
      </Wrap>
    </>
  )
}

export default AuctionMainList