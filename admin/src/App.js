import Home from "./pages/home/Home";
import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';
import Login from './pages/login/Login'
import List from './pages/list/List'
import Single from './pages/single/Single'
import New from './pages/new/New'
import { productInputs, userInputs } from "./formSource";
import { useEffect, useState } from "react";
import axios from "axios";


function App() {


  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route path='/users'>
            <Route index element={<List/>}/>
            <Route path=":userIndex" element={<Single/>}/>
            <Route path="new" element={<New inputs={userInputs} title='Add New User'/>}/>
          </Route>
          <Route path='/movies'>
            <Route index element={<List/>}/>
            <Route path=":movieId" element={<Single/>}/>
            <Route path="new" element={<New inputs={productInputs} title='Add New Movie'/>}/>
          </Route>
          <Route path='/lists'>
            <Route index element={<List/>}/>
            <Route path=":listId" element={<Single/>}/>
            <Route path="new" element={<New inputs={productInputs} title='Add New List'/>}/>
          </Route>
          <Route path='*'  element={<Navigate to='/' replace/>}/>
        </Routes>
      </BrowserRouter> 
    </div>
  );
}

export default App;
