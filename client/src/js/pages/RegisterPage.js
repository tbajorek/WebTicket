import React from 'react';
import AbstractPage from './AbstractPage';
import '../../css/login.css';
import { GUEST } from '../tools/CheckRole';

import RegisterForm from '../components/user/RegisterForm';

/**
 * Component of page with registeer form
 */
class RegisterPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = GUEST;
    }

    safeRender() {
        return (
            <div className="loginpage container">
                <RegisterForm hash={this.props.match.params.hash} />
            </div>
        );
    }
}

export default RegisterPage;