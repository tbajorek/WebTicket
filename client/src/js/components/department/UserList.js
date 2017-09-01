import React from 'react';
import { Table, DropdownButton, Label } from 'react-bootstrap';
import { Link } from 'react-router-dom';

import { checkRole, MANAGER } from '../../tools/CheckRole';

/**
 * Component of users list
 */
const UserList = ({users, currentUser}) => {
    var tableList = [];
    if (typeof users !=='undefined' && users.length > 0) {
        var rateHeader = null;
        if(checkRole(currentUser, MANAGER)) {
            rateHeader = (<th>Ocena</th>);
        }
        for (var i in users) {
            var rateField = null;
            if(checkRole(currentUser, MANAGER)) {
                rateField = (<td className="userRate">{users[i].rate}</td>);
            }
            tableList.push(
                <tr key={i}>
                    <td>
                        <Link to={'/user/'+users[i].id}>{users[i].name+' '+users[i].surname}</Link>
                    </td>
                    <td>{users[i].department.name}</td>
                    <td>{users[i].position}</td>
                    { rateField }
                </tr>
            );
        }
        return (
            <Table striped bordered condensed hover>
                <thead>
                <tr>
                    <th>Imię i nazwisko</th>
                    <th>Departament</th>
                    <th>Stanowisko</th>
                    { rateHeader }
                </tr>
                </thead>
                <tbody>
                { tableList }
                </tbody>
            </Table>
        );
    } else {
        return null;
    }
};

export default UserList;