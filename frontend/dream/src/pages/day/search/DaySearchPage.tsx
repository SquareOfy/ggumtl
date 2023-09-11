{/* <SearchBar></SearchBar> */}

// 2개 손바뀜
{/* <DaySearchMain></DaySearchMain> */}
{/* <DaySearchResultList></DaySearchResultList> */}
// 리액트
import React from "react";

// 컴포넌트
import SearchBar from "components/common/SearchBar";
import DaySearchMain from "components/day/search/DaySearchMain";
import DaySearchResultList from "components/day/search/DaySearchResultList";

// 스타일

const DaySearchPage = () => {

  return (
    <>
    <SearchBar/>
    <DaySearchMain/>
    </>
  )
}

export default DaySearchPage