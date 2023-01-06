import Home from "./pages/home/Home";
import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';
import Login from './pages/login/Login'
import List from './pages/list/List'
import Single from './pages/single/Single'
import New from './pages/new/New'


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route path='/users'>
            <Route index element={<List/>}/>
            <Route path="me" element={<Single/>}/>
            <Route path="new" element={<New/>}/>
          </Route>
          <Route path='/products'>
            <Route index element={<List/>}/>
            <Route path=":productId" element={<Single/>}/>
            <Route path="new" element={<New/>}/>
          </Route>
          <Route path='*'  element={<Navigate to='/' replace/>}/>
        </Routes>
      </BrowserRouter> 
    </div>
  );
}

export default App;
