import React from 'react';
import AbstractPage from './AbstractPage';
import '../../css/ticket.css';
import { WORKER } from '../tools/CheckRole';

import TicketForm from '../components/ticket/TicketForm';

/**
 * Component of page with new ticket page
 */
class NewTicketPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = WORKER;
    }

    safeRender() {
        return (
            <div className="newticketpage container">
                <TicketForm user={this.state.user} token={this.state.token} />
            </div>
        );
    }
}

export default NewTicketPage;