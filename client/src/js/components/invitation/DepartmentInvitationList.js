import React from 'react';
import Reflux from 'reflux';

import InvitationActions from '../../actions/InvitationActions';
import InvitationStore from '../../stores/InvitationStore';
import InvitationList from './InvitationList';
import InvitationPreview from './InvitationPreview';

/**
 * Component of department invitations list
 */
class DepartmentInvitationList extends Reflux.Component {
    constructor(props) {
        super(props);
        this.state = {};
        this.store = InvitationStore;
    }
    componentWillMount() {
        super.componentWillMount();
        InvitationActions.clear();
        InvitationActions.loadList(this.props.departmentId, this.props.token);
    }

    render() {
        return (
            <div>
                <InvitationList shown={this.state.shown} invitations={this.state.invitations} invitation={this.state.invitation} user={this.props.user} token={this.props.token} />
                <InvitationPreview invitation={this.state.invitation} shown={this.state.shown} />
            </div>
        );
    }
}

export default DepartmentInvitationList;