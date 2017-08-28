import React from 'react';
import AbstractPage from './AbstractPage';
import '../../css/login.css';
import { GUEST } from '../tools/CheckRole';

import LoginForm from '../components/login/LoginForm';

/**
 * Component of page with login page
 */
class LoginPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = GUEST;
    }

    safeRender() {
        return (
            <div className="loginpage container">
                <LoginForm />
            </div>
        );
    }
}

export default LoginPage;