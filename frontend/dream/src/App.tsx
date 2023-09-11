import React, {useState} from 'react';
import GlobalStyle from './style/GlobalStyles';
import {ThemeProvider} from "styled-components"
import { nightTheme, dayTheme } from './style/theme';

// 라우터
import { Route, Routes, useLocation } from "react-router-dom";
import FooterBar from 'components/common/FooterBar';

function App() {
  const location = useLocation();
  const hideComponent :boolean = location.pathname.startsWith("/sunset") || location.pathname.includes("comments");

  return (
    <>
    {/* <ThemeProvider > */}
    <GlobalStyle/>
      <div className="App">
      <Routes>

        
      </Routes>
      {!hideComponent && <FooterBar/> }
      </div>
    {/* </ThemeProvider> */}
    </>
  );
}

export default App;
