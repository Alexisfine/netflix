import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useLocation } from 'react-router-dom';
import Datatable from '../../components/datatable/Datatable';
import Navbar from '../../components/navbar/Navbar';
import Sidebar from '../../components/sidebar/Sidebar';
import MovieDatatable from '../../components/MovieDatatable/MovieDatatable'
import UserDatatable from '../../components/userDatatable/UserDatatable'
import ListDatatable from '../../components/ListDataTable/ListDatatable'

import './List.scss';
const List = () => {
  const location = useLocation();
  const type = location.pathname.split('/')[1];
  const token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3MzcxODIxOX0.GVhIpqNRl2q-ZZOU6Ipsi35QIQmu0gsoEEn5keEvtAu0WnB7rnlYcoOd7IxTrFYZ6K-_n3gGYHwI8R6JtlgLAg'
    const config = {
      headers : {Authorization:`Bearer ${token}`}
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