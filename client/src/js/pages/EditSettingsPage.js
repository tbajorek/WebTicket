import React from 'react';
import AbstractPage from './AbstractPage';
import { WORKER } from '../tools/CheckRole';

import SettingsForm from '../components/settings/SettingsForm';

/**
 * Component of page with user settings
 */
class EditSettingsPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = WORKER;
    }

    safeRender() {
        return (
            <SettingsForm departmentId={this.props.match.params.departmentId} user={this.state.user} token={this.state.token} />
        );
    }
}

export default EditSettingsPage;