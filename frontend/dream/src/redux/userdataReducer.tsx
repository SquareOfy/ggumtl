import { createSlice } from "@reduxjs/toolkit";
import type { PayloadAction } from '@reduxjs/toolkit'

export interface UserdataState{
  userdata: {}
  // 데이터 형태 나오면 작성
}

const initialState: UserdataState={
  userdata: {},
  // 데이터 형태 나오면 작성
}

export const userdataReducer = createSlice({
  name: 'userdataReducer',
  initialState,
  reducers:{
    getCurrentUserdata: (state, action) => {
      state.userdata =  action.payload;
    },
    logout: (state) => {
      state.userdata = {};
    }
  },
});

export const { getCurrentUserdata, logout} = userdataReducer.actions;

export default userdataReducer;