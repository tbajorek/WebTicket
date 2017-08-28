import React from 'react';
import AbstractPage from './AbstractPage';
import { ADMIN } from '../tools/CheckRole';

import DepartmentForm from '../components/department/DepartmentForm';

/**
 * Component of page with new department form
 */
class NewDepartmentPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = ADMIN;
    }

    safeRender() {
        return (
            <DepartmentForm user={this.state.user} token={this.state.token} />
        );
    }
}

export default NewDepartmentPage;