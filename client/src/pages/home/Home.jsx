import axios from 'axios'
import React, { useEffect, useState } from 'react'
import Featured from '../../components/featured/Featured'
import List from '../../components/list/List'
import Navbar from '../../components/navbar/Navbar'
import './Home.scss'

const Home = ({type}) => {
  const [lists, setLists] = useState(null);
  const [genre, setGenre] = useState(null);
  const token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyNSIsImV4cCI6MTY3Mzg4MDgyNn0.Ke0g3I_jEF_oHTLYxf6T8_Ge1PsHnJfg27mHSCZh8qizMBwXtorZPNEW42tCXMqwm8B1YVIWEAVBPW9BjzduRg'
  const config = {
    headers : {Authorization:`Bearer ${token}`}
  }

  useEffect(() => {
    const getRandomLists = async () => {
      try {
        const res = await axios.get
        (`/lists?page=0&size=10${type ? '&type=' + type : ''}${genre ?'&genre=' + genre : ''}`
        , config);
        setLists(res.data.content);
      } catch (err) {
        console.log(err);
      }
    } 
    getRandomLists();
  },[genre, type])

  return (
    <div className='home'>
        <Navbar/>
        <Featured type={type}/>
        {lists?.map((list) => (
          <List key={list.id} list={list}/>
        ))}
      
    </div>
  )
}

export default Home