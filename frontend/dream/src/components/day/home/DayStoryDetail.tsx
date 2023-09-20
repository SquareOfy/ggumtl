
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
import { Box } from "style/Box";

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
      <div>
        {/* 챌린지 제목 */}
        <Box $mainTitleBox>
          <img />
          <Text>1일 1커밋 챌린지</Text>
        </Box>

        {/* 사진 */}
        <Image $signupImage><img/></Image>

        {/* 내용 */}
        <Box $storyContentsBox $day>
          여기는 스토리 세부 내용 Lorem ipsum dolor sit amet consectetur adipisicing elit. Amet, sint nostrum? Dicta, provident maiores! Explicabo excepturi, odit ea facilis itaque neque laboriosam totam perspiciatis repellat quia aut consequatur dolorem accusantium!
        </Box>
      </div>


    </Wrap>
    </>
  )
}

export default DayStoryDetail