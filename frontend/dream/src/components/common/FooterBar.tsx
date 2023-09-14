import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router';
import { useSelector } from 'react-redux';
import { RootState } from 'store';

// 컴포넌트
import Button from "./Button";

// 스타일
import styled, {css} from "styled-components"
import { FiHome, FiSearch, FiPlusSquare, FiDollarSign, FiBook, FiUser } from "react-icons/fi";
import { Bar } from 'style/Bar';

const IconHome = styled(FiHome)`
  width: 2rem;
  height: 2rem;
  margin: 0.5rem;
`
const IconSearch = styled(FiSearch)`
  width: 2rem;
  height: 2rem;
  margin: 0.5rem;
`
const IconPlusSquare = styled(FiPlusSquare)`
  width: 2rem;
  height: 2rem;
  margin: 0.5rem;
`
const IconUser = styled(FiUser)`
  width: 2rem;
  height: 2rem;
  margin: 0.5rem;
`
const IconAuction = styled(FiDollarSign)`
  width: 2rem;
  height: 2rem;
  margin: 0.5rem;
`
const IconBook = styled(FiBook)`
  width: 2rem;
  height: 2rem;
  margin: 0.5rem;
`

const FooterBar = () => {
  const navigate = useNavigate()
  
  //true : night, false : day
  const [nightDayMode, setNightDayMode] = useState<boolean>(true);

  const themeMode = useSelector((state: RootState) => state.themeModeReducer.themeMode);

  useEffect(()=>{
    setNightDayMode(themeMode.mode ==='night' ? true : false)
  },[themeMode.mode])

  return (
    <>
      <Bar $footer $day={!nightDayMode} $night={nightDayMode}>  
        <IconHome onClick={()=>{navigate(nightDayMode ? "/night/main":"/day/main")}}/>
        <IconSearch onClick={()=>{navigate(nightDayMode ? "/night/search":"/day/search")}}/>
        <IconPlusSquare onClick={()=>{navigate(nightDayMode ? "/night/dream/create":"/day/challenge/create")}}/>
        {nightDayMode ? (
          <IconAuction onClick={()=>{navigate("/night/auction/list")}}/>
          ):(
          <IconBook onClick={()=>{navigate("/day/main")}}/>
        )}
        <IconUser onClick={()=>{navigate(nightDayMode ? "/night/profile/:userId":"/day/profile/:userId")}}/>
      </Bar>
    </>
  )
}

export default FooterBar
