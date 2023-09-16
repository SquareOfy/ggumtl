// 음성 LongButton
// WideTextInput
// 공개 // 버튼 2개

// 리액트
import React, { useState } from "react";

// 컴포넌트

// 스타일
import Button from "components/common/Button";
import TextArea from "style/TextArea";
import styled from "styled-components";

const DreamCreateContainer = styled.div`
  width: 100%;
  margin: 6rem 0.5rem;
`;

const DreamCreate = () => {
  return (
    <>
    <DreamCreateContainer>
      <Button
      $fullWidth
      $nightVoice
      />
      <TextArea
      $nightDreamInput
      />

      <div
      style={{
        display: "flex", 
        justifyContent: "space-between", 
        padding: "0 1rem"
      }}
      >
        <div>
          {/* 임시 체크박스 */}
          <input
          type="checkbox"
          />
          공개
        </div>

        <div>
          <Button
          $nightMiddlePurple
          >
          취소
          </Button>
          <Button
          $nightPurple
          >
          등록
          </Button>
        </div>
      </div>
    </DreamCreateContainer>
    </>
  )
}

export default DreamCreate