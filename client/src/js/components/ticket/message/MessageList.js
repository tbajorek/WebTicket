import React from 'react';
import Reflux from 'reflux';

import MessageListStore from '../../../stores/MessageListStore';
import MessageListActions from '../../../actions/MessageListActions';
import MessageView from './MessageView';

/**
 * Component of ticket message list
 */
class MessageList extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = MessageListStore;
    }
    componentWillMount() {
        super.componentWillMount();
        MessageListActions.loadMessages(this.props.ticketId, this.props.token);
    }

    render() {
        var messages = [];
        for(var i in this.state.messages) {
            messages.push(<MessageView closed={this.props.closed} key={i} user={this.props.user} message={this.state.messages[i]} token={this.props.token} />);
        }
        return (
            <ul className="message-list">
                {messages}
            </ul>
        );
    }
}

export default MessageList;