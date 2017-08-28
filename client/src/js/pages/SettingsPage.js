import React from 'react';
import AbstractPage from './AbstractPage';
import { Tabs, Tab } from 'react-bootstrap';
import { WORKER } from '../tools/CheckRole';

import SettingsView from '../components/settings/SettingsView';
import PasswordForm from '../components/settings/PasswordForm';

/**
 * Component of page with user settings
 */
class SettingsPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = WORKER;
    }

    safeRender() {
        return (
            <Tabs defaultActiveKey={1} id="settings-tabs">
                <Tab eventKey={1} title="Dane użytkownika">
                    <SettingsView user={this.state.user} token={this.state.token} />
                </Tab>
                <Tab eventKey={2} title="Zmiana hasła">
                    <PasswordForm user={this.state.user} token={this.state.token} />
                </Tab>
            </Tabs>
        );
    }
}

export default SettingsPage;