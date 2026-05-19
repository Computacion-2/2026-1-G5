import { createContext, useState } from 'react';

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [authData, setAuthData] = useState({username:'', permissions:[]});
  const [isAuthenticated, setIsAuthenticated] = useState(false);


  return (
    <AuthContext.Provider value={{ user: authData, isAuthenticated, setUser: setAuthData, setIsAuthenticated}}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
export {AuthContext};


