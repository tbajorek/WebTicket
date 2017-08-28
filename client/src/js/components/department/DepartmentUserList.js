import React from 'react';
import Reflux from 'reflux';
import { Row, Glyphicon, Button } from 'react-bootstrap';
import createHistory from 'history/createHashHistory';

import { checkRole, MANAGER, ADMIN } from '../../tools/CheckRole';
import UserListStore from '../../stores/UserListStore';
import UserListActions from '../../actions/UserListActions';
import UserList from './UserList';

const history = createHistory();

/**
 * Component of department user list
 */
class DepartmentUserList extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = UserListStore;
    }
    componentWillMount() {
        super.componentWillMount();
        UserListActions.clearUsers();
        UserListActions.loadUsers(this.props.departmentId, this.props.token);
    }

    render() {
        var inviteButton = null;
        if(checkRole(this.props.user, ADMIN) || (checkRole(this.props.user, MANAGER) && this.props.user.department.id===this.props.departmentId)) {
            inviteButton = (
                <Row>
                    <Button bsStyle="success" className="pull-right" onClick={(e) => history.push('/newinvitation/'+this.props.departmentId)}>
                        <Glyphicon glyph="plus" />&nbsp;Zaproś pracownika
                    </Button>
                </Row>
            );
        }
        return (
            <div>
                { inviteButton }
                <UserList users={this.state.users} currentUser={this.props.user} token={this.props.token} />
            </div>
        );
    }
}

export default DepartmentUserList;