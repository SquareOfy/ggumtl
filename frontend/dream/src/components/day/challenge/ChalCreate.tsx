
// ThinTextInput 챌린지 제목
// WideTextInput 챌린지 내용
// TextBox 카테고리 (드롭다운 선택)
// TextBox 챌린지 기간 (드롭다운 선택)

// 등록하기 버튼 -> ./capsule ChalCapsuleCreatePage 페이지로 이동.

// 리액트
import React, {useEffect, useState} from "react";
import styled from "styled-components";


// 컴포넌트
import Button from "components/common/Button";
import Input from "style/Input";
import Dropdown from "components/common/Dropdown";
import TextArea from "style/TextArea";


// 스타일
const ChalCreateContainer = styled.div`
  margin: 1rem;
`
const ChalCreate = () => {
  // 지금은 하드코딩
  const categoryList = ['카테고리','사랑','공부','운동']
  const periodList = ['챌린지기간','7일','30일','100일','365일']
  
  // 클릭 되어있는지 상태 확인 변수
  const [showCategoryDropdown, setShowCategoryDropdown] = useState(false)  
  const [showPeriodDropdown, setShowPeriodDropdown] = useState(false)  

  // 동시에 둘다 드롭다운 되어있는 것 방지
  useEffect(()=>{
    if (showCategoryDropdown && showPeriodDropdown) {
      setShowPeriodDropdown(!showPeriodDropdown)
    }
    else if (showPeriodDropdown && showCategoryDropdown) {
      setShowCategoryDropdown(!showCategoryDropdown)
    }
  },[showCategoryDropdown, showPeriodDropdown])

  return (
    <ChalCreateContainer>
    {/* 챌린지 제목 */}
    <Input $chalCreateInput $dayColor placeholder="챌린지 제목"></Input>

    <TextArea $chalDetailValue placeholder="챌린지 내용"></TextArea>


    {/* 카테고리 드롭다운 */}
    <div onClick={()=>{setShowCategoryDropdown(!showCategoryDropdown)}}>
      <Dropdown $show={showCategoryDropdown}>{categoryList}</Dropdown>
    </div>

    {/* 기간 드롭다운 */}
    <div onClick={()=>{setShowPeriodDropdown(!showPeriodDropdown)}}>
      <Dropdown $show={showPeriodDropdown}>{periodList}</Dropdown>
    </div>

    {/* 등록하기 버튼 */}
    <Button $fullWidth $dayBlue $isBold>{'등록하기'}</Button>
    </ChalCreateContainer>
  )
}

export default ChalCreate