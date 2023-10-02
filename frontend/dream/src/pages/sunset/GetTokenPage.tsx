import React, {useEffect, useState} from "react";
import { useLocation, useNavigate, useSearchParams } from "react-router-dom";

const GetTokenPage = () => {
  
  const [searchParams, setSearchParams] = useSearchParams()
  const navigate = useNavigate();
  // console.log('쿼리스틩',queryString)
  // console.log(searchParams.get('type'))
  console.log(searchParams.get('accessToken'))
  // console.log(searchParams.get('refreshToken'))
  const type = searchParams.get('type');
  const accessToken :string|null= searchParams.get('accessToken');
  const refreshToken :string|null = searchParams.get('refreshToken');

  useEffect(()=>{
    // 세션에 accessToken, refreshToken 저장
    sessionStorage.setItem('accessToken', accessToken ? accessToken : '');
    sessionStorage.setItem('refreshToken', refreshToken ? refreshToken : '');

    if (type === 'login') {
      console.log('로그인 완료')
      // 메인으로 이동
      navigate('/sunset/main')

    }
    else if (type === 'signup') {
      console.log('회원가입으로 이동')
      // 회원가입 페이지로 이동
      navigate('/sunset/signup')
    }

  },[])

  return (
    <>
    <p>로딩 페이지를 넣을까요?</p>
    {/* 대기 페이지 */}
    </>
  )
}

export default GetTokenPage