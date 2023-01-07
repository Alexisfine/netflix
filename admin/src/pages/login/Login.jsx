import React, { useContext, useState } from 'react'
import { login } from '../../context/authContext/apiCalls';
import { AuthContext } from '../../context/authContext/authContext';
import './Login.scss';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const {loading, dispatch} = useContext(AuthContext);

  const handleLogin = (e) => {
    e.preventDefault();
    login({username, password}, dispatch);

  }
  return (
    <div className="login">
      <div className="top">
        <div className="wrapper">
          <img
            className="logo"
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Netflix_2015_logo.svg/2560px-Netflix_2015_logo.svg.png"
            alt=""
          />
        </div>
      </div>
      <div className="container">
        <form>
          <h1>Admin Sign In</h1>
          <input type="text" placeholder="Username" onChange={(e)=>setUsername(e.target.value)}/>
          <input type="password" placeholder="Password" onChange={(e)=>setPassword(e.target.value)}/>
          <button className="loginButton" onClick={handleLogin} disabled={loading}>Sign In</button>
      
        </form>
      </div>
    </div>
  )
}

export default Login