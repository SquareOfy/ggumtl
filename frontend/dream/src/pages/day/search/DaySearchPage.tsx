{/* <SearchBar></SearchBar> */}

// 2개 손바뀜
{/* <DaySearchMain></DaySearchMain> */}
{/* <DaySearchResultList></DaySearchResultList> */}
// 리액트
import React,{useState} from "react";

// 컴포넌트
import SearchBar from "components/common/SearchBar";
import DaySearchMain from "components/day/search/DaySearchMain";
import DaySearchResultList from "components/day/search/DaySearchResultList";
import Container from "style/Container";

// 스타일

const DaySearchPage = () => {

  const [searchWord, setSearchWord] = useState<string>('')

  return (
    <>
    <Container $dayBaseContainer>
      {searchWord==='' ? (<DaySearchMain/>):(null)}
      <SearchBar setSearchWord={setSearchWord}/>
    </Container>
    {searchWord==='' ? (null):(<DaySearchResultList searchWord={searchWord}/>)}
    

    </>
  )
}

export default DaySearchPage