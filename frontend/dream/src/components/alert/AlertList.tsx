// .map <AlertItem/>

// 리액트
import React, { useEffect, useRef, useState } from "react";

// 컴포넌트
import AlertItem from "./AlertItem";
import InfiniteScroll from "components/common/InfiniteScroll";
import tokenHttp from "api/tokenHttp";
import styled from "styled-components";

// 스타일
export interface AlertListAxiosType {
  notificationId :number,
  createdAt :string,
  type :string,
  referenceId :number,
  title :string,
  content :string,
  isRead :string
}

const AlertListWrap = styled.div`
  background-color: white;
  opacity: 0.5;
  position: fixed;
  width: 100%;
  height: 100%;
  z-index: -1;
`

const AlertList = () => {
  const [alertList, setAlertList] = useState<AlertListAxiosType[]>([]);
  const [arriveEnd, setArriveEnd] = useState<boolean>(true); // 바닥에 다다름을 알려주는 변수
  const [lastItemId, setLastItemId] = useState<number>(0);
  const [hasNext, setHasNext] = useState<boolean>(true); 
  let size = 12;
  const apiAddressRef = useRef<string>("")
  const getAxios = () => {
    let apiAddress :string = "";

    // 처음 요청 받을 때
    if (lastItemId === 0) {apiAddressRef.current = `/notification/list?size=${size}`}
    // 두번째부터 요청 할 때
    else {apiAddressRef.current = `/notification/list?lastItemId=${lastItemId}&size=${size}`}
    
    if (arriveEnd && hasNext) {  // 끝에 도달하고 다음이 있을 때 다음 데이터 호출
      console.log(apiAddressRef.current, "현재")
      tokenHttp.get(apiAddressRef.current)
      .then((res)=>{
        const response = res.data.data
        const auctionList = response.auctionList
        setAlertList([...alertList, ...auctionList]);
        setLastItemId(auctionList[auctionList.length - 1].dreamCardId);
        setHasNext(response.hasNext);
        console.log("== 알람 리스트 ==", res); 
      })
      .catch((err) => console.log("== 알람 리스트 에러 ==", err))
    }
  }

  useEffect(() => {
    getAxios();
  }, []);

  const sampleData = {
    notificationId : 1,
    createdAt : "12:12",
    type : "AUCTION",
    referenceId : 1,
    title : "AUCTION TITLE 1",
    content : "CONTENT 1",
    isRead : "T"
  }

  return (
    <>
    <AlertListWrap/>

      {/* <InfiniteScroll
      setArriveEnd={setArriveEnd} 
      // component={
      //   alertList?.map((data, i) => (
      //     <AlertItem data={data} key={i}/>
      //   ))
      // }
      component={<AlertItem data={sampleData}/>}
      >

      </InfiniteScroll> */}
      <AlertItem data={sampleData} />
      <AlertItem data={sampleData} />
      <AlertItem data={sampleData} />
      <AlertItem data={sampleData} />
      <AlertItem data={sampleData} />
      <AlertItem data={sampleData} />
      <AlertItem data={sampleData} />
      <AlertItem data={sampleData} />
    </>
  )
}

export default AlertList