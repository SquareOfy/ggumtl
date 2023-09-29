
// <ChalComment></ChalComment>

// 리액트
import React, {useState} from "react";

// 컴포넌트
import ChalCommentList from "components/day/challenge/ChalCommentList";
import Container from "style/Container";
import NavCommentBar from "components/common/NavCommentBar";
import FooterCommentBar from "components/common/FooterCommentBar";
import { useParams } from "react-router";

// 스타일

const ChalCommentPage = () => {

  const params = useParams()
  const currentDetailId = Number(params.challengeDetailId)
  const [newCommentSignal,setNewCommentSignal] = useState<boolean>(false) 
  
  return (
    <Container $commentContainer>
      <NavCommentBar>댓글</NavCommentBar>
      <ChalCommentList 
        newCommentSignal={newCommentSignal}
        setNewCommentSignal={setNewCommentSignal}
      />
      <FooterCommentBar 
        currentDetailId = {currentDetailId} 
        setNewCommentSignal={setNewCommentSignal}
      ></FooterCommentBar>
    </Container>
  )
}

export default ChalCommentPage