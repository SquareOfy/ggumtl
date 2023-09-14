import React, {useState, useEffect} from 'react';
import GlobalStyle from './style/GlobalStyles';
import {ThemeProvider} from "styled-components"
import { sunsetTheme,nightTheme, dayTheme } from './style/theme';

// 라우터
import { Route, Routes, useLocation } from "react-router-dom";
import FooterBar from 'components/common/FooterBar';
import SunsetMainPage from 'pages/sunset/SunsetMainPage';
import LoginPage from 'pages/sunset/LoginPage';
import SignUpPage from 'pages/sunset/SignUpPage';
import NightHomePage from 'pages/night/home/NightHomePage';
import DreamCreatePage from 'pages/night/dream/DreamCreatePage';
import DreamDetailPage from 'pages/night/dream/DreamDetailPage';
import AuctionCreatePage from 'pages/night/auction/AuctionCreatePage';
import AuctionMainPage from 'pages/night/auction/AuctionMainPage';
import AuctionDetailPage from 'pages/night/auction/AuctionDetailPage';
import AuctionBuyingPage from 'pages/night/auction/AuctionBuyingPage';
import AuctionBuyingSuccessPage from 'pages/night/auction/AuctionBuyingSuccessPage';
import NightSearchPage from 'pages/night/search/NightSearchPage';
import NightSearchDetailPage from 'pages/night/search/NightSearchDetailPage';
import NightProfilePage from 'pages/night/profile/NightProfilePage';
import NightAlertPage from 'pages/alert/NightAlertPage';
import DayHomePage from 'pages/day/home/DayHomePage';
import DaySearchPage from 'pages/day/search/DaySearchPage';
import ChalDetailPage from 'pages/day/challenge/ChalDetailPage';
import ChalCreatePage from 'pages/day/challenge/ChalCreatePage';
import ChalCommentPage from 'pages/day/challenge/ChalCommentPage';
import ChalCapsuleCreatePage from 'pages/day/capsule/ChalCapsuleCreatePage';
import ChalManagePage from 'pages/day/challenge/ChalManagePage';
import ChalManageDetailPage from 'pages/day/challenge/ChalManageDetailPage';
import ChalCreateCertPage from 'pages/day/challenge/ChalCreateCertPage';
import ChalCapsuleListPage from 'pages/day/capsule/ChalCapsuleListPage';
import DayAlertPage from 'pages/alert/DayAlertPage';
import BackgroundImage from 'style/backgroundImage';


function App() {
  const location = useLocation();
  const hideComponent :boolean = location.pathname.startsWith("/sunset") || location.pathname.includes("comments");
  
  const [theme,setTheme] = useState(sunsetTheme);
  
  // 라우터 이동 시에 url pathname 확인
  useEffect(()=>{
    if (location.pathname.includes('sunset')){
      setTheme(sunsetTheme)
    } else if (location.pathname.includes('day')){
      setTheme(dayTheme)
    } else if (location.pathname.includes('night')){
      setTheme(nightTheme)
    }
  })

  return (
    <>
    {/* <ThemeProvider theme={theme}> */}
    <BackgroundImage backgroundImage={theme.backgroundImageUrl}/>
    <GlobalStyle/>
      <Routes>
        {/* 초기 3개 화면 */}
        <Route path="/sunset/main" element={<SunsetMainPage/>} />
        <Route path="/sunset/login" element={<LoginPage/>}/>
        <Route path="/sunset/signup" element={<SignUpPage/>}/>
        {/* 밤 페이지 */}
        <Route path="/night/main" element={<NightHomePage/>}/>
        <Route path="/night/search" element={<NightSearchPage/>}/>
        <Route path="/night/search/dreamTelling/:dreamTellingId" element={<NightSearchDetailPage/>}/>
        <Route path="/night/dream/create" element={<DreamCreatePage/>}/>
        <Route path="/night/dream/:dreamCardId" element={<DreamDetailPage/>}/>
        {/* 경매장 */}
        <Route path="/night/auction/list" element={<AuctionMainPage/>}/>

        <Route path="/night/auction/detail/:dreamCardId" element={<AuctionDetailPage/>}/> 
        <Route path="/night/auction/bidding/:dreamCardId" element={<AuctionBuyingPage/>}/> 

        <Route path="/night/auction/bidding/review" element={<AuctionBuyingSuccessPage/>}/> 

        {/* 밤 프로필 */}
        <Route path="/night/profile/:userId" element={<NightProfilePage/>}/>

        {/* 낮 페이지 */}
        <Route path="/day/main" element={<DayHomePage/>}/>
        {/* 검색 - 우리가 쿼리스트링으로 추가할 수도. */}
        <Route path="/day/search" element={<DaySearchPage/>}/>

        {/* 챌린지 관련 */}
        {/* 챌린지 상세조회 */}
        <Route path="/day/challenge/:challangeId" element={<ChalDetailPage/>}/>
        <Route path="/day/challenge/:challangeId/comments" element={<ChalCommentPage/>}/>
        <Route path="/day/challenge/create" element={<ChalCreatePage/>}/>
        {/* 타임캡슐 */}
        {/* 모달 */}
        {/* <Route path="/day/challenge/:challangeId/timecapsule" element={<ChalCapsuleListPage/>}/> */}
        <Route path="/day/challenge/:challangeId/timecapsule/create" element={<ChalCapsuleCreatePage/>}/>
        
        {/* 챌린지 매니지(내 챌린지) 관련 */}
        <Route path="/day/mychallenge/list" element={<ChalManagePage/>}/>
        <Route path="/day/mychallenge/:challengeId" element={<ChalManageDetailPage/>}/>
        {/* 인증글 올리기 */}
        <Route path="/day/mychallenge/cert/create" element={<ChalCreateCertPage/>}/>        

        {/* 낮 프로필 */}        
        <Route path="/day/profile/:userId" element={<NightProfilePage/>}/>

        {/* 알림 */}
        <Route path="/night/alert" element={<NightAlertPage/>}/>
        <Route path="/day/alert" element={<DayAlertPage/>}/>
      </Routes>
      {!hideComponent && <FooterBar/> }
    {/* </ThemeProvider> */}
    </>
  );
}

export default App;