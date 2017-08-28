import React from 'react';
import Reflux from 'reflux';

/**
 * Actions for invitation
 */
const InvitationActions = Reflux.createActions([
    'setDepartment',
    'changeEmail',
    'changePosition',
    'changeDepartment',
    'changeAccountType',
    'loadAccountTypes',
    'clear',
    'loadList',
    'show',
    'hide',
    'addNew',
    'remove'
]);

export default InvitationActions;