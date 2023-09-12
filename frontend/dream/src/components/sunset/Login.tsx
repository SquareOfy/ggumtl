// 로고 이미지

// 카카오로그인버튼
// 구글로그인
// 최초 로그인:  -> <SignUpPage/>
// 기존 회원: ->  <SunsetMainPage/>

import React from "react";
import styled from "styled-components";

// 스타일
import Button from "components/common/Button";

const LoginContainer = styled.div`
  width: 80%;
  margin:auto;
  margin-top: 40vh;
`;


const Login = () => {

  return (
    <LoginContainer>
      <Button 
      $fullWidth 
      $kakao
      >Login with Kakao</Button>
    </LoginContainer>
  )
}

export default Login