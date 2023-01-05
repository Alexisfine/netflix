import { ArrowBackOutlined } from '@material-ui/icons';
import React from 'react';
import './Watch.scss';

const Watch = () => {
  return (
    <div className='watch'>
        <div className="back">
            <ArrowBackOutlined/>
            Home
        </div>
        <video className='video' autoPlay progress controls src="https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"></video>
    </div>
  )
}

export default Watch