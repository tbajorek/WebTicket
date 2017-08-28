import React from 'react';
import Reflux from 'reflux';

/**
 * Actions for ticket
 */
const TicketActions = Reflux.createActions([
    'clearTicket',
    'changeTitle',
    'changeDepartment',
    'loadTicket',
    'takeTicket',
    'addTicket',
    'saveTicket',
    'closeTicket',
    'removeTicket',
    'showRate',
    'setRate',
    'rate',
    'hideRate'
]);

export default TicketActions;