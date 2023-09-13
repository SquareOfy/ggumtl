import React, {useState} from 'react';

// 컴포넌트
import Button from "./Button";

// 스타일
import styled, {css} from "styled-components"
import { VscHome,VscSearch, VscDiffAdded ,VscAccount } from "react-icons/vsc"

import { FiHome, FiSearch, FiPlusSquare, FiDollarSign, FiTrendingUp,FiPenTool, FiBook, FiUser } from "react-icons/fi";
import { useNavigate } from 'react-router';

// 타입
interface NavProps {
  $day ?: boolean;
  $night ?: boolean;
}


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

const FooterBarContainer = styled.div<NavProps>`
  width: 100vw;
  height:3rem;
  position: fixed;
  left: 0;
  bottom: 0;
  display: flex;
  justify-content: space-around;
  /* align-items: center; */

  ${(props) => props.$day &&
    css `
      background-color: rgba(160, 190, 210, 0.5);
    `
  }
  ${(props) => props.$night &&
    css `
      background-color: rgba(157, 142, 196, 0.5);
    `
  }
`

const FooterBar = () => {
  const navigate = useNavigate()
  
  //true : night, false : day
  const [nightDayMode, setNightDayMode] = useState<boolean>(true);
  return (
    <>
      <FooterBarContainer $day={!nightDayMode} $night={nightDayMode}>  
        <IconHome onClick={()=>{navigate(nightDayMode ? "/night/main":"/day/main")}}/>
        <IconSearch onClick={()=>{navigate(nightDayMode ? "/night/search":"/day/search")}}/>
        <IconPlusSquare onClick={()=>{navigate(nightDayMode ? "/night/dream/create":"/day/challenge/create")}}/>
        {nightDayMode ? (
          <IconAuction onClick={()=>{navigate("/night/auction/list")}}/>
          ):(
          <IconBook onClick={()=>{navigate("/day/main")}}/>
        )}
        <IconUser onClick={()=>{navigate(nightDayMode ? "/night/profile/:userId":"/day/profile/:userId")}}/>
      </FooterBarContainer>
    </>
  )
}

export default FooterBar
