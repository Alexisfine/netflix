import { ArrowBackIosOutlined, ArrowForwardIosOutlined } from '@material-ui/icons'
import React, { useRef, useState } from 'react'
import ListItem from '../listItem/ListItem'
import './List.scss'

const List = ({list}) => {
    const [slideNum, setSlideNum] = useState(0);
    const [moved, setMoved] = useState(false);
    const listRef = useRef();

    
    const handleClick = (direction) => {
        setMoved(true);
        let distance = listRef.current.getBoundingClientRect().x - 58;
        if (direction === "left" && slideNum > 0) {
      listRef.current.style.transform = `translateX(${230 + distance}px)`;
      setSlideNum(slideNum - 1);
        }
        if (direction === "right" && slideNum < 5) {
        listRef.current.style.transform = `translateX(${-230 + distance}px)`;
        setSlideNum(slideNum + 1)
    }
    }
  return (
    <div className='list'>
        <span className="listTitle">{list.title}</span>
        <div className="wrapper">
            <ArrowBackIosOutlined className='sliderArrow left' style={{display: !moved && 'none'}}
            onClick={() => handleClick('left')}/>
            <div className="container" ref={listRef}>
              {list.content?.map((item, i) => (
                <ListItem index={i} key={i} item={item}/>
              ))}
            </div>
            <ArrowForwardIosOutlined className='sliderArrow right' onClick={() => handleClick('right')}/>
        </div>
    </div>
  )
}

export default List