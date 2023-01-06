import { ArrowBackOutlined } from '@material-ui/icons';
import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import './Watch.scss';

const Watch = () => {
  const location = useLocation();
  const movie = location?.state; 
  const defaultMovie = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
  return (
    <div className='watch'>
        <Link to='/'>
        <div className="back">
            <ArrowBackOutlined/>
            Home
        </div>
        </Link>
        <video className='video' autoPlay progress controls src={movie.video ? movie.video : defaultMovie}></video>
    </div>
  )
}

export default Watch