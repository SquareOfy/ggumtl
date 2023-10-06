// list.map
// <ChalCommentItem></ChalCommentItem>

// CircleImage :댓글프로필 -> 하단바 사라짐.
// Input // end에 게시 , placeholder

// 리액트
import React, { useEffect, useState, Dispatch, SetStateAction } from 'react'

// 컴포넌트
import ChalCommentItem from './ChalCommentItem'
import Container from 'style/Container'
import Input from 'style/Input'
import tokenHttp from 'api/tokenHttp'
import InfiniteScroll from 'components/common/InfiniteScroll'
import { useParams } from 'react-router-dom'

// 스타일

// 타입
export interface ChalCommentAxiosType {
  commentId: number
  userId: number
  nickname: string
  content: string
  profileImageUrl: string
}

interface ChalCommentListProps {
  newCommentSignal: boolean
  setNewCommentSignal: Dispatch<SetStateAction<boolean>>
}

const ChalCommentList = ({
  newCommentSignal,
  setNewCommentSignal
}: ChalCommentListProps) => {
  const params = useParams()
  const challengeId = params.challengeId
  const challengeDetailId = params.challengeDetailId

  const [commentList, setCommentList] = useState<ChalCommentAxiosType[]>([])
  const [lastItemId, setLastItemId] = useState<number>(0)
  const [hasNext, setHasNext] = useState<boolean>(true)

  // infinite scroll
  const [arriveEnd, setArriveEnd] = useState<boolean>(false) // 바닥에 다다름을 알려주는 변수

  const getAxios = () => {
    let apiAddress: string = ''

    if (lastItemId === 0) {
      apiAddress = `/day/challenge/detail/${challengeDetailId}/comment?size=12`
    } else {
      apiAddress = `/day/challenge/detail/${challengeDetailId}/comment?lastItemId=${lastItemId}&size=7`
    }

    if (arriveEnd && hasNext) {
      console.log(apiAddress)
      tokenHttp
        .get(apiAddress)
        .then((response) => {
          const res = response.data.data
          // 댓글리스트에 댓글이 존재할 때는 아래 api, 없을 땐 위의 api
          if (commentList[commentList.length - 1]) {
            setLastItemId(Number(commentList[commentList.length - 1].commentId))
          }
          setCommentList([...commentList, ...res.resultList])
          setHasNext(res.hasNext)
          console.log('댓글 무한스크롤 성공', res)
        })
        .catch((err) => {
          console.log('댓글 무한 스크롤 실패', err)
        })
    }
  }

  // 댓글 바로 추가 되게 하는 부분
  useEffect(() => {
    tokenHttp
      .get(`/day/challenge/detail/${challengeDetailId}/comment?size=1`)
      .then((response) => {
        const res = response.data.data.resultList
        // 시그널 바뀔 때마다 호출되어서 true일때만 호출
        if (newCommentSignal) {
          setCommentList([...res, ...commentList])
          setNewCommentSignal(false)
        }
        console.log('댓글 한개 추가', res)
      })
      .catch((err) => {
        console.log('댓글 한개 추가 실패', err)
      })
  }, [newCommentSignal])

  useEffect(() => {
    if (arriveEnd) {
      getAxios()
      setArriveEnd(false)
    }
  }, [arriveEnd])

  return (
    <>
      <Container $commentListContainer>
        {/* 댓글 리스트 */}
        {commentList && (
          <InfiniteScroll
            setArriveEnd={setArriveEnd}
            // lastItemId={lastItemId}
            component={commentList?.map(
              (item: ChalCommentAxiosType, idx: number) => (
                <ChalCommentItem commentData={item} key={idx} />
              )
            )}
          />
        )}
      </Container>
    </>
  )
}

export default ChalCommentList
