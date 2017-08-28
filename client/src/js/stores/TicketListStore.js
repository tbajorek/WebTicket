import React from 'react';
import Reflux from 'reflux';

import AbstractAjaxStore from './AbstractAjaxStore';
import TicketListActions from '../actions/TicketListActions';
import MessageActions from '../actions/MessageActions';

/**
 * Store for tickets list
 */
class TicketListStore extends AbstractAjaxStore {
    constructor() {
        super();
        this.state = {
            tickets: []
        };
        this.listenables = TicketListActions;
    }

    onClearTickets() {
        this.setState({
            tickets: []
        });
    }

    onLoadMyTickets(token) {
        this.getData('ticket/my', token, (response) => {
            if(typeof response.data !== 'undefined' && typeof response.data.tickets !== 'undefined') {
                var newTickets = response.data.tickets;
            } else {
                var newTickets = [];
            }
            this.setState({
                tickets: newTickets
            });
        });
    }

    onLoadAdoptedTickets(token) {
        this.getData('ticket/adopted', token, (response) => {
            if(typeof response.data !== 'undefined' && typeof response.data.tickets !== 'undefined') {
                var newTickets = response.data.tickets;
            } else {
                var newTickets = [];
            }
            this.setState({
                tickets: newTickets
            });
        });
    }

    onLoadForDepartment(departmentId, token) {
        this.getData('department/'+departmentId+'/tickets', token, (response) => {
            if(typeof response.data !== 'undefined' && typeof response.data.tickets !== 'undefined') {
                var newTickets = response.data.tickets;
            } else {
                var newTickets = [];
            }
            this.setState({
                tickets: newTickets
            });
        });
    }
}

export default TicketListStore;