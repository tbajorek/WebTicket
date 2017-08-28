import React from 'react';
import Reflux from 'reflux';

import AuthActions from '../actions/AuthActions';
import MessageActions from '../actions/MessageActions';

/**
 * Store for taking globally logged user
 */
class AuthStore extends Reflux.Store {
    constructor() {
        super();
        var savedState = JSON.parse(localStorage.getItem('authData'));
        if(savedState === null) {
            this.state = this.getDefaultState();
        } else {
            this.state = savedState;
        }
        this.listenables = AuthActions;
    }

    getDefaultState() {
        return {
            user: {
                type: {
                    id: 0
                }
            },
            token: null,
            isLogged: false
        };
    }

    onLogin(user, token) {
        this.setState({
            "user": user,
            "token": token,
            "isLogged":true
        });
        localStorage.setItem('authData', JSON.stringify(this.state));
    }

    onLogout() {
        this.setState(this.getDefaultState());
        localStorage.removeItem('authData');
        MessageActions.clearMessages();
        MessageActions.addSuccess('Zostałeś pomyślnie wylogowany');
    }
}

AuthStore.id = 'auth';

export default AuthStore;