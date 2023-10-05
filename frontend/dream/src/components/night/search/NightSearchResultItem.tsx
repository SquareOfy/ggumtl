// 오직 검색 결과 리스트만.
// 여기에 제목 // tooltip되는 문구

// 리액트
import tokenHttp from 'api/tokenHttp'
import React, { useEffect, useState } from 'react'
import Text from 'style/Text'
import { SearchResultObjType } from './NightSearchResultList'
import Container from 'style/Container'
import { Box } from 'style/Box'
import { FiX } from 'react-icons/fi'
import Button from 'components/common/Button'

// 컴포넌트

// 스타일

// 타입
interface NightSearchResultItemProps {
  item: SearchResultObjType
}

const NightSearchResultItem = ({ item }: NightSearchResultItemProps) => {
  const [modal, setModal] = useState<boolean>(false)

  return (
    <>
      {modal ? (
        <Container $nightSearchModal>
          <Text $nightWhite $nightModalTitle>
            상세 내용
          </Text>
          <Box $nightSearchModal>
            <Button
              $nightSearchModalXButton
              onClick={() => {
                setModal(false)
              }}
              $icon
            >
              <FiX />
            </Button>
            <Text $nightWhite $nightSearchModal>
              {item.dreamTelling}
            </Text>
          </Box>
        </Container>
      ) : (
        <Container $nightSearch>
          <Box
            $nightSearchResultBox
            onClick={() => {
              setModal(true)
            }}
          >
            <Text $nightWhite $nightSearch>
              {item.dreamTelling}
            </Text>
          </Box>
        </Container>
      )}
    </>
  )
}

export default NightSearchResultItem
