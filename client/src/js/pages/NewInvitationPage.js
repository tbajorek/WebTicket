import React from 'react';
import AbstractPage from './AbstractPage';
import { MANAGER } from '../tools/CheckRole';

import InvitationForm from '../components/invitation/InvitationForm';

/**
 * Component of page with new invitation form
 */
class NewInvitationPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = MANAGER;
    }

    safeRender() {
        return (
            <InvitationForm departmentId={this.props.match.params.departmentId} user={this.state.user} token={this.state.token} />
        );
    }
}

export default NewInvitationPage;