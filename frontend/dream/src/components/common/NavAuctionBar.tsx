import React, {useState, useEffect} from "react"
import { useSelector } from "react-redux";
import { RootState } from "store";

// 컴포넌트
import { Bar } from "style/Bar";

interface NavAuctionProps {
  children ?: React.ReactNode;
}

const NavAuctionBar = (props:NavAuctionProps) => {

  const [nightDayMode, setNightDayMode] = useState<boolean>(true);
  const themeMode = useSelector((state :RootState) => state.themeMode.themeMode);

  useEffect(()=>{
    setNightDayMode(themeMode.mode === 'night' ? true : false)
  })

  return (
    <>
    {/* <h1>NavAuctionBar</h1> */}
    <Bar $navTitle $day={!nightDayMode} $night={nightDayMode}>
      <div>{props.children}</div>
    </Bar>
    </>
  )
}

export default NavAuctionBar