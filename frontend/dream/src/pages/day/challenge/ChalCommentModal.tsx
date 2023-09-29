// 리액트
import React from "react";

// 컴포넌트
import ChalCommentList from "components/day/challenge/ChalCommentList";
import Container from "style/Container";
import NavCommentBar from "components/common/NavCommentBar";
import FooterCommentBar from "components/common/FooterCommentBar";
import { useParams } from "react-router-dom";

// 스타일

const ChalCommentModal = () => {

  const params = useParams()
  const currentDetailId = Number(params.challengeDetailId)

  return (
    <Container $commentContainer>
      <NavCommentBar>댓글</NavCommentBar>
      <ChalCommentList />
      <FooterCommentBar
        currentDetailId={currentDetailId}
      ></FooterCommentBar>
    </Container>
  )
}

export default ChalCommentModal