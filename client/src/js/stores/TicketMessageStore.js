import React from 'react';
import Reflux from 'reflux';
import createHistory from 'history/createHashHistory';

import AbstractAjaxStore from './AbstractAjaxStore';
import TicketMessageActions from '../actions/TicketMessageActions';
import MessageListActions from '../actions/MessageListActions';

const history = createHistory();

/**
 * Store for ticket messages
 */
class TicketMessageStore extends AbstractAjaxStore {
    constructor() {
        super();
        this.state = {
            message: this.getClearMessage(),
            ticket: null,
            submitted: false,
            loaded: false
        };
        this.listenables = TicketMessageActions;
    }

    getClearMessage() {
        return {
            author: {
                name: '',
                    surname: ''
            },
            created: null,
                content: ''
        };
    }

    onClearMessage() {
        this.setState({
            message: this.getClearMessage()
        });
    }

    onChangeTicket(ticketId) {
        this.setState({
            ticket: ticketId
        });
    }

    onChangeContent(value) {
        this.setState({
            message: {
                ...this.state.message,
                content: value
            }
        });
    }

    onLoadMessage(messageId, token) {
        this.setState({
            loaded: false
        });
        this.getData('message/'+messageId, token, (response) => {
            this.setState({
                message: response.data,
                loaded: true
            });
        });
    }

    onAddMessage(token) {
        this.setState({
            submitted: true
        });
        this.postData('message', token, {
            ticket: this.state.ticket,
            content: this.state.message.content
        }, (response) => {
            this.setState({
                submitted: false,
                message: {
                    ...this.state.message,
                    content: ''
                }
            });
            MessageListActions.loadMessages(this.state.ticket, token);
        }, (error) => {
            this.setState({
                submitted: false
            });
        });
    }

    onSaveMessage(token) {
        this.setState({
            submitted: true
        });
        this.putData('message/'+this.state.message.id, token, {
            content: this.state.message.content
        }, (response) => {
            this.setState({
                submitted: false
            });
            setTimeout(()=> {
                history.goBack();
            }, 3000);
        }, (error) => {
            this.setState({
                submitted: false
            });
        });
    }

    onRemoveMessage(messageId, token) {
        this.deleteData('message/'+messageId, token, (response) => {
            MessageListActions.loadMessages(this.state.ticket, token);
        });
    }
}

export default TicketMessageStore;