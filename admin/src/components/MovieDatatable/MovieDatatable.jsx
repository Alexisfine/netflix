import React, { useContext } from 'react'
import './MovieDatatable.scss'


import { DataGrid, GridColDef, GridValueGetterParams } from '@mui/x-data-grid';
import {userColumns} from '../../datatablesource';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../../context/authContext/authContext';


const Datatable = ({type, items}) => {
  const navigate = useNavigate();
  const {user} = useContext(AuthContext);
  const config = {
    headers : {Authorization:`Bearer ${user?.token}`}
  }

  const handleDelete = async (id) => {
     try {
        await axios.delete(`/movies/${id}`, config);
        navigate(0);
     } catch (err){
        console.log(err);
     }
  }
    const column = [
        { field: "id", headerName: "ID", width: 150 },
        {
            field: "title",
            headerName: "Title",
            width: 130,
          },
          {
            field: "imageSmall",
            headerName: "Image",
            width: 140,
            renderCell: (params) => {
              return (
                <div className="cellWithImg">
                  <img className="cellImg" src={params.row.imageSmall} alt="" />
                </div>
              );
            },
          },
          {
            field: "movieStatus",
            headerName: "Movie Status",
            width: 130,
            renderCell: (params) => {
                return (
                    <div className={`cellWithStatus ${params.row.movieStatus}`}>
                      {params.row.movieStatus}
                    </div>
                  );
            }
          }, 
          {
            field: "genre",
            headerName: "Genre",
            width: 130,
          }, 
          {
            field: "series",
            headerName: "Is Series",
            width: 130,
            renderCell: (params) => {
                return (
                    <div>{params.row.series ? "Yes" : "No"}
                    </div>
                );
            }
          }, 
        
        {
            field: "action",
            headerName:"Action",
            width: 200,
            renderCell: (params) => {
                return (<div className='cellAction'>
                <Link to='/users/123' style={{textDecoration:'none'}}>
                    <div className='viewButton'>Edit</div>
                    </Link>
                    <div className='deleteButton' onClick={() => handleDelete((params.row.id))}>Delete</div>
                </div>)
            }
        }
    ]
   
  return (
    <div className='datatable'>
      <div className="datatableTitle">
        Add new {type}
        <Link to='/movies/new' className='link'>
        Add New
        </Link>
      </div>
        <DataGrid
        rows={items}
        columns={column}
        pageSize={20}
        rowsPerPageOptions={[20, 50]}
        checkboxSelection
      />
    </div>
  )
}

export default Datatable