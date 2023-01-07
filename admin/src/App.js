import Home from "./pages/home/Home";
import { BrowserRouter, Routes, Route, Navigate, useNavigate} from 'react-router-dom';
import Login from './pages/login/Login'
import List from './pages/list/List'
import Single from './pages/single/Single'
import New from './pages/new/New'
import { productInputs, userInputs } from "./formSource";
import { useContext, useEffect, useState } from "react";
import axios from "axios";
import { AuthContext } from "./context/authContext/authContext";


function App() {
  const {user} = useContext(AuthContext);
  console.log(user);
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path='/login' element={user ? <Navigate to="/" replace /> :  <Login />}/>
          { user ? <>
          <Route path='/' element={<Home/>}/>
          <Route path='/users'>
            <Route index element={<List/> }/>
            <Route path=":userIndex" element={<Single/> }/>
            <Route path="new" element={<New inputs={userInputs} title='Add New User'/>}/>
          </Route>
          <Route path='/movies'>
            <Route index element={<List/> }/>
            <Route path=":movieId" element={<Single/> }/>
            <Route path="new" element={<New inputs={productInputs} title='Add New Movie'/> }/>
          </Route>
          <Route path='/lists'>
            <Route index element={<List/> }/>
            <Route path=":listId" element={ <Single/>}/>
            <Route path="new" element={<New inputs={productInputs} title='Add New List'/>}/>
          </Route>
          <Route path='*'  element={<Navigate to='/' replace/>}/> </> 
          : <Route path='*'  element={<Navigate to='/login' replace/>}/>}
        </Routes>
      </BrowserRouter> 
    </div>
  );
}

export default App;
