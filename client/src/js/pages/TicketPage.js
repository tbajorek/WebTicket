import React from 'react';
import AbstractPage from './AbstractPage';
import '../../css/ticket.css';
import { WORKER } from '../tools/CheckRole';

import TicketView from '../components/ticket/TicketView';

/**
 * Component of page with single ticket
 */
class TicketPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = WORKER;
    }

    safeRender() {
        return (
            <div className="ticketpage container">
                <TicketView user={this.state.user} ticketId={this.props.match.params.ticketId} token={this.state.token} />
            </div>
        );
    }
}

export default TicketPage;