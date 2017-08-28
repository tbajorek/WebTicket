import React from 'react';
import Reflux from 'reflux';
import { FormGroup, FormControl, Button, ControlLabel } from 'react-bootstrap';

import FieldGroup from '../form/FieldGroup';
import SettingsActions from '../../actions/SettingsActions';
import SettingsStore from '../../stores/SettingsStore';

/**
 * Component of settings form
 */
class SettingsForm extends Reflux.Component {
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
        const { submitted, email, name, surname } = this.state;

        return (
            <form className="form-settings">
                <h3>Edytuj dane konta</h3>
                <FieldGroup
                    id="settingsEmail"
                    type="text"
                    label="Adres email"
                    placeholder="Adres email"
                    value={email}
                    onChange={SettingsActions.changeEmail}
                />
                <FieldGroup
                    id="settingsName"
                    type="text"
                    label="Imię"
                    placeholder="Imię"
                    value={name}
                    onChange={SettingsActions.changeName}
                />
                <FieldGroup
                    id="settingsSurname"
                    type="text"
                    label="Nazwisko"
                    placeholder="Nazwisko"
                    value={surname}
                    onChange={SettingsActions.changeSurname}
                />
                <button
                    className="btn btn-lg btn-primary btn-block"
                    type="submit"
                    disabled={submitted}
                    onClick={SettingsActions.changeProfileData}>Zmień dane
                </button>
            </form>
        );
    }
}

export default SettingsForm;