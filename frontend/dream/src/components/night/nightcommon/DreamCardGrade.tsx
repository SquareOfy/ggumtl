// 꿈 희귀도 및 길몽지수 영역 컴포넌트화

// 리액트
import React from "react";

// 컴포넌트

// 스타일
import styled, {css} from "styled-components";
import {GradeWrappingBox } from "style/Box";

interface DreamCardGradeProps {
  positiveGrade ?: string;
  rareGrade ?: string;
}

const CustomGradeWrappingBox = styled(GradeWrappingBox)`
  margin-top: 2rem;
  margin-bottom: 2rem;
`

const DreamCardGrade = (props:DreamCardGradeProps) => {
  const {positiveGrade, rareGrade} = props
  // console.log(props, "그레이드")
  return (
    <>
    <CustomGradeWrappingBox>
      <div>
        <p>{positiveGrade}</p>
        <p>길몽지수</p>
      </div>
      <div>
        <p>{rareGrade}</p>
        <p>희귀성</p>
      </div>
    </CustomGradeWrappingBox>
    </>
  )
}

export default DreamCardGrade