
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
  // 스크롤 내리면서 받아올 새 리스트 
  const [newStoryDataList, setNewStoryDataList] = useState<StoryDataListType>([]);
  const [lastItemId, setLastItemId] = useState<number>(0); // 마지막 아이템 번호

  // 샘플 데이터
  const [sampleDataList, setSampleDataList] = useState<StoryDataListType>([]);
  let sample = {
    challengeId : 11,
    challengeTitle : "11",
    challengeDetailId : 11,
    photoUrl : "http://sanriokorea.co.kr/wp-content/themes/sanrio/images/new_main_crt5_ov.png",
    challengeDetailContent : "111"
  }
  
  let newSample = {
    challengeId : 22,
    challengeTitle : "22",
    challengeDetailId : 22,
    photoUrl : "http://sanriokorea.co.kr/wp-content/themes/sanrio/images/new_main_crt2_ov.png",
    challengeDetailContent : "222"
  }

  useEffect(() => {
    setSampleDataList([
      sample, sample, sample, sample, 
      sample, sample, sample, sample, 
      sample, sample, 
    ])
  }, [])
  // 여기까지 샘플 데이터 추가하는 코드

  // 끝에 도달하면 새로운 데이터 추가
  useEffect(() => {
    if (arriveEnd) {
      // axios 요청하는 동작 추가
      setSampleDataList([...sampleDataList, newSample])
      setArriveEnd(false);
      // setLastItemId(newChalList[-1]["challengeId"]); // 마지막 item id 변경
    }
  }, [arriveEnd])


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
            <img src={chal.photoUrl}></img>
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