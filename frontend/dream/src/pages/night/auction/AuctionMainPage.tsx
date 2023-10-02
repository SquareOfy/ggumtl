// 리액트
import React from "react";

// 컴포넌트
import AuctionMainList from "components/night/auction/AuctionMainList";
import NavAuctionBar from "components/common/NavAuctionBar";

// 스타일

const AuctionMainPage = () => {

  return (
    <>
    <NavAuctionBar>꿈 경매</NavAuctionBar>
    <AuctionMainList/>
    </>
  )
}

export default AuctionMainPage