// 리액트
import React, {useEffect, useState} from "react";

// 외부 라이브러리 파일
import axios from "axios";
import { baseUrl } from "api/api";

// 컴포넌트
import NightHomeItem from "./NightHomeItem";


// 타입
export interface NightHomeItemType {
  dreamCardId: number;
  dreamCardOwner: number;
  ownerNickname: string;
  ownerProfileUrl: string;
  dreamCardAuthor: number; // 꿈 카드 작성자
  createAt: string;
  likedNumber: number,
  like: boolean;
}

export interface NightHomeListType {
  [index: number] : NightHomeItemType[]
}

const NightHomeList = () => {
  const initNightHomeDataSet :NightHomeItemType = {
      dreamCardId: 0,
      dreamCardOwner: 0,
      ownerNickname: "",
      ownerProfileUrl: "",
      dreamCardAuthor: 0, // 꿈 카드 작성자
      createAt: "",
      likedNumber: 0,
      like: false,  
    }
  const [nightHomeDataSet, setNightHomeDataSet] = useState<any>([initNightHomeDataSet])

  useEffect(() => {
    axios.get(`${baseUrl}/night/?size=${6}`)
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