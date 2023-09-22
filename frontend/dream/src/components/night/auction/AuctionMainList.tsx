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
import basicHttp from "api/basicHttp";

// 스타일
import Wrap from "style/Wrap";
import { useNavigate } from "react-router-dom";

export interface AuctionMainType {
  color : string
  size: number
}

export interface AuctionCardType {
  dreamCardId: number;
  dreamCardImageUrl: string;
  keywords: string[];
  grade: string;
  positiveGrade: string;   
  rareGrade: string;
  endedAt: string;
  auctionStatus: string; 
  auctionId: number;
}

export interface AuctionListType extends Array<AuctionCardType> {}

const AuctionMainList = () => {
  const [auctionList, setAuctionList] = useState<AuctionListType>([])
  const size = 10

  useEffect(() => {
    basicHttp.get(`/auction/list?size=${size}`)
    .then(res => {
      setAuctionList(res.data.data.list)
      console.log(res.data.data.list, "옥션리스트")
    })
  }, [])
  // 무한 스크롤 부분


  return (
    <>
      <SearchBar onChange={()=>console.log("짠")} />
      <Wrap $auctionCardWrap>
        {
          auctionList.map((item, idx) => (
            <AuctionCard auctionCard={item} key={item.auctionId} />
          ))
        }
      </Wrap>
    </>
  )
}

export default AuctionMainList