import axios from 'axios';
import type {
  LoginRequest,
  LoginResponse,
  RegisterTenantRequest,
  RegisterTenantResponse,
} from '../types/Auth';

const api = axios.create({ baseURL: '/auth' });

export const login = async (data: LoginRequest): Promise<LoginResponse> => {
  const res = await api.post<LoginResponse>('/login', data);
  return res.data;
};

export const getMe = async (token: string): Promise<LoginResponse> => {
  const res = await api.get<LoginResponse>('/me', {
    headers: { Authorization: `Bearer ${token}` },
  });
  return res.data;
};

export const registerTenant = async (
  data: RegisterTenantRequest
): Promise<RegisterTenantResponse> => {
  const res = await api.post<RegisterTenantResponse>('/register', data);
  return res.data;
};
