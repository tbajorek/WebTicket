import React from 'react';
import Reflux from 'reflux';

/**
 * Actions for authentication
 */
const LoginActions = Reflux.createActions([
    'changeEmail',
    'changePassword',
    'login'
]);

export default LoginActions;