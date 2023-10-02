import React from 'react';

// 컴포넌트
import NavTitleBar from "../../components/common/NavTitleBar"
import AlertMain from 'components/alert/AlertMain';
import FooterBar from 'components/common/FooterBar';

// 스타일

const NightAlertPage = () => {

  return (
    <>
    <NavTitleBar>알림</NavTitleBar>
    <AlertMain />
    {/* <FooterBar></FooterBar> */}
    </>
  )
}

export default NightAlertPage