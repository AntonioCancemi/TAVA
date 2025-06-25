import { useDispatch, useSelector } from 'react-redux';
import { login as loginApi, registerTenant as registerApi } from '../api/authAPI';
import {
  loginStart,
  loginSuccess,
  loginFailure,
  logout as logoutAction,
} from '../store/authSlice';
import type { AppDispatch, RootState } from '../store';

export function useAuth() {
  const dispatch = useDispatch<AppDispatch>();
  const { user, token, status } = useSelector((state: RootState) => state.auth);

  const doLogin = async (email: string, password: string) => {
    dispatch(loginStart());
    try {
      const data = await loginApi({ email, password });
      localStorage.setItem('token', data.token);
      dispatch(loginSuccess(data));
    } catch (err) {
      dispatch(loginFailure());
    }
  };

  const doRegister = async (tenantName: string, email: string, password: string) => {
    dispatch(loginStart());
    try {
      const data = await registerApi({ tenantName, email, password });
      localStorage.setItem('token', data.token);
      dispatch(loginSuccess(data));
    } catch (err) {
      dispatch(loginFailure());
    }
  };

  const logout = () => {
    localStorage.removeItem('token');
    dispatch(logoutAction());
  };

  return {
    user,
    token,
    status,
    isAuthenticated: Boolean(token),
    doLogin,
    doRegister,
    logout,
  };
}
