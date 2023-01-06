import React from 'react'
import './Home.scss';
import Sidebar from '../../components/sidebar/Sidebar'
import Navbar from '../../components/navbar/Navbar';
import Widget from '../../components/widget/Widget';
import FeaturedCharts from '../../components/featuredCharts/FeaturedCharts';
import Charts from '../../components/charts/Charts';

const Home = () => {
  return (
    <div className='home'>
        <Sidebar/>
        <div className="homeContainer">
            <Navbar/>
            <div className="widgets">
                <Widget type='user'/>
                <Widget type='order'/>
                <Widget type='earning'/>
                <Widget type='balance'/>
            </div>
            <div className="charts">
                <FeaturedCharts/>
                <Charts/>
            </div>
        </div>
    </div>
  )
}

export default Home