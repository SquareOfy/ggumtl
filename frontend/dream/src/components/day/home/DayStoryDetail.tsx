
// 상단 div
// CircleImage 
// 닉네임 text 
// X button

// TitleBox
// MainImage
// WideTextBox

// 리액트
import React, { Dispatch, SetStateAction } from "react";

// 컴포넌트

// 스타일
import Image from "style/Image";
import Text from "style/Text";
import Wrap from "style/Wrap";
import { AiOutlineClose } from "react-icons/ai";

export interface DayStoryDetailProps {
  setIsOpenModal : Dispatch<SetStateAction<boolean>>
}

const DayStoryDetail = ({setIsOpenModal} :DayStoryDetailProps) => {

  const handleIsOpenModal = () => {
    setIsOpenModal(false);
    console.log("모달 닫기");
  }

  return (
    <>
    <Wrap $storyWrap>
      {/* 상단바 */}
        <div>
          <Image $tinyProfileImage><img /></Image>
          <Text $isBold $nightWhite>나는프론트엔드</Text>
          <AiOutlineClose onClick={handleIsOpenModal}></AiOutlineClose>
        </div>
      {/* 챌린지 제목 */}

      {/* 사진 */}

      {/* 내용 */}


    </Wrap>
    </>
  )
}

export default DayStoryDetail