import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react'
import { useLocation } from 'react-router-dom';
import Datatable from '../../components/datatable/Datatable';
import Navbar from '../../components/navbar/Navbar';
import Sidebar from '../../components/sidebar/Sidebar';
import MovieDatatable from '../../components/MovieDatatable/MovieDatatable'
import UserDatatable from '../../components/userDatatable/UserDatatable'
import ListDatatable from '../../components/ListDataTable/ListDatatable'

import './List.scss';
import { AuthContext } from '../../context/authContext/authContext';
const List = () => {
  const location = useLocation();
  const type = location.pathname.split('/')[1];
   const {user} = useContext(AuthContext);
  const config = {
    headers : {Authorization:`Bearer ${user?.token}`}
  }

  const [items, setItems] = useState({});
  useEffect(() => {
    const fetchItems = async () => {
      try {
        const {data} = await axios.get(`/${type}?page=0&size=100`, config);
        setItems(data.content);
      } catch (err) {
        console.log(err)
      }
    }
    fetchItems();
  },[items, type])

  return (
    <div className='list'>
      <Sidebar/>
      <div className="listContainer">
        <Navbar/>
        {type === 'users' ? 
        <UserDatatable type={type} items={items}/> 
        : type === 'movies' ? <MovieDatatable type={type} items={items}/>
        : type === 'lists' ? <ListDatatable type={type} items={items}/>
        :<Datatable type={type} items={items}/>}
        
      </div>
    </div>
  )
}

export default List