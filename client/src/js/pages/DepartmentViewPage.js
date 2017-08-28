import React from 'react';
import { withRouter } from 'react-router';
import AbstractPage from './AbstractPage';
import { WORKER } from '../tools/CheckRole';
import '../../css/department.css';

import DepartmentView from '../components/department/DepartmentView';

/**
 * Component of page with view of one department
 */
class DepartmentViewPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = WORKER;
    }

    safeRender() {
        return (
            <div className="department-view">
                <DepartmentView departmentId={this.props.match.params.departmentId} user={this.state.user} token={this.state.token} />
            </div>
        );
    }
}

export default withRouter(DepartmentViewPage);