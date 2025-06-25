import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { registerTenant } from '../../api/authAPI';
import { loginSuccess } from '../../store/authSlice';
import { AppDispatch } from '../../store';

const RegisterTenantPage: React.FC = () => {
  const dispatch: AppDispatch = useDispatch();
  const navigate = useNavigate();
  const [tenantName, setTenantName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!tenantName || !email || !password) {
      setError('All fields are required');
      return;
    }

    try {
      const data = await registerTenant({ tenantName, email, password });
      localStorage.setItem('token', data.token);
      dispatch(loginSuccess(data));
      navigate('/');
    } catch (err: any) {
      setError('Registration failed');
    }
  };

  return (
    <div>
      <h2>Register Tenant</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Tenant Name</label>
          <input
            type="text"
            value={tenantName}
            onChange={(e) => setTenantName(e.target.value)}
          />
        </div>
        <div>
          <label>Email</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div>
          <label>Password</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        {error && <p>{error}</p>}
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default RegisterTenantPage;
