
// .map
// CircleImage (프로필 이미지)
// onClick할 때 스토리로 가기. ~ Router {/* <DayStoryDetailPage></DayStoryDetailPage> */}

// 리액트
import React, { useState, useEffect } from "react";
import tokenHttp from "api/tokenHttp";

// 컴포넌트
import DayStoryDetail from "./DayStoryDetail";
import InfiniteScrollHorizon from "components/common/InfiniteScrollHorizon";

// 스타일
import styled from "styled-components";
import Image from "style/Image";

export interface StoryDataObjType {
  challengeId :number,
  challengeTitle :string,
  challengeDetailId :number,
  photoUrl :string,
  challengeDetailContent :string
}

export interface StoryDataListType extends Array<StoryDataObjType> {}


const DayStoryList = () => {
  // 스크롤 내리면서 받아올 새 리스트 
  const [storyDataList, setStoryDataList] = useState<StoryDataListType>([]);
  const [lastItemId, setLastItemId] = useState<number>(-1); // 마지막 아이템 번호
  let size = 8;

  // axios 연결 - 팔로우 유저 목록 조회
  const getAxios = () => {
    let apiAddress :string = "";

    // 처음 요청
    if (lastItemId === -1) {apiAddress = `/day/challenge/story/user-list?size=${size}`}
    // 두번째부터 요청
    else {apiAddress = `/day/challenge/story/user-list?lastItemId=${lastItemId}&size=${size}`}
    
    tokenHttp.get(apiAddress)
    .then((res) => {
      console.log(res)
      if (typeof res.data.data.resultList === "object") {
        setStoryDataList([...storyDataList, ...res.data.data.resultList]);
      }})
    .catch((err) => console.log("DayStoryList : ", err))  
  };

  useEffect(() => {
    getAxios();
  }, []);

  // StoryDataList에 새로운 내용이 들어오면 lastItemId 변경
  useEffect(() => {
    if (storyDataList) {
      storyDataList[storyDataList.length - 1] && 
      setLastItemId(storyDataList[storyDataList.length - 1].challengeDetailId)
      console.log(lastItemId)
    }
  }, [setStoryDataList, storyDataList])


  // 끝에 도달하면 새로운 데이터 추가
  const [arriveEnd, setArriveEnd] = useState<boolean>(false); // 바닥에 다다름을 알려주는 변수

  useEffect(() => {
    if (arriveEnd) {
      getAxios();
      setArriveEnd(false);
      // setLastItemId(newChalList[-1]["challengeId"]); // 마지막 item id 변경
    }
  }, [arriveEnd])

  // 스토리 모달 띄우기
  const [isOpenModal, setIsOpenModal] = useState<boolean>(false);

  const showStoryModal = () => {
    setIsOpenModal(true);
    console.log("유저 프로필 클릭");
  }


  return (
    <>
    {
      isOpenModal && 
      <DayStoryDetail 
      setIsOpenModal={setIsOpenModal} 
      isOpenModal={isOpenModal}
      ></DayStoryDetail>
    }
    <div style={{display: "-webkit-box", overflowX: "scroll"}}>

      <InfiniteScrollHorizon
      setArriveEnd={setArriveEnd} 
      // lastItemId={lastItemId}
      component={
        storyDataList?.map((chal :StoryDataObjType, key :number) => (
        <Image
          $smallProfileImage
          key={key}
          onClick={showStoryModal}
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