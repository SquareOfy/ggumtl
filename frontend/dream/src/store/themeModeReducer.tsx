import { createSlice } from "@reduxjs/toolkit";

export interface themeModeType{
  textColor: string;
  backgroundImageUrl : string;
  mode: string;
}

export interface initialStateType{
  themeMode: themeModeType
}

const initialState :initialStateType={
  themeMode: { 
    textColor: "#FFFFFF",
    backgroundImageUrl : `${process.env.PUBLIC_URL}/image/background-image/sunset-background.jpg`,
    mode:'sunset'
  },
}

export const themeModeReducer = createSlice({
  name: 'themeModeReducer',
  initialState,
  reducers:{
    changeMode: (state, action) => {
      state.themeMode =  action.payload;
    },
  },
});

export const { changeMode } = themeModeReducer.actions;

export default themeModeReducer;