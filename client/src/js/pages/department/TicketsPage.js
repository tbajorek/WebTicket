import React from 'react';
import { Button, Glyphicon } from 'react-bootstrap';
import { withRouter } from 'react-router';
import AbstractPage from './../AbstractPage';
import { WORKER } from '../../tools/CheckRole';

import DepartmentTicketList from '../../components/ticket/DepartmentTicketList';

/**
 * Component of page with tickets for department
 */
class TicketsPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = WORKER;
    }

    safeRender() {
        return (
            <div className="department-tickets container">
                <h2>Zgłoszenia do departamentu</h2>
                <DepartmentTicketList departmentId={this.props.match.params.departmentId} user={this.state.user} token={this.state.token} />
            </div>
        );
    }
}

export default withRouter(TicketsPage);