import React, {useEffect, useState} from "react";
import { useNavigate } from "react-router";
import styled from "styled-components";


const StartPage = () => {
  const navigate = useNavigate()

  useEffect(()=>{
    navigate('/sunset/login')
  },[])  
  return (
    <></>
  )
}

export default StartPage