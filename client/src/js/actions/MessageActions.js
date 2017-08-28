import React from 'react';
import Reflux from 'reflux';

/**
 * Actions for global messages
 */
const MessageActions = Reflux.createActions([
    'addError',
    'addWarning',
    'addInfo',
    'addSuccess',
    'clearMessages',
    'prolongateMessages'
]);

export default MessageActions;