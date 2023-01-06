import React from 'react'
import Charts from '../../components/charts/Charts'
import Navbar from '../../components/navbar/Navbar'
import Sidebar from '../../components/sidebar/Sidebar'
import TableList from '../../components/table/Table'
import './Single.scss'
const Single = () => {
  return (
    <div className='single'>
      <Sidebar/>
      <div className="singleContainer">
        <Navbar/>
        <div className="top">
          <div className="left">
            <div className="editButton">Edit</div>
            <h1 className="title">Information</h1>
            <div className="item">
              <img
                src="https://images.pexels.com/photos/733872/pexels-photo-733872.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260"
                alt=""
                className="itemImage"
              />
              <div className="details">
                <h1 className="itemTitle">Jane Doe</h1>
                <div className="detailItem">
                  <span className="itemKey">Email:</span>
                  <span className="itemKey">jane@gmail.com</span>
                </div>
                <div className="detailItem">
                  <span className="itemKey">Phone:</span>
                  <span className="itemKey">+ 12342323</span>
                </div>
                <div className="detailItem">
                  <span className="itemKey">Address:</span>
                  <span className="itemKey">London</span>
                </div>
                <div className="detailItem">
                  <span className="itemKey">Country</span>
                  <span className="itemKey">Canada</span>
                </div>
              </div>

            </div>
            </div>
            <div className="right">
              <Charts aspect={3 / 1} title="User Spending In Last 6 Months"/>
            </div>
          </div> 
        <div className="bottom">
        <h1 className="title">Last Transactions</h1>
          <TableList/>
        </div>
      </div>
    </div>
  )
}

export default Single