import './App.scss';
import Home from "./pages/home/Home";
import Login from './pages/login/Login';
import Register from './pages/register/Register';
import Watch from './pages/watch/Watch';
import { BrowserRouter,Routes, Route, Redirect, Navigate, } from 'react-router-dom';
function App() {
  const user = true;
  return (
    <BrowserRouter>
      <Routes>
      <Route path='/register' element={!user ? <Register/> : <Navigate to='/home' replace/>}/>
      <Route path='/login' element={!user ? <Login/> : <Navigate to='/home' replace/>}/>
      <Route path='/' element={user ? <Home/> : <Navigate to='/login' replace/>}/>
      <Route path='/movies' element={user ? <Home type='movies'/> : <Navigate to='/login' replace/>}/>
      <Route path='/series' element={user ? <Home type='series'/> : <Navigate to='/login' replace/>}/>
      <Route path='/watch' element={user ? <Watch/> : <Navigate to='/login' replace/>}/>
      <Route path='*' element={user ? <Navigate to='/' replace/> : <Navigate to='/login' replace/>}/>

      </Routes>
    </BrowserRouter>
  );
}

export default App;
