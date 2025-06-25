import axios from 'axios';
import {
  LoginRequest,
  LoginResponse,
  RegisterTenantRequest,
  RegisterTenantResponse,
} from '../types/Auth';

const api = axios.create({
  baseURL: '/api',
});

export async function login(data: LoginRequest): Promise<LoginResponse> {
  const response = await api.post<LoginResponse>('/auth/login', data);
  return response.data;
}

export async function fetchMe(token: string): Promise<LoginResponse> {
  const response = await api.get<LoginResponse>('/auth/me', {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
}

export async function registerTenant(
  data: RegisterTenantRequest
): Promise<RegisterTenantResponse> {
  const response = await api.post<RegisterTenantResponse>(
    '/auth/register',
    data
  );
  return response.data;
}
