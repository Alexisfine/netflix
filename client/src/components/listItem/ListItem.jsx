import { Add, PlayArrow, ThumbDownOutlined, ThumbUpOutlined } from '@material-ui/icons'
import React, { useState } from 'react'
import { Link } from 'react-router-dom';
import './ListItem.scss'

const ListItem = ({index, item}) => {
  const [isHovered, setIsHovered] = useState(false);
  const trailer =
    "https://player.vimeo.com/external/371433846.sd.mp4?s=236da2f3c0fd273d2c6d9a064f3ae35579b2bbdf&profile_id=139&oauth2_token_id=57447761";
  const image = "https://occ-0-1723-92.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABU7D36jL6KiLG1xI8Xg_cZK-hYQj1L8yRxbQuB0rcLCnAk8AhEK5EM83QI71bRHUm0qOYxonD88gaThgDaPu7NuUfRg.jpg?r=4ee"  
  const description = "Lorem ipsum dolor, sit amet consectetur adipisicing elit.Praesentium hic rem eveniet error possimus, neque ex doloribus."
  return (
    <Link to='/watch' state={{movie:item}}>
    <div className='listItem' style={{left:isHovered && index * 225 - 50 + index * 0.5}}
      onMouseEnter={()=>setIsHovered(true)} onMouseLeave={()=>setIsHovered(false)}>
            <img
        src={item.image ? item.image : image}
        alt=""
      />
      {isHovered && (
        <>
        <video src={item.trailer ? item.trailer : trailer} autoPlay={true} loop/>
        <div className="itemInfo">

        <div className="icons">
          <PlayArrow className='icon'/>
          <Add className='icon'/>
          <ThumbUpOutlined className='icon'/>
          <ThumbDownOutlined className='icon'/>
        </div>
        <div className="itemInfoTop">
          <span>1 hour 14 minutes</span>
          <span className='limit'>+ {item.limit ? item.limit : "16"}</span>
          <span>{item.year ? item.year : "2023"}</span>
          </div>
          <div className="description">
              {item.description ? item.description : description}
            </div>
            <div className="genre">{item.genre ? item.genre : 'Action'}</div>
        
      </div>
      </>
      )}
    </div>
    </Link>
  )
}

export default ListItem