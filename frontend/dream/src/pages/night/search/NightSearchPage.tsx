{/* <SearchBar></SearchBar> */}

// 바뀌는 2가지
{/* <NightSearchMain></NightSearchMain> */}
{/* <NightSearchResultList></NightSearchResultList> */}

// 리액트
import React, {useState} from "react";

// 컴포넌트
import SearchBar from "components/common/SearchBar";
import NightSearchMain from "components/night/search/NightSearchMain";
import NightSearchResultList from "components/night/search/NightSearchResultList";
import Container from "style/Container";

// 스타일

const NightSearchPage = () => {

  const [searchWord, setSearchWord] = useState<string>('')

  return (
    <>
     <>
    <Container $dayBaseContainer>
      {searchWord==='' ? (<NightSearchMain/>):(null)}
      <SearchBar setSearchWord={setSearchWord}/>
    </Container>
    {searchWord==='' ? (null):(<NightSearchResultList searchWord={searchWord}/>)}
    

    </>
    </>
  )
}

export default NightSearchPage