// 리액트
import React, {useEffect, useState} from "react";

// 외부 라이브러리 파일
import axios from "axios";
import basicHttp from "api/basicHttp";

// 컴포넌트
import NightHomeItem from "./NightHomeItem";


// 타입
export interface NightHomeItemType {
  dreamCardId: number;
  dreamCardOwner: number;
  ownerNickname: string;
  ownerProfileUrl: string;
  dreamCardImageUrl: string;
  dreamCardAuthor: number; // 꿈 카드 작성자
  createAt: string;
  likedNumber: number,
  like: boolean;
}

export interface NightHomeListType extends Array<NightHomeItemType>{}

const NightHomeList = () => {

  const [nightHomeDataSet, setNightHomeDataSet] = useState<NightHomeListType>([])

  useEffect(() => {
    basicHttp.get(`/night/?size=${6}`)
    .then((res)=> {
      setNightHomeDataSet(res.data.data.list)
      // setNightHomeDataSet((prev:any) => {
      //   [...prev, ...res.data.list]
      // })
      // console.log(res.data.data.list)
    })
    .catch(err=>console.log(err))
  }, [])
  return (
    <>
    {
      nightHomeDataSet.map((item: NightHomeItemType, idx:number) => (
        <NightHomeItem cardData={item} key={idx}/>
      )
    )}
    </>
  )
}

export default NightHomeList