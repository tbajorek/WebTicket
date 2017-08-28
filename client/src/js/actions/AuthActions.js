import React from 'react';
import Reflux from 'reflux';

/**
 * Actions for setting main user
 */
const AuthActions = Reflux.createActions([
    'login',
    'logout'
]);

export default AuthActions;