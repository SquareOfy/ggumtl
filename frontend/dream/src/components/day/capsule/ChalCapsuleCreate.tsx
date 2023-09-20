
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
import React from "react";
import styled from "styled-components";

// 컴포넌트
import Button from "components/common/Button";

// 스타일
import Text from "style/Text";
import Container from "style/Container";

const ChalCapsuleCreate = () => {

  return (
    <Container $baseContainer $dayCreate >
      <Text>
        열심히 도전하고 있을 미래의 나에게<br/>
        메세지를 전달해보세요!<br/>
        <br/>
        중간 달성 시에<br/>
        다른 사람들의 메세지와 함꼐 볼 수 있어요.<br/>
      </Text>
    </Container>
  )
}
export default ChalCapsuleCreate