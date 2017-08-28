import React from 'react';
import AbstractPage from './AbstractPage';
import { ADMIN } from '../tools/CheckRole';

import DepartmentForm from '../components/department/DepartmentForm';

/**
 * Component of page with edition of department
 */
class EditDepartmentPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = ADMIN;
    }

    safeRender() {
        return (
            <DepartmentForm departmentId={this.props.match.params.departmentId} user={this.state.user} token={this.state.token} />
        );
    }
}

export default EditDepartmentPage;