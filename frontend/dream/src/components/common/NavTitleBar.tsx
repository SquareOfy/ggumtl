import React, {useState, useEffect} from "react"
import { useSelector } from "react-redux";
import { RootState } from "store";

// 컴포넌트
import Button from "./Button";
import { Bar } from "style/Bar";

import { FiX } from "react-icons/fi";
import { useNavigate } from "react-router-dom";

interface NavTitleProps {
  children ?: React.ReactNode;
}

const NavTitleBar = (props:NavTitleProps) => {

  const [nightDayMode, setNightDayMode] = useState<boolean>(true);
  const themeMode = useSelector((state :RootState) => state.themeMode.themeMode);
  const navigate = useNavigate();

  useEffect(()=>{
    setNightDayMode(themeMode.mode === 'night' ? true : false)
  })

  // X 버튼 클릭 시 메인으로 이동
  const goToMain = () => {
    if (themeMode.mode === "day") {navigate(`/day/main`)} 
    else {navigate(`/night/main`)}
  }

  return (
    <>
    {/* <h1>NavTitleBar</h1> */}
    <Bar $navTitle $day={!nightDayMode} $night={nightDayMode}>
      <div>{props.children}</div>
      <Button 
      onClick={() => goToMain()}
      $icon
      ><FiX /></Button>
    </Bar>
    </>
  )
}

export default NavTitleBar