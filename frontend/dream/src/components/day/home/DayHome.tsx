
// 스토리
// <DayStoryList></DayStoryList>

// 카테고리 (textdiv)
//<DayCategoryList></DayCategoryList> in ./daycommon

// Hot Challenge(textdiv)
//<DayChallengeList></DayChallengeList>

// 리액트
import React, { useState } from "react";

// 컴포넌트
import DayStoryList from "./DayStoryList";
import DayCategoryList, { CategoryAxiosType } from "../daycommon/DayCategoryList";
import DayChallengeList from "./DayChallengeList";
import Text from "style/Text";

// 스타일

const DayHome = ()=> {
  const [categoryProps, setCategoryProps] = useState<CategoryAxiosType>({keyword: '', keywordId: 0});

  return (
    <>
    {/* 스토리 */}
    <DayStoryList />

    {/* 카테고리 */}
    <Text $isBold $dayHomeText>카테고리</Text>
    <DayCategoryList setCategoryProps={setCategoryProps}/>

    {/* HotChalllenge Arr */}
    <Text $isBold $dayHomeText>Hot Challenge</Text>
    <DayChallengeList categoryProps={categoryProps}/>
    
    </>
  )
}

export default DayHome;