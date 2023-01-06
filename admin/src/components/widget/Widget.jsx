import React from 'react'
import './Widget.scss'
import {AccountBalanceWalletOutlined, KeyboardArrowUp, MonetizationOnOutlined, PersonOutlined, ShoppingCartOutlined} from '@mui/icons-material'

const Widget = ({type}) => {
    let data;
    const amount  = 800;
    const diff = 20;
    switch(type) {
        case "user":
            data = {
                title: 'USERS',
                isMoney: false,
                link:"See all users",
                icon: (<PersonOutlined className='icon' style={{color:"crimson", backgroundColor: "rgba(255,0,0,0.2)"}}/>)
            };
            break;
        case "order":
            data = {
                title: 'ORDERS',
                isMoney: false,
                link:"View all orders",
                icon: (<ShoppingCartOutlined  className='icon' style={{color:"green", backgroundColor: "rgba(0,128,0,0.2)"}}/>)
            };
            break;
        case "earning":
            data = {
                title: 'EARNINGS',
                isMoney: true,
                link:"View net earnings",
                icon: (<MonetizationOnOutlined className='icon' style={{color:"crimson", backgroundColor: "rgba(255,0,0,0.2)"}}/>)
            };
            break;   
        case "balance":
        data = {
            title: 'BALANCE',
            isMoney: true,
            link:"See details",
            icon: (<AccountBalanceWalletOutlined className='icon' style={{color:"purple", backgroundColor: "rgba(128,0,128,0.2)"}}/>)
        };
        break; 
        default: 
            break;
    }
  return (
    <div className='widget'>
        <div className="left">
            <div className="title">{data.title}</div>
            <div className="counter">{data.isMoney && '$'} {amount}</div>
            <div className="link">{data.link}</div>

        </div>
        <div className="right">
            <div className="percentage positive">
                <KeyboardArrowUp/>20%
            </div>
            {data.icon}
        </div>

    </div>
  )
}

export default Widget