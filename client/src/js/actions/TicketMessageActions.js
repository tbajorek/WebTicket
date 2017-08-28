import React from 'react';
import Reflux from 'reflux';

/**
 * Actions for messages of tickets
 */
const TicketMessageActions = Reflux.createActions([
    'clearMessage',
    'changeTicket',
    'changeContent',
    'loadMessage',
    'addMessage',
    'saveMessage',
    'removeMessage'
]);

export default TicketMessageActions;