import React from 'react';
import { Button, Glyphicon } from 'react-bootstrap';
import { withRouter } from 'react-router';
import AbstractPage from './../AbstractPage';

import { checkRole, MANAGER, ADMIN } from '../../tools/CheckRole';
import DepartmentInvitationList from '../../components/invitation/DepartmentInvitationList';

/**
 * Component of page with invitations to department
 */
class InvitationsPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = MANAGER;
    }

    safeRender() {
        var button = null;
        if(checkRole(this.state.user, ADMIN) || (checkRole(this.state.user, MANAGER) && this.state.user.department.id === this.props.match.params.departmentId)) {
            button = (
                <Button bsStyle="success" className="pull-right" onClick={(e) => this.props.history.push('/newinvitation/'+this.props.match.params.departmentId)}>
                    <Glyphicon glyph="plus" />&nbsp;Zaproś nowego użytkownika
                </Button>
            );
        }
        return (
            <div className="mytickets container">
                <h2>Utworzone zaproszenia</h2>
                { button }
                <DepartmentInvitationList user={this.state.user} token={this.state.token} departmentId={this.props.match.params.departmentId} />
            </div>
        );
    }
}

export default withRouter(InvitationsPage);