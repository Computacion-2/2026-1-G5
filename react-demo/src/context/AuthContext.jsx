import { createContext, useState } from 'react';

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [authData, setAuthData] = useState({username:'', permissions:[]});
  const isAuth = localStorage.getItem("isAuth");
  const [isAuthenticated, setIsAuthenticated] = useState(isAuth == 'true');



  const fromLocal = (value) => {
    setIsAuthenticated(value)
    localStorage.setItem("isAuth",value)
  }
  return (
    <AuthContext.Provider value={{ user: authData, isAuthenticated, setUser: setAuthData, setIsAuthenticated:fromLocal}}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
export { AuthContext };


