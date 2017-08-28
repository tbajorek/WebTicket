import React from 'react';
import AbstractPage from './AbstractPage';
import { WORKER } from '../tools/CheckRole';

import AdoptedTicketList from '../components/ticket/AdoptedTicketList';

/**
 * Component of page with list of adopted tickets
 */
class AdoptedTicketsPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = WORKER;
    }

    safeRender() {
        return (
            <div className="mytickets container">
                <h2>Przyjęte zgłoszenia</h2>
                <AdoptedTicketList user={this.state.user} token={this.state.token} />
            </div>
        );
    }
}

export default AdoptedTicketsPage;