// 공통 Input 컴포넌트
import React from 'react'

import styled, {css} from "styled-components"

interface TextAreaProps {
    children?: React.ReactNode;
    // styles?: string;
    onChange ?: (e :any) => void;
    onBlur ?: (e :any) => void;
    disabled?: boolean;    
    type?: 'text';
    placeholder ?: string;
    id ?: string;
    value ?: string;
    

    // 별개 스타일링
    // text area
    $nightDreamInput ?: boolean;
    $chalCapsuleInput ?: boolean;
    $chalDetailValue ?: boolean;
    $inputStart ?: boolean;
    $commentInput ?: boolean;
}

const StyledTextArea = styled.textarea<TextAreaProps>`
  color: #374151;
  outline: none;
  border: none;
  border-radius: 3rem;   
  padding: 2rem;
  text-align: start;
  resize: none;

  // nightDreamInput
  ${(props) =>
    props.$nightDreamInput &&
    css`
      color: #F9FAFB;
      background-color: rgb(190, 169, 215, 50%);
      width: 100%;
      height: 30rem;
      border-radius: 2rem;   
      margin: 1rem 0;
    `
  }

  // chalCapsuleInput
  ${(props) =>
  props.$chalCapsuleInput &&
    css`
      background-color: rgb(117, 168, 199, 50%);
      width: 100%;
      height: 10rem;
    `
  }
  
  // chalDetailValue - Box
  ${(props) =>
    props.$chalDetailValue &&
    css`
      border: none;
      border-radius: 0.75rem;    
      padding : 1.25rem 0.75rem;
      background-color: rgb(249, 249, 249, 50%); 
      width: 100%;
      height: 8rem;
      margin-top: 0.5rem;
      margin-bottom: 0.5rem;
    `
  }
  // commentInput
  ${(props) =>
    props.$commentInput &&
    css`
      border-radius: 1rem;    
      padding-left: 1rem;
      padding-right: 4rem;
      padding-top: 0.5rem;
      padding-bottom: 0.5rem;
      width: 55%;
      height: 1rem;
      margin-top: 0.5rem;
      margin-bottom: 0.5rem;
      border: 0.1rem solid rgb(101, 101, 101, 30%);
      text-align: left;
    `
  }


`

const TextArea = (props:TextAreaProps) => {
    return <StyledTextArea {...props}></StyledTextArea>
}


export default TextArea