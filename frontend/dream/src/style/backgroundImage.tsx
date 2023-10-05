import React from 'react';
import styled from 'styled-components';

interface BackgroundImageProps {
  backgroundimage:string;
}

const StyledBackgroundImage = styled.div<BackgroundImageProps>`
  position: fixed;
  top:0;
  background-image: url(${(props)=> props.backgroundimage});
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  width: 100vw;
  height: 100vh;
  z-index: -1;
`

// const BackgroundImage:React.FC<BackgroundImageProps> = (props:BackgroundImageProps) => {
//   return <StyledBackgroundImage {...props}></StyledBackgroundImage>
// };

const BackgroundImage: React.FC<BackgroundImageProps> = ({ backgroundimage }) => {
  return <StyledBackgroundImage backgroundimage={backgroundimage} />;
};

export default BackgroundImage;