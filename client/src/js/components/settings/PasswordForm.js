import React from 'react';
import Reflux from 'reflux';
import '../../../css/login.css';

import SettingsActions from '../../actions/SettingsActions';
import SettingsStore from '../../stores/SettingsStore';

/**
 * Component of password form
 */
class PasswordForm extends Reflux.Component {
    constructor(props)
    {
        super(props);
        this.store = SettingsStore;
    }

    render() {
        const { submitted, oldPassword, password, password2 } = this.state;

        return (
            <form className="form-signin">
                <label htmlFor="inputOldPassword" className="sr-only">Stare hasło</label>
                <input
                    type="password"
                    id="inputOldPassword"
                    className="form-control"
                    placeholder="Stare hasło"
                    required
                    value={oldPassword}
                    onChange={SettingsActions.changeOldPassword} />
                <label htmlFor="inputPassword" className="sr-only">Nowe hasło</label>
                <input
                    type="password"
                    id="inputPassword"
                    className="form-control"
                    placeholder="Nowe hasło"
                    required
                    value={password}
                    onChange={SettingsActions.changePassword} />

                <label htmlFor="inputPassword2" className="sr-only">Powtórz nowe hasło</label>
                <input
                    type="password"
                    id="inputPassword2"
                    className="form-control"
                    placeholder="Powtórz nowe hasło"
                    required
                    value={password2}
                    onChange={SettingsActions.changePassword2} />
                <button
                    className="btn btn-lg btn-primary btn-block"
                    type="submit"
                    disabled={submitted}
                    onClick={SettingsActions.performNewPassword}>Zmień hasło
                </button>
            </form>
        );
    }
}

export default PasswordForm;