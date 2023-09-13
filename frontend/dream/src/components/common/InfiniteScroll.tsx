// 리액트
import React, { Dispatch, SetStateAction, useCallback, useEffect } from "react";

// 컴포넌트

// 스타일
export interface DayChallengeObj {
  title :string,
  period :string,
  challengeId :number
}

export interface DayChallengeList extends Array<DayChallengeObj> {}

export interface InfiniteScrollProps {
  children ?: any,
  setArriveEnd :Dispatch<SetStateAction<boolean>>,
  // lastItemId :number,
  component :any,
} 


const InfiniteScroll = ({
  setArriveEnd, 
  // lastItemId,
  component
} :InfiniteScrollProps) => {
  

  // 무한 스크롤

  // 스크롤을 하면서 실행할 내용
  const handleScroll = useCallback((): void => {
    const { innerHeight } = window;
    // 브라우저창 내용의 크기 (스크롤을 포함하지 않음)
    
    const { scrollHeight } = document.body;
    // 브라우저 총 내용의 크기 (스크롤을 포함한다)
    
    const scrollTop :any = document.documentElement.children[1].scrollTop;
    // 현재 스크롤바의 위치

    
    if (Math.round(scrollTop + innerHeight) >= scrollHeight*0.9 ) {
      // scrollTop과 innerHeight를 더한 값이 scrollHeight*0.95 보다 크다면, 가장 아래에 도달했다는 의미
      setArriveEnd(true);
      console.log('가장 아래에 도달')
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
    <>
      {component}
    </>
  )
}

export default InfiniteScroll