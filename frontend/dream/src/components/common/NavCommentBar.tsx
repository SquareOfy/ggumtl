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

const NavCommentBar = (props:NavTitleProps) => {

  const themeMode = useSelector((state :RootState) => state.themeMode.themeMode);
  const navigate = useNavigate();

  return (
    <>
    {/* <h1>NavTitleBar</h1> */}
    <Bar $navCommentTitle>
      <div>{props.children}</div>
      <Button 
      onClick={() => navigate(-1)}
      $icon
      ><FiX /></Button>
    </Bar>
    </>
  )
}

export default NavCommentBar