// 오직 검색 결과 리스트만.
// 여기에 제목 // tooltip되는 문구

// 리액트
import tokenHttp from 'api/tokenHttp'
import React, { useEffect, useState } from 'react'
import NightSearchResultItem from './NightSearchResultItem'

// 컴포넌트

// 스타일

// 타입
interface SearchResultProps {
  searchWord: string
}

export interface SearchResultObjType {
  dream: string
  dreamTelling: string
}

const NightSearchResultList = ({ searchWord }: SearchResultProps) => {
  const [resultList, setResultList] = useState<SearchResultObjType[]>([])
  const [resultStatus, setResultStatus] = useState<Number>(400)
  useEffect(() => {
    console.log(searchWord)
    tokenHttp
      .get(`/night/dream/interpretation?keyword=${searchWord}`)
      .then((res) => {
        console.log(res.data)
        setResultList(res.data.data)
        setResultStatus(res.data.status)
      })
      .catch((err) => console.log(err))
  }, [searchWord])

  return (
    <>
      {resultStatus === 200 ? (
        resultList.map((item, idx) => (
          <NightSearchResultItem item={item} key={idx} />
        ))
      ) : (
        <div>검색 내용이 없습니다.</div>
      )}
    </>
  )
}

export default NightSearchResultList
