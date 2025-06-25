import { useSelector, useDispatch } from 'react-redux';
import { RootState, AppDispatch } from '../store';
import { logout as logoutAction } from '../store/authSlice';

export default function useAuth() {
  const dispatch: AppDispatch = useDispatch();
  const user = useSelector((state: RootState) => state.auth.user);
  const token = useSelector((state: RootState) => state.auth.token);

  const logout = () => {
    localStorage.removeItem('token');
    dispatch(logoutAction());
  };

  return {
    user,
    token,
    isAuthenticated: Boolean(token),
    logout,
  };
}
