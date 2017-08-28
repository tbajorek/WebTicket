import React from 'react';
import Reflux from 'reflux';
import createHistory from 'history/createHashHistory';

import AbstractAjaxStore from './AbstractAjaxStore';
import UserActions from '../actions/UserActions';
import MessageActions from '../actions/MessageActions';

const history = createHistory();

/**
 * Store for single user
 */
class UserStore extends AbstractAjaxStore {
    constructor() {
        super();
        this.state = this.getInitialState();
        this.listenables = UserActions;
    }

    getInitialState() {
        return {
            name: '',
            surname: '',
            password: '',
            password2: ''
        };
    }

    onClear() {
        this.setState(this.getInitialState());
    }

    onChangeName(e) {
        this.setState({
            name: e.target.value
        });
    }

    onChangeSurname(e) {
        this.setState({
            surname: e.target.value
        });
    }

    onChangePassword(e) {
        this.setState({
            password: e.target.value
        });
    }

    onChangePassword2(e) {
        this.setState({
            password2: e.target.value
        });
    }

    onRegister(hash) {
        if (this.state.password === this.state.password2) {
            this.postData('user/' + hash, null, {
                name: this.state.name,
                surname: this.state.surname,
                password: this.state.password
            }, (response) => {
                setTimeout(()=> {
                    history.push('/login');
                }, 2000);
                this.setState({
                    submitted: false
                });
            }, (error) => {
                this.setState({
                    submitted: false
                });
            });
        } else {
            MessageActions.clearMessages();
            MessageActions.addError('Podane hasła nie są identyczne');
            this.setState({
                submitted: false
            });
        }
    }
}

export default UserStore;