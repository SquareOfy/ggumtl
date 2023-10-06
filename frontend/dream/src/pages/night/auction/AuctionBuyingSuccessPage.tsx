// 리액트
import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import AuctionReview from "components/night/auction/AuctionReview";
// 스타일

const AuctionBuyingSuccessPage = () => {

  return (
    <>
    <NavTitleBar>꿈 카드 리뷰</NavTitleBar>
    {/* <AuctionBuying /> */}
    <AuctionReview/>
    </>
  )
}

export default AuctionBuyingSuccessPage