import React from 'react';
import { Row, Glyphicon, Button } from 'react-bootstrap';
import { checkRole, ADMIN } from '../../tools/CheckRole';
import TicketActions from '../../actions/TicketActions';

/**
 * Component of single ticket menu
 */
const TicketMenu = (props) => {
    "use strict";
    const {closed, user, ticket, token} = props;
    var buttons = {
        take: null,
        rate: null,
        close: null,
        remove: null
    };

    if(ticket.author.id===user.id) {
        if(closed===false) {
            buttons.close = (
                <Button bsStyle="success"
                        onClick={(e)=>{TicketActions.closeTicket(ticket.id, token)}}>
                    <Glyphicon glyph="ok"/>&nbsp;Zamknij
                </Button>
            );
        } else if(ticket.recipient != null && ticket.rate == null) {
            buttons.rate = (
                <Button bsStyle="success"
                        onClick={(e)=>{TicketActions.showRate(ticket.id);}}>
                    <Glyphicon glyph="thumbs-up"/>&nbsp;Oceń
                </Button>
            );
        }
    } else if(ticket.recipient == null && user.department.id === ticket.department.id) {
        buttons.take = (
            <Button bsStyle="success"
                    onClick={(e)=>{TicketActions.takeTicket(ticket.id, token)}}>
                <Glyphicon glyph="check"/>&nbsp;Przyjmij
            </Button>
        );
    }

    if(checkRole(user, ADMIN)) {
        buttons.remove = (
            <Button bsStyle="danger"
                    onClick={(e)=>{TicketActions.removeTicket(ticket.id, token, 'back')}}>
                <Glyphicon glyph="erase"/>&nbsp;Usuń
            </Button>
        );
    }
    return (
        <Row>
            { buttons.take }
            { buttons.close }
            { buttons.rate }
            { buttons.remove }
        </Row>
    );
};

export default TicketMenu;