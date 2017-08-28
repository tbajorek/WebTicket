import React from 'react';
import Reflux from 'reflux';

import MessageActions from '../actions/MessageActions';
import AuthStore from '../stores/AuthStore';
import Error from '../components/message/Error';

import { checkRole, GUEST } from '../tools/CheckRole';

/**
 * Component of abstract page what provides authorization functionality
 */
class AbstractPage extends Reflux.Component {
    constructor(props) {
        super(props);
        this.stores = [AuthStore];
        this.allowedRole = GUEST;
    }

    componentWillMount() {
        super.componentWillMount();
        const { history } = this.props;
        this.unsubscribeFromHistory = history.listen(this.onChangeLocation);
        this.onChangeLocation(history.location);
    }

    safeRender() {
        return null;
    }

    render() {
        if(!checkRole(this.state.user, this.allowedRole)) {
            const { history } = this.props;
            setTimeout(()=> {
                history.push('/login');
            }, 2000);
            return (<Error message="Nie masz uprawnień do przeglądania tej strony" />);
        } else {
            return this.safeRender();
        }
    }

    componentWillUnmount() {
        super.componentWillUnmount();
        if (this.unsubscribeFromHistory) {
            this.unsubscribeFromHistory();
        }
    }

    onChangeLocation = (location) => {
        MessageActions.clearMessages();
    };
}

export default AbstractPage;