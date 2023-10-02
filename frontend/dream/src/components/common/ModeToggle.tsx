import React, { useEffect, useState } from "react";

import '../../style/css/modeToggle.css'
import { useSelector } from "react-redux";
import { RootState } from "store";
import { useLocation, useNavigate } from "react-router-dom";



const ModeToggle = () => {
  const navigate = useNavigate()
  const location = useLocation()

  const [nightDayMode, setNightDayMode] = useState<boolean>(
    location.pathname.includes('day') ? true : false
  );

  const themeMode = useSelector((state: RootState) => state.themeMode.themeMode);

  const changeNightDay = () => {
    console.log(location)
    if (location.pathname.includes('day')){
      if (location.pathname.includes('main')) {
        navigate('/night/main')
      }
      else if (location.pathname.includes('profile')) {
        navigate('/night/profile/:userId')
      }

      // return location.pathname.includes('main') ? navigate('/night/main'):navigate('/night/profile/:userId')  

    }
    else if (location.pathname.includes('night')){
      if (location.pathname.includes('main')) {
        navigate('/day/main')
      }
      else if (location.pathname.includes('profile')) {
        navigate('/day/profile/:userId')
      }
      // return location.pathname.includes('main') ? navigate('/day/main'):navigate('/day/profile/:userId') 
    }
  } 

  useEffect(()=>{
    setNightDayMode(themeMode.mode ==='day' ? true : false)
  },[themeMode.mode])

  return (
    <>
      <label className="switch">
        <input 
          className="switch__input" 
          type="checkbox" 
          checked ={nightDayMode}
          onChange={()=>{changeNightDay()}}
        />
        <span className="switch__background">
          <span className="switch__toggle">
            <span className="switch__moon"></span>
          </span>
          <span className="switch__stars"></span>
          <span className="switch__clouds"></span>
        </span>
      </label>
    </>
  )
}

export default ModeToggle