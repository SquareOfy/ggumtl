
// map
// <ChallengeContentListItem></ChallengeContentListItem> ./daycommon 

// 리액트
import React, { useCallback, useEffect, useState } from "react";

// 컴포넌트
import ChalContentListItem from "../daycommon/ChalContentListItem";

// 스타일
export interface DayChallengeObj {
  title :string,
  period :string,
  challengeId :number
}

export interface DayChallengeList extends Array<DayChallengeObj> {}


const DayChallengeList = () => {
  const [allChalList, setAllChalList] = useState<DayChallengeList>([]);
  
  // .axios 연결 전 임의의 값을 allChalList에 넣어두기
  let newObj :DayChallengeObj = {
    title : "111",
    period : "1일",
    challengeId : 111,
  }
  let newObj2 :DayChallengeObj = {
    title : "222",
    period : "2일",
    challengeId : 222,
  }

  // 처음 렌더링 시 Challenge List axios 요청
  // axios로 받아오면 setAllChalList로 기존 배열에 새 배열 추가하기
  useEffect(() => {
    setAllChalList([newObj, newObj2, newObj2, newObj2, newObj2, newObj2, newObj2, newObj2,
      newObj2, newObj2, newObj2, newObj2, newObj2, newObj2
    ])
  }, [])


  // 무한 스크롤
  const [page, setPage] = useState<number>(1); // 무한 스크롤 요청할 페이지 번호 변수
  // const [posts, setPosts] = useState<postType[]>(getPostList(1));
  // posts 배열의 초기값은 페이지가 1인 객체들 입니다.
  // const [currentPage, setCurrentPage] = useState<number>(1);

  // 스크롤을 하면서 실행할 내용
  const handleScroll = useCallback((): void => {
    const { innerHeight } = window;
    // 브라우저창 내용의 크기 (스크롤을 포함하지 않음)
    
    const { scrollHeight } = document.body;
    // 브라우저 총 내용의 크기 (스크롤을 포함한다) - 사이즈 3rem정도 줄이기 (미리 axios 요청하도록)
    
    const { scrollTop } = document.documentElement;
    // 현재 스크롤바의 위치
    
    if (Math.round(scrollTop + innerHeight) >= scrollHeight*0.95 ) {
      // scrollTop과 innerHeight를 더한 값이 scrollHeight*0.95 보다 크다면, 가장 아래에 도달했다는 의미
      
      // setPosts(posts.concat(getPostList(page + 1)));
      setAllChalList([...allChalList, newObj])
      // 페이지에 따라서 불러온 배열을 합쳐줍니다. - axios 요청 필요
      
      setPage((prevPage: number) => prevPage + 1);
      // 페이지 state 변수의 값도 1씩 늘려줍니다.

    }
  }, [page, setAllChalList]);
  
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
    {
      allChalList?.map((chal :DayChallengeObj) => (
      <ChalContentListItem key={chal.challengeId} chal={chal} />))
    }
    </>
  )
}

export default DayChallengeList