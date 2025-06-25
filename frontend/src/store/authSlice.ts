import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import type { User } from '../types/Auth';

interface AuthState {
  user: User | null;
  token: string | null;
  status: 'idle' | 'loading' | 'failed';
}

const initialState: AuthState = {
  user: null,
  token: localStorage.getItem('token'),
  status: 'idle',
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    loginStart(state) {
      state.status = 'loading';
    },
    loginSuccess(state, action: PayloadAction<{ user: User; token: string }>) {
      state.status = 'idle';
      state.user = action.payload.user;
      state.token = action.payload.token;
    },
    loginFailure(state) {
      state.status = 'failed';
    },
    logout(state) {
      state.user = null;
      state.token = null;
      state.status = 'idle';
    },
  },
});

export const { loginStart, loginSuccess, loginFailure, logout } = authSlice.actions;
export default authSlice.reducer;
