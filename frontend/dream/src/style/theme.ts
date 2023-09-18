import {DefaultTheme} from "styled-components";

export const sunsetTheme: DefaultTheme = {
  textColor: "#FFFFFF",
  backgroundImageUrl : `${process.env.PUBLIC_URL}/image/background-image/sunset-background.jpg`,
  mode:'sunset',
};

export const nightTheme: DefaultTheme = {
  textColor: "#FFFFFF",
  backgroundImageUrl : `${process.env.PUBLIC_URL}/image/background-image/night-background.jpg`,
  mode:'night',
};

export const dayTheme: DefaultTheme = {
  textColor: "#374151",
  backgroundImageUrl : `${process.env.PUBLIC_URL}/image/background-image/day-background.jpg`,
  mode:'day'
};

export const theme = {
  sunsetTheme,
  nightTheme,
  dayTheme,
};