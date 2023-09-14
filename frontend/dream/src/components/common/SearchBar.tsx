// 검색창

// 리액트
import React, {useEffect, useState} from "react";

// 컴포넌트
import Input from "style/Input";

// 스타일
import styled from "styled-components";
import {IoIosSearch} from "react-icons/io"
import {CiCircleRemove} from "react-icons/ci"
import Wrap from "style/Wrap";
import { boolean } from "yargs";

// 외부 라이브러리

export interface colorProps {
  color ?: string;
  onChange ?: (e:any) => void
  disabled?: boolean;    
  type?: 'text';
  placeholder ?: string;
  id ?: string;
}

const NarrowWrap = styled.div`
  margin: 0 0.5rem
`
const SearchBarContainer = styled.div`
  position: relative;
  width: 100%;
  /* margin: 0 0.5rem;
  padding: 0 0.5rem; */
`
const IconSearch = styled(IoIosSearch)`
  position: absolute;
  top: 50%;
  right: 0.75rem;
  transform: translateY(-50%);
`
export interface SearchBar {
  isSearch: boolean
}

const SearchBar = (props: colorProps) => {
  useEffect(()=> {

  }, [])
  return (
    <>
    <Wrap $baseWrap>
    <SearchBarContainer >
      <Input $searchBar
      $nightColor={false}
      $dayColor={true}
      onChange={props.onChange}
      placeholder="검색"
      type="text"
      />
    <IconSearch/>
    </SearchBarContainer>
    </Wrap>
    </>
  )
}

export default SearchBar