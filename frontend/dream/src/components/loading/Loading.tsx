import React from "react"
import styled from "styled-components"

const LoadingContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 85vh;
  /* width: 30%; */
`

const Loading = () => {
  return (
    <>
    <LoadingContainer>
      <img src={process.env.PUBLIC_URL + "/image/loading3.gif"} />
      {/* <div>기다려주세요...!</div> */}
    </LoadingContainer>
    </>
  )
}

export default Loading