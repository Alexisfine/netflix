import React from 'react'
import './Datatable.scss';
import { DataGrid, GridColDef, GridValueGetterParams } from '@mui/x-data-grid';
import {userColumns, userRows} from '../../datatablesource';
import { Link } from 'react-router-dom';
const Datatable = ({type, items}) => {
  console.log(items);
    const actionColumn = [{
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
        rows={userRows}
        columns={userColumns.concat(actionColumn)}
        pageSize={9}
        rowsPerPageOptions={[9]}
        checkboxSelection
      />
    </div>
  )
}

export default Datatable