import React from 'react';
import Reflux from 'reflux';
import { Table, Button } from 'react-bootstrap';
import { withRouter } from 'react-router';

import SettingsActions from '../../actions/SettingsActions';
import SettingsStore from '../../stores/SettingsStore';

/**
 * Component of settings view
 */
class SettingsView extends Reflux.Component {
    constructor(props)
    {
        super(props);
        this.store = SettingsStore;
    }

    componentWillMount() {
        super.componentWillMount();
        SettingsActions.loadSettings(this.props.user.id, this.props.token);
    }

    render() {
        const { email, name, surname, department, position } = this.state;

        return (
            <div>
                <Table striped bordered condensed hover>
                    <tbody>
                    <tr>
                        <td>Imię</td>
                        <td>{name}</td>
                    </tr>
                    <tr>
                        <td>Nazwisko</td>
                        <td>{surname}</td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>{email}</td>
                    </tr>
                    <tr>
                        <td>Departament</td>
                        <td>{department.name}</td>
                    </tr>
                    <tr>
                        <td>Stanowisko</td>
                        <td>{position}</td>
                    </tr>
                    </tbody>
                </Table>
                <Button bsStyle="primary" bsSize="large" block onClick={(e) => this.props.history.push('/settings/edit')}>
                    Edytuj ustawienia
                </Button>
            </div>
        );
    }
}

export default withRouter(SettingsView);