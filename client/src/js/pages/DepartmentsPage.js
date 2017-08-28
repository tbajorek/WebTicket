import React from 'react';
import { withRouter } from 'react-router';
import AbstractPage from './AbstractPage';
import { Button, Glyphicon } from 'react-bootstrap';
import { ADMIN } from '../tools/CheckRole';
import DepartmentList from '../components/department/DepartmentList';
import '../../css/department.css';

/**
 * Component of page with list of departments
 */
class DepartmentsPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = ADMIN;
    }

    safeRender() {
        return (
            <div className="departments container">
                <h2>Lista departamentów</h2>
                <Button bsStyle="success" className="pull-right" onClick={(e) => this.props.history.push('/newdepartment')}>
                    <Glyphicon glyph="plus" />&nbsp;Dodaj departament
                </Button>
                <DepartmentList user={this.state.user} token={this.state.token} />
            </div>
        );
    }
}

export default withRouter(DepartmentsPage);