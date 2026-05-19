import { Navigate, Outlet } from 'react-router-dom';
import { usePermission } from '../../hooks/usePermission';

/**
 * Component that guards routes based on permissions.
 * If authorized, it renders the child routes via <Outlet />.
 * Otherwise, it redirects to the login page.
 */
const ProtectedRoute = () => {
  const { isAuthenticated } = usePermission();

  if (!isAuthenticated) {
    // Redirect to login if not authorized
    return <Navigate to="/login" replace />;
  }

  // If authorized, render the matched child route
  return <Outlet />;
};

export default ProtectedRoute;
