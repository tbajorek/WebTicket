import React from 'react';
import { Table, DropdownButton, Label } from 'react-bootstrap';
import { Link } from 'react-router-dom';

import TicketListMenu from './TicketListMenu';
import MenuItem from '../menu/element/MenuItem';
import { ADMIN } from '../../tools/CheckRole';

/**
 * Component of tickets list
 */
const TicketList = ({action, tickets, user, token}) => {
    var tableList = [];
    if (tickets.length > 0) {
        for (var i in tickets) {
            var closed = null;
            if(tickets[i].closed != null) {
                closed = (<Label bsStyle="success">closed</Label>);
            }
            var rated = null;
            if(tickets[i].rate != null) {
                rated = (<Label bsStyle="warning">{tickets[i].rate}</Label>);
            }
            var recipient = '---';
            if(tickets[i].recipient != null) {
                recipient = (<Link to={'/user/'+tickets[i].recipient.id}>{tickets[i].recipient.name + ' ' + tickets[i].recipient.surname}</Link>);
            }
            tableList.push(
                <tr key={i}>
                    <td>
                        <Link to={'/ticket/'+tickets[i].id}>{tickets[i].title}</Link>&nbsp;
                        { closed }&nbsp;{rated}
                    </td>
                    <td>{tickets[i].department.name}</td>
                    <td>{recipient}</td>
                    <td><Link to={'/user/'+tickets[i].author.id}>{tickets[i].author.name + ' ' + tickets[i].author.surname}</Link></td>
                    <td>{(new Date(tickets[i].created)).toDateString()}</td>
                    <td>{(new Date(tickets[i].lastModified)).toDateString()}</td>
                    <td>
                        <TicketListMenu ticket={tickets[i]} user={user} token={token} action={action} />
                    </td>
                </tr>
            );
        }
        return (<Table striped bordered condensed hover>
            <thead>
            <tr>
                <th>Tytuł</th>
                <th>Departament</th>
                <th>Wykonawca</th>
                <th>Autor</th>
                <th>Data utworzenia</th>
                <th>Ostatnio modyfikowany</th>
                <th>Operacje</th>
            </tr>
            </thead>
            <tbody>
            { tableList }
            </tbody>
        </Table>);
    } else {
        return null;
    }
};

export default TicketList;