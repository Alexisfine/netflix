import React from 'react'
import './Sidebar.scss'
import DashboardIcon from '@mui/icons-material/Dashboard'
import { AccountCircleOutlined, CreditCard, DynamicForm, ExitToApp, InsertChart, LiveTv, LocalShipping, Notifications, PersonOutline, PsychologyOutlined, SettingsApplications, SettingsSystemDaydreamOutlined, SmartDisplay, Store, VideoLibrary } from '@mui/icons-material'
import { Link } from 'react-router-dom'
const Sidebar = () => {
  return (
    <div className='sidebar'>
      <Link to='/' style={{textDecoration:'none'}}>
        <div className="top"><span className='logo'>Alexadmin</span></div>
      </Link>
      <hr/>
      <div className="center">
        <ul> 
          <p className="title">MAIN</p>
          <Link to='/' style={{textDecoration:'none'}}>
          <li>
            <DashboardIcon className='icon'/>
            <span>Dashboard</span>
            </li>
            </Link>
          <p className="title">LISTS</p>
          <Link to='/users' style={{textDecoration:'none'}}>
          <li>
            <PersonOutline className='icon'/>
            <span>Users</span>
            </li>
            </Link>
            <Link to='/movies' style={{textDecoration:'none'}}>
          <li>
            <VideoLibrary className='icon'/>
            <span>Movies</span>
            </li>
            </Link>
          <li>
            <SmartDisplay className='icon'/>
            <span>Documentaries</span>
            </li>
            <li>
            <LiveTv className='icon'/>
            <span>TV Series</span>
            </li>
            <Link to='/lists' style={{textDecoration:'none'}}>
          <li>
            <DynamicForm className='icon'/>
            <span>Lists</span>
            </li>
            </Link>
          <p className="title">USEFUL</p>
          <li>
            <InsertChart className='icon'/>
            <span>Stats</span>
            </li>
          <li>
            <Notifications className='icon'/>
            <span>Notifications</span>
            </li>
            <p className="title">SERVICE</p>

          <li>
            <SettingsSystemDaydreamOutlined className='icon'/>
            <span>System Health</span>
            </li>
          <li>
            <PsychologyOutlined className='icon'/>
            <span>Logs</span>
            </li>
          <li>
            <SettingsApplications className='icon'/>
            <span>Settings</span>
            </li>
            <p className="title">USER</p>
          <li>
            <AccountCircleOutlined className='icon'/>
            <span>Profile</span>
            </li>
          <li>
            <ExitToApp className='icon'/>
            <span>Logout</span>
            </li>

        </ul>
      </div>
      <div className="bottom">
        <div className="colorOption"></div>
        {/* <div className="colorOption"></div> */}
      </div>
    </div>
  )
}

export default Sidebar