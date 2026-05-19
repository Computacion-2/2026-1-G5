import { useAuthContext } from "./useAuthContext";

export const usePermission = () => {
  const { isAuthenticated } = useAuthContext();

  // Logic to validate permissions
  // In a real app, you might pass specific permission strings here

  return { isAuthorized:true, isAuthenticated };
};
