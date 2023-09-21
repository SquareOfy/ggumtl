import React, {useState, useEffect} from "react"
import { useSelector } from "react-redux";
import { RootState } from "store";

// 컴포넌트
import Button from "./Button";
import { Bar } from "style/Bar";

import { FiX } from "react-icons/fi";

interface NavTitleProps {
  children ?: React.ReactNode;
}

const NavTitleBar = (props:NavTitleProps) => {

  const [nightDayMode, setNightDayMode] = useState<boolean>(true);
  const themeMode = useSelector((state :RootState) => state.themeMode.themeMode);
  
  useEffect(()=>{
    setNightDayMode(themeMode.mode === 'night' ? true : false)
  })

  return (
    <>
    {/* <h1>NavTitleBar</h1> */}
    <Bar $navTitle $day={!nightDayMode} $night={nightDayMode}>
      <div>{props.children}</div>
      <Button $icon><FiX /></Button>
    </Bar>
    </>
  )
}

export default NavTitleBar