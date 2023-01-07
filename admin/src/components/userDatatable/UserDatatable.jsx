import React from 'react'
import './UserDatatable.scss';
import { DataGrid, GridColDef, GridValueGetterParams } from '@mui/x-data-grid';
import {userColumns} from '../../datatablesource';
import { Link } from 'react-router-dom';

const Datatable = ({type, items}) => {
    console.log(items);
    const column = [
        { field: "id", headerName: "ID", width: 150 },
        {
            field: "user",
            headerName: "User",
            width: 200,
            renderCell: (params) => {
              return (
                <div className="cellWithImg">
                  <img className="cellImg" src={params.row.profilePic} alt="" />
                  {params.row.username}
                </div>
              );
            },
          },
          {
            field: "roles",
            headerName: "Roles",
            width: 170,
            renderCell: (params) => {
                return (
                    <div>{params.row.roles.map(role => (<span key={params.row.id}>{role.title} </span>))}</div>
                )
            }
          }, 
          {
            field: "enabled",
            headerName: "Enabled",
            width: 160,
            renderCell: (params) => {
              return (
                <div className={`cellWithEnabled ${params.row.enabled}`}>
                  {params.row.enabled ? "Yes" : "No"}
                </div>
              );
            },
          }, 
          {
            field: "locked",
            headerName: "Locked",
            width: 160,
            renderCell: (params) => {
              return (
                <div className={`cellWithLocked ${params.row.locked}`}>
                  {params.row.locked ? "Yes" : "No"}
                </div>
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
                    <div className='viewButton'>View</div>
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