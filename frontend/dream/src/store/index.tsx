import { configureStore, createSlice } from "@reduxjs/toolkit";
import userdataReducer from "./userdataReducer";
import themeModeReducer from "./themeModeReducer";

import storageSession from 'redux-persist/lib/storage/session'
import { persistReducer, FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER } from 'redux-persist';
import { combineReducers } from "@reduxjs/toolkit";

// 이 부분에 reducer 추가
const rootReducer = combineReducers({
  auth: userdataReducer.reducer, // .reducer 붙여야 타입에러가 안남
  themeMode: themeModeReducer.reducer
})

// storage에 저장하기 위해서 persistConfig 생성
const persistConfig= {
  key:'root', // obj의 key
  storage:storageSession, // storage의 타입 => sessionStorage 사용
  whitelist:['auth'], // auth Reducer만 persist 적용한다는 것
}

// redux-persist + redux 모듈을 종합하여 persist 반환
const persistedReducer = persistReducer(persistConfig, rootReducer);

// redux-persist store
const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
});

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch;
export default store;