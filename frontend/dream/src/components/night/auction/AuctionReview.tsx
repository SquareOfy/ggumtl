// 리액트
import React, {ChangeEvent, useState} from "react";

// 컴포넌트
import Button from "components/common/Button";

// 스타일
import styled, {css} from "styled-components";
import "./AuctionReview.css"
import Text from "style/Text";
import { HiStar } from "react-icons/hi";
import {LuChevronRight} from "react-icons/lu"
import {SlRocket} from "react-icons/sl"

const AlignCenter = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    // height: 100%;
    position: fixed;
    left: 50%;
    top: 40%;
    transform: translate(-50%, 0);

`

const ReviewContainer = styled.div`
    display: flex;
    justify-content: center;
    align-content: center;
    margin: 0.5rem auto;
`

const AuctionReview = () => {
    const [dreamScore, setDreamScore] = useState<number>(10)
    const scoreChange = (e: ChangeEvent<HTMLInputElement>) => {
        e.stopPropagation()
        setDreamScore(parseFloat(e.currentTarget.value))
        console.log(e.currentTarget.value, "평가점수")
    }

    return (
        <>
        <AlignCenter>

        <ReviewContainer>
            <Text $nightWhite $nightKeword>카드는 마음에 들었나요?</Text>
        </ReviewContainer>
        <ReviewContainer>
            <span className="dreamStar">
                <HiStar />
                <HiStar />
                <HiStar />
                <HiStar />
                <HiStar />
                <span style={{width: `${dreamScore*20}%`}}>
                    <HiStar />
                    <HiStar />
                    <HiStar />
                    <HiStar />
                    <HiStar />
                </span>
            <input
                type="range"
                value={dreamScore}
                step={1}
                min={1}
                max={5}
                onInput={scoreChange}
                />
            </span>
        </ReviewContainer>
        <Button $icon $nightPalePurple><SlRocket/></Button>
        </AlignCenter>
        </>
    )
}

export default AuctionReview;