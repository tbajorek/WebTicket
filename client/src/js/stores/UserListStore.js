import React from 'react';
import Reflux from 'reflux';

import AbstractAjaxStore from './AbstractAjaxStore';
import UserListActions from '../actions/UserListActions';
import MessageActions from '../actions/MessageActions';

/**
 * Store for user list
 */
class UserListStore extends AbstractAjaxStore {
    constructor() {
        super();
        this.state = {
            users: []
        };
        this.listenables = UserListActions;
    }

    onClearUsers() {
        this.setState({
            users: []
        });
    }

    onLoadUsers(departmentId, token) {
        this.getData('department/'+departmentId+'/users', token, (response) => {
            if(typeof response.data !== 'undefined' && typeof response.data.users !== 'undefined') {
                var newUsers = response.data.users;
            } else {
                var newUsers = [];
            }
            this.setState({
                users: newUsers
            });
        });
    }
}

export default UserListStore;