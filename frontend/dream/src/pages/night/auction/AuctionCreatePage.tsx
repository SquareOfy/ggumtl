// 리액트
import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import AuctionCreate from "components/night/auction/AuctionCreate";

// 스타일

const AuctionCreatePage = () => {

  return (
    <>
    <NavTitleBar />
    <AuctionCreate/>
    </>
  )
}

export default AuctionCreatePage