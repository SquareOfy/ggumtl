{/* <SearchBar></SearchBar> */}

// 바뀌는 2가지
{/* <NightSearchMain></NightSearchMain> */}
{/* <NightSearchResultList></NightSearchResultList> */}

// 리액트
import React from "react";

// 컴포넌트
import SearchBar from "components/common/SearchBar";
import NightSearchMain from "components/night/search/NightSearchMain";
import NightSearchResultList from "components/night/search/NightSearchResultList";

// 스타일

const NightSearchPage = () => {

  return (
    <>
    <SearchBar />
    </>
  )
}

export default NightSearchPage