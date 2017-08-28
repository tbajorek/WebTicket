import React from 'react';
import { Button, Glyphicon } from 'react-bootstrap';
import { withRouter } from 'react-router';
import AbstractPage from './../AbstractPage';
import { WORKER } from '../../tools/CheckRole';

import DepartmentUserList from '../../components/department/DepartmentUserList';

/**
 * Component of page with users assigned to department
 */
class UsersPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = WORKER;
    }

    safeRender() {
        return (
            <div className="department-users container">
                <h2>Pracownicy</h2>
                <DepartmentUserList departmentId={this.props.match.params.departmentId} user={this.state.user} token={this.state.token} />
            </div>
        );
    }
}

export default withRouter(UsersPage);