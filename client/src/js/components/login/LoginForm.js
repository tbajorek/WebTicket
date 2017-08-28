import React from 'react';
import Reflux from 'reflux';

import LoginActions from '../../actions/LoginActions';
import LoginStore from '../../stores/LoginStore';

/**
 * Component of login form
 */
class LoginForm extends Reflux.Component {
    constructor(props)
    {
        super(props);
        this.store = LoginStore;
    }

    render() {
        const { submitted, email, password } = this.state;

        return (
            <form className="form-signin">
                <h2 className="form-signin-heading">Proszę się zalogować</h2>
                <label htmlFor="inputEmail" className="sr-only">Adres email</label>
                <input
                    type="email"
                    id="inputEmail"
                    className="form-control"
                    placeholder="Email address"
                    required
                    autoFocus
                    value={email}
                    onChange={LoginActions.changeEmail}/>
                <label htmlFor="inputPassword" className="sr-only">Hasło</label>
                <input
                    type="password"
                    id="inputPassword"
                    className="form-control"
                    placeholder="Password"
                    required
                    value={password}
                    onChange={LoginActions.changePassword} />
                <button
                    className="btn btn-lg btn-primary btn-block"
                    type="submit"
                    disabled={submitted}
                    onClick={LoginActions.login}>Zaloguj się
                </button>
            </form>
        );
    }
}

export default LoginForm;