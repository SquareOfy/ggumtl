import React from 'react';

// 컴포넌트
import NavTitleBar from "../../components/common/NavTitleBar"
import AlertMain from 'components/alert/AlertMain';
import FooterBar from 'components/common/FooterBar';

// 스타일

const NightAlertPage = () => {

  return (
    <>
    <NavTitleBar></NavTitleBar>
    <AlertMain />
    {/* <FooterBar></FooterBar> */}
    </>
  )
}

export default NightAlertPage