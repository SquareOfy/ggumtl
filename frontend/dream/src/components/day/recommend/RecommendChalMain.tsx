// 텍스트
// 어떤 도전을 하고 싶으신가요?
// 함께 찾아봐요.

// 리액트
import tokenHttp from "api/tokenHttp";
import React, { useEffect, useState } from "react";
import Text from "style/Text";
import RecommendChalItem from "./RecommendChalItem";
import Container from "style/Container";

// 컴포넌트

// 스타일

//slick
import Slider, {Settings} from 'react-slick'
import 'slick-carousel/slick/slick.css'
import 'slick-carousel/slick/slick-theme.css'

// 타입
export interface RecommendObjType {
  challengeId: number,
  title: string,
  period: string,
  participateCount: number,
  challengeKeywordId: number,
  badgeUrl: string
}

export interface RecommendListType extends Array<RecommendObjType> { }



const RecommendChalMain = () => {

  const settings :Settings = {
    dots: true,
    infinite: true,
    speed: 500,
  }

  const [recommendList, setRecommendList] = useState<RecommendListType>([])

  // 테스트 데이터
  const testRecommendList: RecommendListType = [
    {
      challengeId: 1,
      title: "제목1",
      period: "7 days",
      participateCount: 0,
      challengeKeywordId: 1,
      badgeUrl: "badge1.jpg"
    },
    {
      challengeId: 2,
      title: "제목2",
      period: "365 days",
      participateCount: 0,
      challengeKeywordId: 1,
      badgeUrl: "badge1.jpg"
    },
    {
      challengeId: 3,
      title: "제목3",
      period: "7 days",
      participateCount: 0,
      challengeKeywordId: 1,
      badgeUrl: "badge1.jpg"
    },
    {
      challengeId: 4,
      title: "제목4",
      period: "7 days",
      participateCount: 0,
      challengeKeywordId: 1,
      badgeUrl: "badge1.jpg"
    },
  ]


  useEffect(() => {
    tokenHttp.get('/day/recommend')
      .then((res) => {
        console.log(res.data)
        if (res.data.status === 400) {
          console.log(res.data.data)
        }
        else if (res.data.status === 200) {
          setRecommendList(res.data.data)
        }
      })
      .catch((err) => console.log(err))
  }, [])

  return (
    <>
      {/* 글자들 */}
      <Text $dayWhite $recommendTitle>추천 챌린지</Text>
      <Container>
      <Slider {...settings}>
        {
          testRecommendList.map((chal, idx) => (
            <div key={idx}>
              <RecommendChalItem chal={chal} />
            </div>
          ))
        }
      </Slider>
      </Container>

    </>
  )
}

export default RecommendChalMain