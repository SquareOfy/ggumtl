
// map
// <CategoryBox></CategoryBox>

// 리액트
import React from "react";
import Text from "style/Text";
import styled from "styled-components";

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

const DayCategoryList = () => {
  let categoryList :string[] = [
    '재물', '진로', '인간관계', '감정', '자기계발', 
    '건강', '도전', '교양', '학습', '기타'];

  return (
    <>
    <CategoryWrap>
      <Text $isBold>카테고리</Text>
      <CategoryListWrap>
        {
          categoryList.map((category, i) => 
            <Button $category key={i}>{category}</Button>
          )
        }
      </CategoryListWrap>
    </CategoryWrap>
    </>
  )
}

export default DayCategoryList