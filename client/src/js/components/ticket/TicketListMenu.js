import React from 'react';
import createHistory from 'history/createHashHistory';
import { DropdownButton } from 'react-bootstrap';

import MenuItem from '../menu/element/MenuItem';
import { ADMIN } from '../../tools/CheckRole';
import TicketStore from '../../stores/TicketStore';

const history = createHistory();
const ticketStore = new TicketStore();

/**
 * Component of tickets list menu
 */
const TicketListMenu = (props) => {
    "use strict";
    const { action, ticket, user, token } = props;

    var actionButton = null;
    if(ticket.author.id===user.id) {
        if(ticket.closed == null) {
            actionButton = (<MenuItem eventKey="3" onClick={(e)=>{ticketStore.onCloseTicket(ticket.id, token);}}>Zamknij</MenuItem>);
        }
    } else if(ticket.closed == null && ticket.recipient == null && user.department.id === ticket.department.id) {
        actionButton = (<MenuItem eventKey="4" onClick={(e)=>{ticketStore.onTakeTicket(ticket.id, token);}}>Przyjmij</MenuItem>);
    }

    return (
        <DropdownButton title="Akcje" id="bg-nested-dropdown">
            <MenuItem eventKey="1" onClick={(e)=>{history.push('/ticket/'+ticket.id);}}>
                Podgląd
            </MenuItem>
            { actionButton }
            <MenuItem eventKey="5" user={user} allowedRole={ADMIN} onClick={(e)=>{ticketStore.onRemoveTicket(ticket.id, token, action);}}>Usuń</MenuItem>
        </DropdownButton>
    );
};

export default TicketListMenu;