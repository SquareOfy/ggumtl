import {DefaultTheme} from "styled-components";

export const sunsetTheme: DefaultTheme = {
  textColor: "#FFFFFF",
  backgroundImageUrl : `${process.env.PUBLIC_URL}/image/background-image/sunset-background.png`
};

export const nightTheme: DefaultTheme = {
  textColor: "#FFFFFF",
  backgroundImageUrl : `${process.env.PUBLIC_URL}/image/background-image/night-background.png`
};

export const dayTheme: DefaultTheme = {
  textColor: "#374151",
  backgroundImageUrl : `${process.env.PUBLIC_URL}/image/background-image/day-background.png`
};

export const theme = {
  sunsetTheme,
  nightTheme,
  dayTheme,
};