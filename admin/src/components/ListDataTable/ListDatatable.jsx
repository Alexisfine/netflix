import React from 'react'
import './ListDatatable.scss'


import { DataGrid, GridColDef, GridValueGetterParams } from '@mui/x-data-grid';
import {userColumns} from '../../datatablesource';
import { Link } from 'react-router-dom';

const Datatable = ({type, items}) => {
    console.log(items);
    const column = [
        { field: "id", headerName: "ID", width: 150 },
        {
            field: "title",
            headerName: "Title",
            width: 130,
          },
          {
            field: "type",
            headerName: "Type",
            width: 130,
          },
          {
            field: "genre",
            headerName: "Genre",
            width: 130,
          },
          {
            field: "numberOfMovies",
            headerName: "Number of movies",
            width: 160,
            renderCell: (params) => {
                return (
                  <div>{params.row.content.length}</div>
                );
              },
          },
         
        {
            field: "action",
            headerName:"Action",
            width: 200,
            renderCell: () => {
                return (<div className='cellAction'>
                <Link to='/users/123' style={{textDecoration:'none'}}>
                    <div className='viewButton'>Edit</div>
                    </Link>
                    <div className='deleteButton'>Delete</div>
                </div>)
            }
        }
    ]
   
  return (
    <div className='datatable'>
      <div className="datatableTitle">
        Add new {type}
        <Link to='/users/new' className='link'>
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