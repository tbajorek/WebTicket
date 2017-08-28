import React from 'react';
import Reflux from 'reflux';

import TicketListStore from '../../stores/TicketListStore';
import TicketListActions from '../../actions/TicketListActions';
import TicketList from '../../components/ticket/TicketList';

/**
 * Component of department tickets list
 */
class DepartmentTicketList extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = TicketListStore;
    }
    componentWillMount() {
        super.componentWillMount();
        TicketListActions.clearTickets();
        TicketListActions.loadForDepartment(this.props.departmentId, this.props.token);
    }

    render() {
        return (
            <TicketList tickets={this.state.tickets} user={this.props.user} token={this.props.token} action={this.props.departmentId} />
        );
    }
}

export default DepartmentTicketList;