import React from 'react';
import styled from 'styled-components';

interface BackgroundImageProps {
  backgroundImage:string;
}

const StyledBackgroundImage = styled.div<BackgroundImageProps>`
  position: fixed;
  top:0;
  background-image: url(${(props)=> props.backgroundImage});
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  width: 100vw;
  height: 100vh;
  z-index: -1;
`

const BackgroundImage:React.FC<BackgroundImageProps> = ({backgroundImage}) => {
  return <StyledBackgroundImage backgroundImage={backgroundImage}/>
};

export default BackgroundImage;