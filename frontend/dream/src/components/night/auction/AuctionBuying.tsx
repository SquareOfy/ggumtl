// 작은 이미지
// 호가 버튼
// 보유 꿈머니
// input - 참여버튼
// 경고문구

// 2개 텍스트 박스(AuctionDetail 복붙)
// 리액트
import React, {useEffect, useState, useRef} from "react";
import { useSelector } from 'react-redux'
import { useParams } from "react-router-dom";

// 컴포넌트
import Button from "components/common/Button";

// 스타일
import styled, {css} from "styled-components";
import Image from "style/Image";
import Container from "style/Container";
import Text from "style/Text";
import Input from "style/Input";
import { RootState } from "store";

// 외부
// 웹소캣
import { Client, Message, Stomp } from "@stomp/stompjs";
import StompJs from '@stomp/stompjs';
import websocket from "websocket"
import { WebSocket, WebSocketServer  } from "ws";

// 범선
import SockJS from 'sockjs-client';
import tokenHttp from "api/tokenHttp";

// Object.assign(global, {WebSocket: websocket.w3cwebsocket})
// Object.assign(global, {WebSocket})


// // push 알림
// import { getMessaging, onMessage } from 'firebase/messaging';
// import { requestPermission } from 'utils/alert/notification';
// import { FCM_VALID_KEY } from 'ignore/Ignore';


const AuctionBidContainer = styled.div`
  margin: 1rem 3rem;

 & :first-child {
  padding-left: 1rem;
 }

 /* & :last-child {
  padding-left: 1rem;
 } */
`

const BiddingWrap = styled.div`
  display: grid;
  grid-template-columns: 75% 25%;
  grid-gap: 1vw;
  margin-top: 1vw;
  margin-bottom: 1vw;
`

// 타입
interface AuctionBuyingProps {
  biddingMoney: number;
  askingMoney: number;
  updateValue: (data:any) => void
}


const AuctionBuying = ({biddingMoney, askingMoney, updateValue} :AuctionBuyingProps) => {
  const userdata = useSelector((state: RootState) => state.auth.userdata);
  const {auctionId} = useParams()
  const accessToken = sessionStorage.getItem('accessToken')

  const userId = useSelector((state:RootState) => state.auth.userdata.userId)

  const clientRef = useRef<Client|null>(null)

  // const biddingMoney :number = 5000 // 서버에서 받을 값
  const [myBiddingMoney, setMyBiddingMoney] = useState<number>(biddingMoney)
  const [currentAskingMoney, setAskingMoney] = useState<number>(askingMoney)

  const [point, setPoint] = useState(userdata.point)


  const headers = {
    login: 'mylogin',
    passcode: 'mypasscode',
    Authorization: "Bearer " + accessToken
}

  useEffect(() => {
    // tokenHttp.get(`/auction/point/${33}`)
    tokenHttp.get(`/auction/point/${userdata.userId}`)
    .then(res => {
      // console.log(res.data.data.point, "머니머니")
      setPoint(res.data.data.point)
    })
    console.log("초기 렌더링 #########")
      // 웹소캣(2)
  const socket = new SockJS("https://j9b301.p.ssafy.io:9090/ws-stomp")

  // 함수화 필수
  clientRef.current = Stomp.over(() => {
    return socket
  })
  // client.connectHeaders = {
  //   Authorization: "Bearer " + accessToken
  // }
  clientRef.current.connectHeaders = headers
  clientRef.current.reconnectDelay=1000 //자동재연결
  clientRef.current.heartbeatIncoming=4000
  clientRef.current.heartbeatOutgoing=4000
  clientRef.current.onConnect = (frame) => {
    if (clientRef.current) {
      clientRef.current.subscribe(`/sub/auction/${Number(auctionId)}`, (msg)=> {
        console.log(msg, "너 왔니? 메세지")
        const newBody = JSON.parse(msg.body)
        const biddingMoney = newBody.biddingMoney
        setMyBiddingMoney(biddingMoney)
        const biddingCount = newBody.biddingCount
        const tmpTime = new Date(newBody.createdAt)
        
        const biddingTime = tmpTime.setHours(tmpTime.getHours()+9)
        const data = {biddingMoney, biddingCount, biddingTime}
        updateValue(data)
        return () => {
          if (clientRef.current) {
            clientRef.current.unsubscribe(`/sub/auction/${Number(auctionId)}`)
          }
        }
      }
    )}
  }
  clientRef.current.debug = (str) => {console.log("디버그:", str)}

  clientRef.current.activate()
    return () => {
      if (clientRef.current) clientRef.current.deactivate()
    }
  }, [])

  // 숫자만 입력 받는 정규식
  const numberCheck = /^[0-9]+$/;

  const [lowerMoney, setLowerMoney] = useState<boolean>(false)
  const [lackMoney, setLackMoney] = useState<boolean>(false)
  // 추후 호가 몫만 들고오기
  const changeBiddingMoney = (e :any) => {
    if (!numberCheck.test(e.currentTarget.value)) {
      console.log("숫자 아니에요.")
      return
    }
    if (e.currentTarget.value < biddingMoney) {
      console.log("현재 가격보다 낮음")
      setMyBiddingMoney(biddingMoney)
      setLowerMoney(true)
      setLackMoney(false)
      return
    } else if (e.currentTarget.value > point) {
      console.log("보유액 부족")
      setMyBiddingMoney(biddingMoney) // 여기 최초가로
      setLowerMoney(false)
      setLackMoney(true)
      return
    }
    setMyBiddingMoney(e.currentTarget.value)
    setLowerMoney(false)
    setLackMoney(false)
    
  }
  
  const addBiddingMoney = () => {
    setMyBiddingMoney(() => Number(myBiddingMoney)+Number(currentAskingMoney))
  }

  const sendBiddingMoney = () => {
    const msgBody = {
      auctionId: Number(auctionId),
      biddingMoney: Number(myBiddingMoney),
      userId,
      askingMoney
    }
    if (clientRef.current) {
      clientRef.current.publish({ destination: "/pub/auction/bidding", body: JSON.stringify(msgBody)})
      // clientRef.current.send("/pub/auction/bidding", {}, JSON.stringify(msgBody))
    }
    // console.log("전송완료", msgBody)
  }

  return (
    <>
      {/* 호가 */}
      <Container $centerContainer>
        <Button $nightPalePurple $biddingBtn
        onClick={addBiddingMoney}
        ><Text $black $isBold>+{currentAskingMoney}</Text></Button>
      </Container>

      <AuctionBidContainer>
        <Text $nightKeword $nightWhite>나의 꿈머니: ${point}</Text>
        <BiddingWrap>
          <Input $nightColor $biddingValue 
          type="number"
          onChange={(e)=>setMyBiddingMoney(e.currentTarget.value)}
          onBlur={changeBiddingMoney}
          value={myBiddingMoney}
          />
          {/* 참여시 무르기 불가함을 고지 */}
          {/* lackMoney, lowerMoney 유효성 검사 통과 이후에만 */}
          <Button $nightMiddlePurple $biddingBtn
          onClick={sendBiddingMoney}
          >참여</Button>
        </BiddingWrap>
        { (!lowerMoney && !lackMoney ) && <Text $nightKeword $nightWhite>한 번 참여 후 취소할 수 없습니다.</Text> }
        { lowerMoney && <Text $danger $nightKeword>
          현재 최고가보다 높은 금액을 입력해주세요</Text>}        
        { lackMoney && <Text $danger $nightKeword>
          보유 금액이 부족합니다.</Text>}
      </AuctionBidContainer>
    </>
  )
}

export default AuctionBuying