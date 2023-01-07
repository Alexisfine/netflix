import React, { useEffect, useState } from 'react'
import './Widget.scss'
import {AccountBalanceWalletOutlined, KeyboardArrowUp, MonetizationOnOutlined, Movie, PersonOutlined, ShoppingCartOutlined} from '@mui/icons-material'
import { Link } from 'react-router-dom';
import axios from 'axios';

const Widget = ({type}) => {
    let data;
    const amount  = 800;
    const diff = 20;

    const token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3MzcxODIxOX0.GVhIpqNRl2q-ZZOU6Ipsi35QIQmu0gsoEEn5keEvtAu0WnB7rnlYcoOd7IxTrFYZ6K-_n3gGYHwI8R6JtlgLAg'
    const config = {
      headers : {Authorization:`Bearer ${token}`}
    }

    const [totalUsers, setTotalUsers] = useState(0);
    useEffect(() => {
        const getTotalUsers = async () => {
            try {
                const res = await axios.get('/users/data/total-users', config);
                console.log(res)
                setTotalUsers(res.data);
            } catch (err) {
                console.log(err);
            }
        }
        getTotalUsers();
    },[])

    console.log(totalUsers);

    switch(type) {
        case "user":
            data = {
                title: 'USERS',
                isMoney: false,
                link:"See all users",
                amount: totalUsers,
                icon: (<PersonOutlined className='icon' style={{color:"crimson", backgroundColor: "rgba(255,0,0,0.2)"}}/>)
            };
            break;
        case "movie":
            data = {
                title: 'MOVIES',
                isMoney: false,
                link:"View all orders",
                amount: 1200,
                icon: (<Movie  className='icon' style={{color:"green", backgroundColor: "rgba(0,128,0,0.2)"}}/>)
            };
            break;
        case "earning":
            data = {
                title: 'EARNINGS',
                isMoney: true,
                link:"View net earnings",
                amount: 820,
                icon: (<MonetizationOnOutlined className='icon' style={{color:"crimson", backgroundColor: "rgba(255,0,0,0.2)"}}/>)
            };
            break;   
        case "balance":
        data = {
            title: 'BALANCE',
            isMoney: true,
            link:"See details",
            amount: 920,
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
            <div className="counter">{data.isMoney && '$'} {data.amount}</div>
            <Link to={`/${data.title.toLowerCase()}`} style={{textDecoration:'none',color:'inherit'}}><div className="link">{data.link}</div></Link>

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