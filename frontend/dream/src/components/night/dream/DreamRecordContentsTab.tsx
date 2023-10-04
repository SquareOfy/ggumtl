// 리액트
import React, {Dispatch, SetStateAction, useEffect, useState} from "react";
import tokenHttp from "api/tokenHttp";
import { useNavigate, useParams } from "react-router-dom";

// 컴포넌트
import Button from "components/common/Button";

// 스타일
import styled, {css} from "styled-components";
import { Box } from "style/Box";
import Text from "style/Text";
import "components/night/dream/DreamCreate.css"
import Wrap from "style/Wrap";
import { ReverseCardType } from "../home/NightHomeItem";

const DreamContentsTabWrap = styled.div`
  margin: 2rem 0.5rem;
  margin-bottom: 1rem;
`

const DreamTabWrap = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  // margin: inherit;
  & > div {
    text-align: center;
  }
`
const TabLine = styled.hr`
  border: 1px solid #D9D9D9;
  opacity: 0.5;
  margin: 0.25rem 1rem
`

const CustomText = styled(Text)<TabStyleType>`  
  color: #999999;
  ${(props) => props.$isActive && 
    css`
      color: #FFFFFF;
      font-weight: 700;
    `
  }
`

export interface TabStyleType {
  $isActive ?: boolean
}

export interface dreamCardDataType {
  dreamCardId :number,
  dreamOwner :number,
  dreamCardAuthor :number,
  grade :string,
  createdAt :string,
  positiveGrade :string,
  rareGrade :string,
  actionStatus :string,
  dreamCardImageUrl :string,
  isShow :string,
  keywords :string[],
  dreamCardContent :string,
  dreamTelling :string,
  likeCount :number,
  reviewStatus :string,
  ownerNickname :string,
}

interface DreamRecordContentsTabProps {
  setReverseCardData: Dispatch<SetStateAction<ReverseCardType | undefined>>;
  setReviewStatus?: Dispatch<SetStateAction<string>> | undefined;
}

// 2개 색깔에 따라 달라짐
const DreamRecordContentsTab = ({setReverseCardData, setReviewStatus} :DreamRecordContentsTabProps) => {
  const navigate = useNavigate();

  // 꿈 기록 탭
  const [isRecordTab, setIsRecordTab] = useState(true)
  // 해몽 탭
  const [isInterpretTab, setIsInterpretTab] = useState(false)

  // 꿈 기록 탭 보여주기
  const show1RecordTab = () => {
    setIsRecordTab(true)
    setIsInterpretTab(false)
  }

  // 해몽 탭 보여주기
  const showInterpretTab = () => {
    setIsRecordTab(false)
    setIsInterpretTab(true)
  }

  // 꿈 해몽 불러오기
  const params = useParams();
  const [dreamCardData, setDreamCardData] = useState<dreamCardDataType>();

  useEffect(() => {
    tokenHttp.get(`/night/dream/${params.dreamCardId}/interpretation`)
    .then((res)=>{
      console.log("꿈 카드 탭 : ", res)
      const response = res.data
      const data = response.data

      // 카드 소유자가 아닐 때
      if (response.status === 400) {
        alert(response.data)
        navigate('/night/main')
      } else if (response.status === 200) {
        setDreamCardData(data)
        // setReviewStatus(data.reviewStatus)
      }

    })
    .catch((err) => console.log("꿈 카드 탭 에러 : ", err))
  }, [])


  // 플립 카드로 보내주는 데이터
  useEffect(() => {
    if (dreamCardData) {
      const data :ReverseCardType = {
        dreamCardId: dreamCardData?.dreamCardId,
        dreamCardOwner: dreamCardData?.dreamOwner,
        ownerNickname: dreamCardData?.ownerNickname,
        dreamCardAuthor: dreamCardData?.dreamCardAuthor,
        grade: dreamCardData?.grade,
        createdAt: dreamCardData?.createdAt,
        positiveGrade: dreamCardData?.positiveGrade,
        rareGrade: dreamCardData?.rareGrade,
        auctionStatus: dreamCardData?.actionStatus,
        dreamCardImageUrl: dreamCardData?.dreamCardImageUrl,
        isShow: dreamCardData?.isShow,
        keywords: dreamCardData?.keywords,
      }
      setReverseCardData(data)
      // setReviewStatus 함수가 정의되어 있을 때만 호출
      if (setReviewStatus) {
        setReviewStatus(dreamCardData.reviewStatus);
      }
    }
  }, [setReviewStatus, dreamCardData])


  

  return (
    <>
    <DreamContentsTabWrap>
      <DreamTabWrap>
        <CustomText 
        onClick={show1RecordTab}
        $isActive={isRecordTab}
        >꿈 기록</CustomText>
        <CustomText 
        onClick={showInterpretTab}
        $isActive={isInterpretTab}
        >해몽</CustomText>
      </DreamTabWrap>
      <TabLine/>
      {isRecordTab && 
        <Box $wideTextBox $night>{dreamCardData?.dreamCardContent}</Box>
      }
      {isInterpretTab && <Box $wideTextBox $night>{dreamCardData?.dreamTelling}</Box>}
    
      {/* <Wrap $nightBotButtonWrap $nightButtonCheckWrap>
        <div>
          <label className="container">
            <input type="checkbox" checked={isChecked} onChange={handleCheckboxChange}/>
            <div className="checkmark"></div>
            <Text $nightWhite>공개</Text>
          </label>
        </div>
        <div>
          <Button 
          $nightPalePurple
          onClick={() => deleteDreamCard()}
          >삭제</Button>
          {
            dreamCardData?.dreamCardAuthor != dreamCardData?.dreamOwner
            ? dreamCardData?.reviewStatus === "F" &&
              <Button 
              $nightPurple 
              onClick={() => navigate(`/night/auction/bidding/review`)}>리뷰</Button>
            : <Button 
              $nightPurple
              onClick={() => navigate(`/night/auction/detail/${dreamCardData?.dreamCardId}/create`)}
              >경매</Button>
          }
          
        </div>
      </Wrap> */}
    </DreamContentsTabWrap>
    </>
  )
}

export default DreamRecordContentsTab