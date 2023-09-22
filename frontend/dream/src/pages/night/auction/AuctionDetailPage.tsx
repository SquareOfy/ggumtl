// 리액트
import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import AuctionDetail from "components/night/auction/AuctionDetail";
// 스타일

const AuctionDetailPage = () => {

  return (
    <>
    <NavTitleBar>꿈 경매장</NavTitleBar>
    <AuctionDetail/>
    </>
  )
}

export default AuctionDetailPage