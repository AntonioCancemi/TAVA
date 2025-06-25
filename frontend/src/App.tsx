import { Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from './features/auth/LoginPage';
import RegisterTenantPage from './features/auth/RegisterTenantPage';
import { useAuth } from './hooks/useAuth';

function PrivateRoute({ children }: { children: JSX.Element }) {
  const { isAuthenticated } = useAuth();
  return isAuthenticated ? children : <Navigate to="/login" replace />;
}

export default function App() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register-tenant" element={<RegisterTenantPage />} />
      <Route
        path="/*"
        element={
          <PrivateRoute>
            <div>Home</div>
          </PrivateRoute>
        }
      />
    </Routes>
  );
}
