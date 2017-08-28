import React from 'react';
import Reflux from 'reflux';
import { FormGroup, FormControl, Button, ControlLabel } from 'react-bootstrap';

import UserStore from '../../stores/UserStore';
import UserActions from '../../actions/UserActions';

import FieldGroup from '../form/FieldGroup';

/**
 * Component of register form
 */
class RegisterForm extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = UserStore;
    }

    componentWillMount() {
        super.componentWillMount();
        UserActions.clear();
    }

    render() {
        return (
            <form>
                <h3>Rejestracja użytkownika</h3>
                <FieldGroup
                    id="invitationName"
                    type="text"
                    label="Imię"
                    placeholder="Imię"
                    value={this.state.name}
                    onChange={UserActions.changeName}
                />
                <FieldGroup
                    id="invitationSurname"
                    type="text"
                    label="Nazwisko"
                    placeholder="Nazwisko"
                    value={this.state.surname}
                    onChange={UserActions.changeSurname}
                />
                <FieldGroup
                    id="invitationPassword"
                    type="password"
                    label="Hasło"
                    placeholder="Hasło"
                    value={this.state.password}
                    onChange={UserActions.changePassword}
                />
                <FieldGroup
                    id="invitationPassword2"
                    type="password"
                    label="Powtórz hasło"
                    placeholder="Powtórz hasło"
                    value={this.state.password2}
                    onChange={UserActions.changePassword2}
                />
                <Button disabled={this.state.submitted } bsStyle="success" onClick={(e)=>{e.stopPropagation();UserActions.register(this.props.hash);return false;}}>Zarejestruj się</Button>
            </form>
        );
    }
}

export default RegisterForm;