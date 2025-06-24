import { useDispatch, useSelector } from 'react-redux';
import { login, registerTenant } from './authAPI';
import { setToken, clearToken } from './authSlice';

export function useAuth() {
  const dispatch = useDispatch();
  const token = useSelector((state: any) => state.auth.token);

  const doLogin = async (email: string, password: string) => {
    const data = await login(email, password);
    dispatch(setToken(data.token));
  };

  const doRegister = async (tenantName: string, email: string, password: string) => {
    const data = await registerTenant(tenantName, email, password);
    dispatch(setToken(data.token));
  };

  const logout = () => dispatch(clearToken());

  return { token, doLogin, doRegister, logout };
}
