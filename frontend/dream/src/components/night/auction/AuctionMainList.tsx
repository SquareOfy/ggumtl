{/* <SearchInput></SearchInput> */}

{/* <AuctionCard></AuctionCard> */}
{/* <AuctionCard></AuctionCard> */}
{/* <AuctionCard></AuctionCard> */}

// 리액트
import React, { useEffect, useState } from "react";

// 컴포넌트
import AuctionCard from "../nightcommon/AuctionCard";
import SearchBar from "components/common/SearchBar";
import Input from "style/Input";

// 외부 라이브러리
import axios from 'axios'

// 스타일

export interface AuctionMainType {
  color : string
  size: number
}

export interface AuctionItemAxiosType {
  dreamCardId: number;
  dreamCardImageUrl: string;
  keywords: string[];
  grade: string;
  positiveGrade: string;   
  rareGrade: string;
  endedAt: string;
  auctionStatus: string; 
}

export interface AuctionListType {
  aucionList : AuctionItemAxiosType[]
}

const AuctionMainList = () => {

  const [auctionList, setAuctionList] = useState<AuctionListType |null>(null)
  const size = 4

  useEffect(() => {
    // axios.get(`*/auction/list?size=${size}`)
    // .then((res)=> {
    //   setAuctionList(res.data.auctionList)
    // })
  }, [])
  // 무한 스크롤 부분


  return (
    <>
    <SearchBar onChange={()=>console.log("짠")} />
    <AuctionCard />
    </>
  )
}

export default AuctionMainList