import React from 'react';
import { Table, DropdownButton, Label } from 'react-bootstrap';
import { Link } from 'react-router-dom';

import DepartmentListMenu from './DepartmentListMenu';

/**
 * Component of department list view
 */
const DepartmentListView = (props) => {
    const {departments, user, token} = props;
    var tableList = [];
    if (departments.length > 0) {
        for (var i in departments) {
            tableList.push(
                <tr key={i}>
                    <td>
                        <Link to={'/department/'+departments[i].id}>{departments[i].name}</Link>
                    </td>
                    <td><DepartmentListMenu department={departments[i]} token={token} index={i} /></td>
                </tr>
            );
        }
        return (
            <div>
                <Table striped bordered condensed hover>
                    <thead>
                    <tr>
                        <th>Nazwa</th>
                        <th>Operacje</th>
                    </tr>
                    </thead>
                    <tbody>
                    { tableList }
                    </tbody>
                </Table>
            </div>
        );
    } else {
        return null;
    }
};

export default DepartmentListView;