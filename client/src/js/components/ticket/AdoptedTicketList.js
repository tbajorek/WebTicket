import React from 'react';
import Reflux from 'reflux';

import TicketListStore from '../../stores/TicketListStore';
import TicketListActions from '../../actions/TicketListActions';
import TicketList from '../../components/ticket/TicketList';

/**
 * Component of adopted tickets list
 */
class AdoptedTicketList extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = TicketListStore;
    }
    componentWillMount() {
        super.componentWillMount();
        TicketListActions.clearTickets();
        TicketListActions.loadAdoptedTickets(this.props.token);
    }

    render() {
        return (
            <TicketList tickets={this.state.tickets} user={this.props.user} token={this.props.token} action="adopted" />
        );
    }
}

export default AdoptedTicketList;