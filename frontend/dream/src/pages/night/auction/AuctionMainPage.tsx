// 리액트
import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import AuctionMainList from "components/night/auction/AuctionMainList";

// 스타일

const AuctionMainPage = () => {

  return (
    <>
    <NavTitleBar>꿈 경매</NavTitleBar>
    <AuctionMainList/>
    </>
  )
}

export default AuctionMainPage