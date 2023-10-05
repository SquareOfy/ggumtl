// // 작은 이미지
// // 호가 버튼
// // 보유 꿈머니
// // input - 참여버튼
// // 경고문구

// // 2개 텍스트 박스(AuctionDetail 복붙)
// // 리액트
// import React, {useEffect, useState} from "react";
// import { useSelector } from 'react-redux'
// import { useParams } from "react-router-dom";

// // 컴포넌트
// import Button from "components/common/Button";

// // 스타일
// import styled, {css} from "styled-components";
// import Image from "style/Image";
// import Container from "style/Container";
// import Text from "style/Text";
// import Input from "style/Input";
// import { RootState } from "store";

// // 외부
// // 웹소캣
// import { Client, Message, Stomp } from "@stomp/stompjs";
// // import StompJs from '@stomp/stompjs';
// import websocket from "websocket"
// import { WebSocket, WebSocketServer  } from "ws";

// // 범선
// import SockJS from 'sockjs-client';

// Object.assign(global, {WebSocket: websocket.w3cwebsocket})
// Object.assign(global, {WebSocket})


// // // push 알림
// // import { getMessaging, onMessage } from 'firebase/messaging';
// // import { requestPermission } from 'utils/alert/notification';
// // import { FCM_VALID_KEY } from 'ignore/Ignore';


// const AuctionBidContainer = styled.div`
//   margin: 1rem 3rem;

//  & :first-child {
//   padding-left: 1rem;
//  }

//  /* & :last-child {
//   padding-left: 1rem;
//  } */
// `

// const BiddingWrap = styled.div`
//   display: grid;
//   grid-template-columns: 75% 25%;
//   grid-gap: 1vw;
//   margin-top: 1vw;
//   margin-bottom: 1vw;
// `

// // 타입
// interface AuctionBuyingProps {
//   biddingMoney: number;
//   askingMoney: number;
// }


// const AuctionBuying = ({biddingMoney, askingMoney} :AuctionBuyingProps) => {
//   const userdata = useSelector((state: RootState) => state.auth.userdata);
//   const {auctionId} = useParams()
//   const accessToken = sessionStorage.getItem('accessToken')

//   // const biddingMoney :number = 5000 // 서버에서 받을 값
//   const [myBiddingMoney, setMyBiddingMoney] = useState<number>(biddingMoney)
//   const [currentAskingMoney, setAskingMoney] = useState<number>(askingMoney)


//   // 웹소캣(2)
//   const socket = new SockJS("https://j9b301.p.ssafy.io/ws-stomp")

//   const client = Stomp.over(socket)
//   client.connectHeaders = {
//     Authorization: "Bearer " + accessToken
//   }

//   client.debug = (str) => {console.log("디버그:", str)}
//   client.reconnectDelay=5000 //자동재연결
//   client.heartbeatIncoming=4000
//   client.heartbeatOutgoing=4000



// //     const client = new Client({
// //     // 일단 둠
// //     brokerURL: "wss://j9b301.p.ssafy.io/ws-stomp",
// //     connectHeaders: {
// //       login: 'user',
// //       passcode: 'password',
// //     },
// //     debug: function (str) {
// //       console.log(str, "디버깅임");
// //     },
// //     reconnectDelay: 5000,
// //     heartbeatIncoming: 4000,
// //     heartbeatOutgoing: 4000,
// // })

// // client.onConnect = (frame) => {
// //   // 무언가 해야함
// //   client.connectHeaders = {
// //     Authorization: "Bearer " + accessToken
// //   }
// //   client.subscribe(`/sub/auction/${auctionId}`, (msg)=> {
// //     const newPrice = JSON.parse(msg.body)
// //     setMyBiddingMoney(newPrice)
// //   })

// //   console.log(frame, "연결!")
// //   return () => {
// //     client.unsubscribe(`/sub/auction/${auctionId}`)
// //   }
// // }

// // client.onStompError = (frame) => {
// //   // 또 해야함.
// //   console.log('Broker reported error: ' + frame.headers['message']);
// //   console.log('Additional details: ' + frame.body);

// // }


//   useEffect(() => {
//     client.onConnect = (frame) => {
//       client.subscribe(`/sub/auction/${auctionId}`, (msg)=> {
//       const newPrice = JSON.parse(msg.body)
//       setMyBiddingMoney(newPrice)
//       })

//       return () => {
//         client.unsubscribe(`/sub/auction/${auctionId}`)
//       }
//     }

//     client.activate()
//     return () => {
//       client.deactivate()
//     }
//   }, [])

//   // 숫자만 입력 받는 정규식
//   const numberCheck = /^[0-9]+$/;

//   const [lowerMoney, setLowerMoney] = useState<boolean>(false)
//   const [lackMoney, setLackMoney] = useState<boolean>(false)
//   // 추후 호가 몫만 들고오기
//   const changeBiddingMoney = (e :any) => {
//     if (!numberCheck.test(e.currentTarget.value)) {
//       console.log("숫자 아니에요.")
//       return
//     }
//     if (e.currentTarget.value < biddingMoney) {
//       console.log("현재 가격보다 낮음")
//       setMyBiddingMoney(biddingMoney)
//       setLowerMoney(true)
//       setLackMoney(false)
//       return
//     } else if (e.currentTarget.value > userdata.point) {
//       console.log("보유액 부족")
//       setMyBiddingMoney(biddingMoney) // 여기 최초가로
//       setLowerMoney(false)
//       setLackMoney(true)
//       return
//     }
//     setMyBiddingMoney(e.currentTarget.value)
//     setLowerMoney(false)
//     setLackMoney(false)
    
//   }
  
//   const addBiddingMoney = () => {
//     setMyBiddingMoney(() => Number(myBiddingMoney)+Number(currentAskingMoney))
//   }

//   // push 알림 확인
//   // const messaging = getMessaging()
//   // onMessage(messaging, (payload) => {
//   //   console.log("메시지 수신", payload)
//   // })
  

//   return (
//     <>
//       {/* 호가 */}
//       <Container $centerContainer>
//         <Button $nightPalePurple $biddingBtn
//         onClick={addBiddingMoney}
//         ><Text $black $isBold>+{currentAskingMoney}</Text></Button>
//       </Container>

//       <AuctionBidContainer>
//         <Text $nightKeword $nightWhite>나의 꿈머니: ${userdata.point}</Text>
//         <BiddingWrap>
//           <Input $nightColor $biddingValue 
//           type="number"
//           onChange={(e)=>setMyBiddingMoney(e.currentTarget.value)}
//           onBlur={changeBiddingMoney}
//           value={myBiddingMoney}
//           />
//           {/* 참여시 무르기 불가함을 고지 */}
//           {/* lackMoney, lowerMoney 유효성 검사 통과 이후에만 */}
//           <Button $nightMiddlePurple $biddingBtn>참여</Button>
//         </BiddingWrap>
//         { (!lowerMoney && !lackMoney ) && <Text $nightKeword $nightWhite>한 번 참여 후 취소할 수 없습니다.</Text> }
//         { lowerMoney && <Text $danger $nightKeword>
//           현재 최고가보다 높은 금액을 입력해주세요</Text>}        
//         { lackMoney && <Text $danger $nightKeword>
//           보유 금액이 부족합니다.</Text>}
//       </AuctionBidContainer>
//     </>
//   )
// }

// export default AuctionBuying