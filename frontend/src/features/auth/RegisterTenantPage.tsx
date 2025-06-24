import { useState } from 'react';
import { useAuth } from './useAuth';

export default function RegisterTenantPage() {
  const { doRegister } = useAuth();
  const [tenantName, setTenantName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const submit = (e: React.FormEvent) => {
    e.preventDefault();
    doRegister(tenantName, email, password);
  };

  return (
    <form onSubmit={submit}>
      <input value={tenantName} onChange={e => setTenantName(e.target.value)} placeholder="Tenant name" />
      <input value={email} onChange={e => setEmail(e.target.value)} placeholder="Admin email" />
      <input value={password} onChange={e => setPassword(e.target.value)} type="password" placeholder="Password" />
      <button type="submit">Register</button>
    </form>
  );
}
