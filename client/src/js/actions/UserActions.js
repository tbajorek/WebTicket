import React from 'react';
import Reflux from 'reflux';

/**
 * Actions for user
 */
const UserActions = Reflux.createActions([
    'clear',
    'changeName',
    'changeSurname',
    'changePassword',
    'changePassword2',
    'register'
]);

export default UserActions;