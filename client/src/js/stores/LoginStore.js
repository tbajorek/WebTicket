import React from 'react';
import Reflux from 'reflux';
import createHistory from 'history/createHashHistory';

import AbstractAjaxStore from './AbstractAjaxStore';
import LoginActions from '../actions/LoginActions';
import AuthActions from '../actions/AuthActions';

const history = createHistory();

/**
 * Store for login
 */
class LoginStore extends AbstractAjaxStore {
    constructor() {
        super();
        this.state = {
            email: 'admin@example.com',
            password: 'qwerty',
            submitted: false
        };
        this.listenables = LoginActions;
    }

    onChangeEmail(event) {
        this.setState({email: event.target.value});
    }

    onChangePassword(event) {
        this.setState({password: event.target.value});
    }

    onLogin(event) {
        this.setState({submitted: true});
        event.stopPropagation();
        this.postData('auth', null, {
            email: this.state.email,
            password: this.state.password
        }, (response)=>{
            AuthActions.login(response.data.user, response.data.token);
            history.push('/');
        }, (error)=>{
            this.setState({submitted: false});
        });

        return false;
    }
}

export default LoginStore;