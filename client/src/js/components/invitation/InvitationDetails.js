import React from 'react';
import { Table } from 'react-bootstrap';
import UrlField from './UrlField';

/**
 * Component of invitation details
 */
const InvitationDetails = (props) => {
    "use strict";
    const { invitation } = props;

    return (
        <Table striped bordered condensed hover>
            <tbody>
            <tr>
                <td>Email</td>
                <td>{invitation.email}</td>
            </tr>
            <tr>
                <td>Departament</td>
                <td>{invitation.department.name}</td>
            </tr>
            <tr>
                <td>Stanowisko</td>
                <td>{invitation.position}</td>
            </tr>
            <tr>
                <td>Rodzaj konta</td>
                <td>{invitation.type.name}</td>
            </tr>
            <tr>
                <td>Adres do zaproszenia</td>
                <td><UrlField hash={invitation.hash} /></td>
            </tr>
            </tbody>
        </Table>
    );
};

export default InvitationDetails;