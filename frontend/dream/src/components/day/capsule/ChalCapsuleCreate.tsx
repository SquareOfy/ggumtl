
// 글귀
// 열심히 도전하고 있을 미래의 나에게 
// 메시지를 전달해보세요
// 중간 달성 시에
// 다른 사람들의 메세지와 함꼐 보실수 있어요.

// WideTextInput (제목 x)
// 타임 캡슐 내용

// LongButton
// 담기

// 리액트
import React, {useState} from "react";
import styled from "styled-components";

// 컴포넌트
import Button from "components/common/Button";

// 스타일
import Text from "style/Text";
import Container from "style/Container";
import TextArea from "style/TextArea";
import { useLocation, useNavigate, useParams } from "react-router";
import basicHttp from "api/basicHttp";

interface ChallengeIdParamType {
  challengeId: string;
}

const ChalCapsuleCreate = () => {

  const params = useParams()
  const currentChallengeId = Number(params.challengeId)
  // const location = useLocation()
  const navigate = useNavigate()
  // const state= location.state.challengeId

  const [timeCapsuleContent,setTimeCapsuleContent] = useState<string>('')

  const addTimecapsule= async()=>{
    const axiosData = {
      "challengeId" : currentChallengeId,
      "timeCapsuleContent" : timeCapsuleContent
    }
    console.log(axiosData)
    
    // 챌린지 참여 axios
    await basicHttp.post('day/challenge', {"challengeId" : currentChallengeId})
      .then((response)=>{console.log(response, '챌린지 참여 axios')})
      .catch((e)=>{console.log(e)})

    // 타임 캡슐 등록 
    await basicHttp.post('/day/challenge/timecapsule', axiosData)
      .then((response) => {
        console.log(response,'타임캡슐 등록 axios')
      })
      .catch((e)=>{console.log(e)})
    
    // 후에 이동
    navigate(`/day/mychallenge/${currentChallengeId}`)
  }

  return (
    <Container $dayBaseContainer $dayCreate >
      <Text $dayWhite>
        열심히 도전하고 있을 미래의 나에게<br/>
        메세지를 전달해보세요!<br/>
        <br/>
        중간 달성 시에<br/>
        다른 사람들의 메세지와 함께 볼 수 있어요.<br/>
        <br/>
      </Text>
      <TextArea 
        $chalDetailValue 
        placeholder="메세지 내용 입력"
        onChange={(e)=>{setTimeCapsuleContent(e.target.value)}}
      >
      </TextArea>
      <Button 
        $dayCreate $fullWidth $dayBlue $isBold
        onClick={()=>{addTimecapsule()}}
      >
        {'담기'}
      </Button>
    </Container>
  )
}
export default ChalCapsuleCreate