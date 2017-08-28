import React from 'react';
import Reflux from 'reflux';

import TicketStore from '../../stores/TicketStore';
import TicketActions from '../../actions/TicketActions';
import TicketPreview from '../../components/ticket/TicketPreview';
import TicketRate from './TicketRate';

/**
 * Component of ticket view
 */
class TicketView extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = TicketStore;
    }
    componentWillMount() {
        super.componentWillMount();
        window.removed = false;
        TicketActions.clearTicket();
        TicketActions.loadTicket(this.props.ticketId, this.props.token);
    }

    render() {
        if(this.state.loaded===true) {
            return (
                <div>
                    <TicketPreview closed={this.state.closed} user={this.props.user} ticket={this.state.ticket} token={this.props.token} />
                    <TicketRate token={this.props.token} shown={this.state.shownRate} />
                </div>
            );
        } else {
            return null;
        }
    }
}

export default TicketView;