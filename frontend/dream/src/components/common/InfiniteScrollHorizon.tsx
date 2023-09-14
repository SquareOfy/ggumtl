// 리액트
import React, { Dispatch, SetStateAction, useCallback, useEffect } from "react";

// 컴포넌트

// 스타일
export interface DayChallengeObjType {
  title :string,
  period :string,
  challengeId :number
}

export interface DayChallengeListType extends Array<DayChallengeObjType> {}

export interface InfiniteScrollProps {
  children ?: any,
  setArriveEnd :Dispatch<SetStateAction<boolean>>,
  // lastItemId :number,
  component :any,
} 


const InfiniteScrollHorizon = ({
  setArriveEnd, 
  // lastItemId,
  component
} :InfiniteScrollProps) => {
  

  // 무한 스크롤

  // 스크롤을 하면서 실행할 내용
  const handleScroll = useCallback((event :any): void => {
    const scrollLeft = event.target.scrollLeft; // 스크롤 바의 위치 가져오기
    const clientWidth = document.documentElement.clientWidth // 화면 너비
    let wid = document.getElementById("component")?.clientWidth // component의 총너비
    
    if (wid) {
      if (Math.round(scrollLeft + clientWidth) >= wid * 0.9) {
        setArriveEnd(true);
        console.log('오른쪽 도달')
      }
    }
  }, []); // 성능 저하 시 lastItemId 넣어보기
  
  useEffect(() => {
    window.addEventListener('scroll', handleScroll, true);
    // 스크롤이 발생할때마다 handleScroll 함수를 호출하도록 추가
    
    return () => {
      window.removeEventListener('scroll', handleScroll, true);
      // 해당 컴포넌트가 언마운트 될때, 스크롤 이벤트를 제거
    };
  }, [handleScroll]);



  return (
    <div
    style={{display: "flex"}}
    id="component"
    >
      {component}
    </div>
  )
}

export default InfiniteScrollHorizon