import React from 'react';
import { Table, DropdownButton, Label } from 'react-bootstrap';
import { Link } from 'react-router-dom';

import InvitationListMenu from './InvitationListMenu';

/**
 * Component of invitation list
 */
const InvitationList = (props) => {
    const {invitations, user, token} = props;
    var tableList = [];
    if (invitations.length > 0) {
        for (var i in invitations) {
            tableList.push(
                <tr key={i}>
                    <td>
                        <a href={'mailto:'+invitations[i].email+'?subject=Zaproszenie%20do%20programu%20WebTicket'} target="_top">{invitations[i].email}</a>
                    </td>
                    <td>{invitations[i].department.name}</td>
                    <td>{invitations[i].position}</td>
                    <td>{invitations[i].type.name}</td>
                    <td><InvitationListMenu invitation={invitations[i]} user={user} token={token} index={i} /></td>
                </tr>
            );
        }
        return (
            <div>
                <Table striped bordered condensed hover>
                    <thead>
                    <tr>
                        <th>Adres email</th>
                        <th>Departament</th>
                        <th>Stanowisko</th>
                        <th>Typ konta</th>
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

export default InvitationList;