// SearchBar
// 검색 입력하면 검색바는 상단

// DayCategoryList // daycommon 폴더

// 검색결과리스트.map
// ChalContentListItem

// 리액트
import React, { useState, useEffect } from 'react'

// 컴포넌트
import DayCategoryList, {
  CategoryAxiosType
} from '../daycommon/DayCategoryList'
import ChalContentListItem from '../daycommon/ChalContentListItem'
import DayChallengeList, {
  DayChallengeListType,
  DayChallengeObjType
} from '../home/DayChallengeList'
import Text from 'style/Text'
import InfiniteScroll from 'components/common/InfiniteScroll'

import tokenHttp from 'api/tokenHttp'

// 스타일

// 타입
interface SearchResultProps {
  searchWord: string
}

const DaySearchResultList = ({ searchWord }: SearchResultProps) => {
  // const [categoryProps, setCategoryProps] = useState<CategoryAxiosType>({keyword: '', keywordId: 0});

  const [allChalList, setAllChalList] = useState<DayChallengeListType>([])
  const [lastItemId, setLastItemId] = useState<number>(0)
  const [hasNext, setHasNext] = useState<boolean>(true)
  let size: number = 20

  // infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(true) // 바닥에 다다름을 알려주는 변수

  const getSearchAxios = () => {
    let apiAddress: string = ''
    // 100개 불러온 것 중 필터해서 검색 결과 보여줌
    apiAddress = `/day?size=${size}`
    // 에러 해결되어야 가능할듯
    // if (lastItemId === 0) {apiAddress = `/day?size=${size}`}
    // else{apiAddress = `/day?lastItemId=${lastItemId}&size=${size}`}

    tokenHttp
      .get(apiAddress)
      .then((res) => {
        const response = res.data.data
        console.log('챌린지 검색 데이터 가져오기 성공', response)
        const challengeList = response.challengeList
        setLastItemId(challengeList[challengeList.length - 1].challengeId)

        // 챌린지 리스트 필터링
        // 검색 필터링에 카테로리, 내용을 넣으면 되지 않을까
        const searchResultList = challengeList.filter(
          (chal: DayChallengeObjType) => {
            if (searchWord) {
              return chal.title?.includes(searchWord)
            } else {
              return true
            }
          }
        )

        setAllChalList(searchResultList)
        setHasNext(response.hasNext)
      })
      .catch((err) => console.log('챌린지 검색 데이터 가져오기 실패', err))
  }

  useEffect(() => {
    getSearchAxios()
    console.log(searchWord)
  }, [searchWord])

  useEffect(() => {
    if (arriveEnd) {
      getSearchAxios()
      setArriveEnd(false)
    }
  }, [arriveEnd])

  return (
    <>
      {/* <DayCategoryList setCategoryProps={setCategoryProps} /> */}
      <Text $isBold $daySearchText>
        검색 결과
      </Text>
      {allChalList &&
        allChalList
          // // 카테고리
          // 카테고리 없는게 나을지도..?
          // 검색 필터링에 애초에 카테고리까지 넣으면 되지 않으려나
          // .filter((chal: DayChallengeObjType) => {
          //   if (categoryProps.keywordId !== 0) {
          //     return chal.dreamKeywordId === categoryProps.keywordId
          //   } else {
          //     return true
          //   }
          // })
          .map((chal: DayChallengeObjType) => (
            <ChalContentListItem key={chal.challengeId} chal={chal} />
          ))}
    </>
  )
}
export default DaySearchResultList
