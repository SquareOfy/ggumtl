
// .map
// CircleImage (프로필 이미지)
// onClick할 때 스토리로 가기. ~ Router {/* <DayStoryDetailPage></DayStoryDetailPage> */}

// 리액트
import InfiniteScrollHorizon from "components/common/InfiniteScrollHorizon";
import React, { useState, useEffect } from "react";

// 컴포넌트
import Image from "style/Image";

// 스타일
import styled from "styled-components";

export interface StoryDataObjType {
  challengeId :number,
  challengeTitle :string,
  challengeDetailId :number,
  photoUrl :string,
  challengeDetailContent :string
}

export interface StoryDataListType extends Array<StoryDataObjType> {}


const DayStoryList = () => {
  const [arriveEnd, setArriveEnd] = useState<boolean>(false); // 바닥에 다다름을 알려주는 변수

  // 샘플 데이터
  const [sampleDataList, setSampleDataList] = useState<StoryDataListType>([]);
  let sample = {
    challengeId : 11,
    challengeTitle : "11",
    challengeDetailId : 11,
    photoUrl : "http://sanriokorea.co.kr/wp-content/themes/sanrio/images/new_main_crt5_ov.png",
    challengeDetailContent : "123"
  }
  
  let newSample = {
    challengeId : 11,
    challengeTitle : "11",
    challengeDetailId : 11,
    photoUrl : "http://sanriokorea.co.kr/wp-content/themes/sanrio/images/new_main_crt2_ov.png",
    challengeDetailContent : "123"
  }

  useEffect(() => {
    setSampleDataList([
      sample, sample, sample, sample, 
      sample, sample, sample, sample, 
      sample, sample, 
    ])
  }, [])
  


  return (
    <>
    <div style={{display: "-webkit-box", overflowX: "scroll"}}>

      <InfiniteScrollHorizon
      setArriveEnd={setArriveEnd} 
      // lastItemId={lastItemId}
      component={
        sampleDataList?.map((chal :StoryDataObjType, key :number) => (
        <Image
          $smallProfileImage
          key={key}
          >
            <img src={sample.photoUrl}></img>
          </Image>
        ))
      }
      >
      </InfiniteScrollHorizon>
    </div>
    </>
  )
}

export default DayStoryList