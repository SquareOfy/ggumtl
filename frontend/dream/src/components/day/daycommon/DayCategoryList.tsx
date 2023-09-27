
// map
// <CategoryBox></CategoryBox>

// 리액트
import React, { SetStateAction, Dispatch, useEffect, useState } from "react";
import Text from "style/Text";
import styled from "styled-components";

// 외부 라이브러리
import tokenHttp from "api/tokenHttp";

// 컴포넌트

// 스타일
import Button from "components/common/Button";

const CategoryWrap = styled.div`
  margin: 0.5rem;
`

const CategoryListWrap = styled.div`
  margin: 1rem 0;
  overflow-x: scroll;
  white-space: nowrap;
  display: flex;
  gap: 0.5rem;
`

export interface CategoryAxiosType {
  keyword :string,
  keywordId :number,
}

export interface CategoryPropsType {
  setCategoryProps :Dispatch<SetStateAction<CategoryAxiosType>>
}

const DayCategoryList = (props :CategoryPropsType) => {
  const [categoryList, setCategoryList] = useState<CategoryAxiosType[]>([]);

  // axios 요청
  const getCategory = () => {
    tokenHttp.get(`/day/keyword/list`)
    .then((res) => {
      setCategoryList(res.data.data);
    })
    .catch((err) => console.log(err))
  }

  useEffect(() => {
    getCategory();
  }, []);

  return (
    <>
    <CategoryWrap>
      <Text $isBold>카테고리</Text>
      <CategoryListWrap>
        {
          categoryList?.map((category, i) => 
            <Button 
            onClick={() => props.setCategoryProps(category)}
            $category key={i}>{category.keyword}</Button>
          )
        }
      </CategoryListWrap>
    </CategoryWrap>
    </>
  )
}

export default DayCategoryList