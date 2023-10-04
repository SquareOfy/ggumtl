import { createSlice } from "@reduxjs/toolkit";
import type { PayloadAction } from '@reduxjs/toolkit'

export interface UserdataType{
  userdata:{
    userId :number
    name :string
    nickname :string
    point :number
    profileImageName :string
    profileImageUrl :string
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
    profileImageUrl:'',
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
      console.log('유저 데이터 redux 저장', state.userdata)
    },
    logout: (state) => {
      // initialState 초기화
      state.userdata = {
        userId : -1,
        name: '',
        nickname:'',
        point:0,
        profileImageName : '',
        profileImageUrl:'',
        createdAt : '',
        wrigglePoint: 36.5,
      };
    }
  },
});

export const { getCurrentUserdata, logout } = userdataReducer.actions;

export default userdataReducer;