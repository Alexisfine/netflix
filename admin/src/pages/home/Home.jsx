import React, { useEffect, useState } from 'react'
import './Home.scss';
import Sidebar from '../../components/sidebar/Sidebar'
import Navbar from '../../components/navbar/Navbar';
import Widget from '../../components/widget/Widget';
import FeaturedCharts from '../../components/featuredCharts/FeaturedCharts';
import Charts from '../../components/charts/Charts';
import Table from '../../components/table/Table';
import axios from 'axios';

const Home = () => {
    const token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3MzcxODIxOX0.GVhIpqNRl2q-ZZOU6Ipsi35QIQmu0gsoEEn5keEvtAu0WnB7rnlYcoOd7IxTrFYZ6K-_n3gGYHwI8R6JtlgLAg'
  const config = {
    headers : {Authorization:`Bearer ${token}`}
  }

   const [usersStats, setUsersStats] = useState({});
  useEffect(() => {
    const getStats = async () => {
      try {
        const {data} = await axios.get('/users/data/cumulative-total-users', config)
        console.log(data);
        var listOfUsers = [];
        Object.keys(data).forEach((key,index) => {
            listOfUsers.push({date:key, users: data[key]})
        });
        setUsersStats(listOfUsers)

      } catch (err) {
        console.log(err);
      }
    }
    getStats();
  },[])

  return (
    <div className='home'>
        <Sidebar/>
        <div className="homeContainer">
            <Navbar/>
            <div className="widgets">
                <Widget type='user'/>
                <Widget type='movie'/>
                <Widget type='earning'/>
                <Widget type='balance'/>
            </div>
            <div className="charts">
                <FeaturedCharts/>
                <Charts title="Cumulative Registered Users" aspect={2 / 1} data={usersStats? usersStats : {}}/>
            </div>
            <div className="listContainer">
                <div className="listTitle">Latest Movies</div>
                <Table/>
            </div>
        </div>
    </div>
  )
}

export default Home