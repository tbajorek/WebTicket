import React from 'react';
import Reflux from 'reflux';

/**
 * Actions for ticket list
 */
const TicketListActions = Reflux.createActions([
    'clearTickets',
    'loadMyTickets',
    'loadAdoptedTickets',
    'loadForDepartment'
]);

export default TicketListActions;