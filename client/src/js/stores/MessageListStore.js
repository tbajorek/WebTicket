import React from 'react';
import Reflux from 'reflux';

import AbstractAjaxStore from './AbstractAjaxStore';
import MessageListActions from '../actions/MessageListActions';

/**
 * Store for message list
 */
class MessageListStore extends AbstractAjaxStore {
    constructor() {
        super();
        this.state = {
            messages: []
        };
        this.listenables = MessageListActions;
    }

    onLoadMessages(ticketId, token) {
        this.getData('ticket/'+ticketId+'/messages', token, (response) => {
            this.setState({
                messages: response.data.messages
            });
        });
    }
}

export default MessageListStore;