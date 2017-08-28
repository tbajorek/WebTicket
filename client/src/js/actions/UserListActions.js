import React from 'react';
import Reflux from 'reflux';

/**
 * Actions for user list
 */
const UserListActions = Reflux.createActions([
    'clearUsers',
    'loadUsers'
]);

export default UserListActions;