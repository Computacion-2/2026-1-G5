import { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './LoginPage.css';
import { login } from '../../services/authService';
import { AuthContext } from '../../context/AuthContext';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const {setIsAuthenticated} = useContext(AuthContext)

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const response = await login(username, password);
      if (response.success) {
        
        setIsAuthenticated(true)
        navigate('/animals');
      } else {
        setError('Invalid credentials');
      }
    } catch {
      setError('An error occurred during login');
    }
  };


  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2>Login</h2>
        {error && <p className="error-message">{error}</p>}
        <div className="form-group">
          <label htmlFor="email">Username</label>
          <input
            id="email"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="login-button">Sign In</button>
      </form>
    </div>
  );
};

export default LoginPage;
