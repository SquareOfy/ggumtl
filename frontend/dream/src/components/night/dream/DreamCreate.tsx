// 음성 LongButton
// WideTextInput
// 공개 // 버튼 2개

// 리액트
import React, { useEffect, useState } from "react";

// 외부
import dataHttp from "api/dataHttp";

// 컴포넌트
import SoundToText from "./SoundToText";

// 스타일
import Button from "components/common/Button";
import TextArea from "style/TextArea";
import styled from "styled-components";
import { IoMicOutline, IoMicSharp } from "react-icons/io5"
import Wrap from "style/Wrap";
import "components/night/dream/DreamCreate.css"
import Text from "style/Text";

const DreamCreateContainer = styled.div`
  margin: 1.5rem 0.5rem;
  display: flex;
  flex-direction: column;
  overflow-y: scroll;
`;

interface DreamTextAreaWrapperProps {
  height :number;
} 

const DreamTextAreaWrapper = styled.div<DreamTextAreaWrapperProps>`
  width: 100%;
  height: calc(${props => props.height/16 > 22 ? props.height/16 : 22}rem);
  // min-height: 22rem;
  margin-bottom: 2rem;
  transition: height 0.3s ease;
`;

const IconRecord = styled(IoMicOutline)`
  width: 2rem;
  height: 2rem;
`

const IconRecording = styled(IoMicSharp)`
  width: 2rem;
  height: 2rem;
`

const DreamCreate = () => {

  const [recordStart, setRecordStart] = useState<boolean>(false); // 녹음 시작
  const [allText, setAllText] = useState<string>(""); // 입력본 + 녹음본 모두 합친 것
  const { startListening, stopListening, accenting, setAccentText, accentText } = SoundToText();
  const [isChecked, setIsChecked] = useState<boolean>(false);
  // const [accentRecord, setAccentRecord] = useState<string>("");
  // const [accentScript, setAccentScript] = useState<string>("");
  // const [accentClickable, setAccentClickable] = useState<boolean>(false);
  // const [accentText, setAccentText] = useState<string>("");
  

  // 녹음 시작 & 종료
  useEffect(() => {
    if (recordStart) {
      startListening();
    } else {
      stopListening();
    }
  }, [setRecordStart, recordStart]);
  
  // 변환된 텍스트 저장
  useEffect(() => {
    if (accentText) {
      setAllText(allText + " " + accentText); // 기존 입력된 값과 합치기
    }
  }, [setAccentText, accentText])
  
  // 체크박스 변화 감지
  const handleCheckboxChange = () => {
    setIsChecked(!isChecked);
  };

  // allText: 보낼 데이터
  // isChecked가 공개 여부
  // userId
  // const userId:number = 1
  const sendDreamToPython = () => {
    const dreamCardAuthor:number = 3
    const isShow = isChecked
    const dreamCardContent = allText
    const data = {dreamCardAuthor, isShow, dreamCardContent}
    console.log(data, "보낼 데이터!")
    dataHttp.post("/night/dream/create", data)
    .then(res => {
      console.log(res, "생성!")
    })
    .catch(err => console.log(err))
  }

  // TextArea 높이 감지
  const [textAreaHeight, setTextAreaHeight] = useState<number>(0);

  useEffect(() => {
    setTextAreaHeight(window.document.querySelector('#textarea')?.scrollHeight ?? 0)
  }, [allText])

  return (
    <>
    <DreamCreateContainer>
      <Button
      $fullWidth
      $nightVoice
      onClick={() => {
        console.log('클릭');
        setRecordStart(!recordStart); // 녹음 시작, 종료
        accenting(); // 텍스트로 변환
      }}
      $isRecording={recordStart}
      >
        {
          recordStart
          ? <IconRecording/>
          : <IconRecord/>
        }
        
      </Button>
      <DreamTextAreaWrapper height={textAreaHeight}>
        <TextArea
        $nightDreamInput
        id="textarea"
        value={allText}
        onChange={(e) => setAllText(e.target.value)}
        />
      </DreamTextAreaWrapper>

      <Wrap $nightBotButtonWrap $nightButtonCheckWrap>
        <div>
          <label className="container">
            <input type="checkbox" checked={isChecked} onChange={handleCheckboxChange}/>
            <div className="checkmark"></div>
            <Text $nightWhite>공개</Text>
          </label>
        </div>
        <div>
          <Button $nightPalePurple>취소</Button>
          <Button 
          $nightPurple
          onClick={sendDreamToPython}
          // onClick={} // Karlo 백 서버 API 연결 - 보낼 데이터 : allText
          >등록</Button>
        </div>
      </Wrap>
    </DreamCreateContainer>
    </>
  )
}

export default DreamCreate