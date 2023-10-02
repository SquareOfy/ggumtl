
import React, {useEffect, useState, SetStateAction, Dispatch} from 'react';

// 컴포넌트
import { CiSquareChevDown } from "react-icons/ci";

// 스타일
import styled from 'styled-components';

interface DropdownProps {
  $show ?: boolean
  $type : string
  setSelectOption : Dispatch<SetStateAction<string>>
  optionData : string[]
}

// 스타일
const SelectBox = styled.div`
    margin-top: 0.5rem;
    margin-bottom: 1rem;
    width: 100%;
    height: 3.5rem;
    border-radius: 0.75rem;    
    padding-left: 0.75rem;
    position: relative;
    border-radius: 12px;
    background-color: rgb(249, 249, 249, 50%);
    color: #374151;
    display: flex;
    align-items: center;
    cursor: pointer;
  `;

  const DropdownIcon = styled.div`
    background-size: cover;
    display: flex;
    position: absolute;
    right: 0.75rem;
    font-size: 1.5rem;
    color: #374151;
  `;

  const Label = styled.label`
    font-size: 14px;
    margin-left: 4px;
    text-align: center;
  `;

  const SelectOptions = styled.ul<{ $show: boolean }>`
    position: absolute;
    list-style: none;
    top: 2.5rem;
    left: 0;
    width: 100%;
    overflow: scroll;
    height: 7rem;
    max-height: ${(props) => (props.$show ? "none" : "0")};
    padding: 0;
    border-radius: 8px;
    background-color: rgb(249, 249, 249);
    color: #374151;;
    z-index: +1;
  `;
  const Option = styled.li`
    font-size: 14px;
    padding: 0.5rem 1rem;
    transition: background-color 0.2s ease-in;
    &:hover {
      background-color: #baccd7;
    }
  `;


const Dropdown = (props:DropdownProps) => {
  
  const [currentValue, setCurrentValue] = useState<string>(props.$type); // 현재 선택되어있는 값
  const [showOptions, setShowOptions] = useState<boolean>(false); // 드롭다운 되어있는지 확인
  const [optionArray, setOptionArray] = useState<string[]>([]) // 옵션 리스트
  
  // 옵션 고를때 창 드롭다운 켜지는지
  useEffect(()=>{
    setShowOptions(props.$show ? true : false)
  },[props.$show])

  // 옵션 선택했을 때 값 바뀌게 하는 함수
  const selectOption = (option:string)=>{
    setCurrentValue(option)
    props.setSelectOption(option)
  }

  return (
    <>
      <SelectBox onClick={()=>{setShowOptions(!showOptions)}}>
        <Label>{currentValue}</Label>
        <DropdownIcon><CiSquareChevDown/></DropdownIcon>
        <SelectOptions $show={showOptions ? true : false}>
          { props.optionData.map((option)=>{
            return (
            <Option 
              key={props.optionData.indexOf(option)} 
              onClick={()=>{selectOption(option)}}
            >
              {option}
            </Option>)
          }) }
        </SelectOptions>

      </SelectBox>
    </>
  )
}

export default Dropdown
