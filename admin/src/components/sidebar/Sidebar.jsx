import React from 'react'
import './Sidebar.scss'
import DashboardIcon from '@mui/icons-material/Dashboard'
import { AccountCircleOutlined, CreditCard, ExitToApp, InsertChart, LocalShipping, Notifications, PersonOutline, PsychologyOutlined, SettingsApplications, SettingsSystemDaydreamOutlined, Store } from '@mui/icons-material'
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
            <Link to='/products' style={{textDecoration:'none'}}>
          <li>
            <Store className='icon'/>
            <span>Products</span>
            </li>
            </Link>
          <li>
            <CreditCard className='icon'/>
            <span>Orders</span>
            </li>
          <li>
            <LocalShipping className='icon'/>
            <span>Delivery</span>
            </li>
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