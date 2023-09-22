import { createSlice } from "@reduxjs/toolkit";
import type { PayloadAction } from '@reduxjs/toolkit'

export interface UserdataType{
  userdata:{
    userId :number
    name :string
    nickname :string
    point :number
    profileImageName :string
    profileUrl :string
    createdAt :string
    wrigglePoint :number
  }
}

const initialState: UserdataType={
  userdata: {
    userId : -1,
    name: '',
    nickname:'',
    point:0,
    profileImageName : '',
    profileUrl:'',
    createdAt : '',
    wrigglePoint: 36.5,
  },
}

export const userdataReducer = createSlice({
  name: 'userdataReducer',
  initialState,
  reducers:{
    getCurrentUserdata: (state, action) => {
      state.userdata =  action.payload;
    },
    logout: (state) => {
      // initialState 초기화
      state.userdata = {
        userId : -1,
        name: '',
        nickname:'',
        point:0,
        profileImageName : '',
        profileUrl:'',
        createdAt : '',
        wrigglePoint: 36.5,
      };
    }
  },
});

export const { getCurrentUserdata, logout } = userdataReducer.actions;

export default userdataReducer;