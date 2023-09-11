{/* <SearchInput></SearchInput> */}

{/* <AuctionCard></AuctionCard> */}
{/* <AuctionCard></AuctionCard> */}
{/* <AuctionCard></AuctionCard> */}

// 리액트
import React from "react";

// 컴포넌트
import AuctionCard from "../nightcommon/AuctionCard";
import SearchBar from "components/common/SearchBar";

// 스타일

const AuctionMainList = () => {

  return (
    <>
    <SearchBar />

    <AuctionCard />
    </>
  )
}

export default AuctionMainList