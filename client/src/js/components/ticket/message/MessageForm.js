import React from 'react';
import Reflux from 'reflux';
import EditableDiv from 'react-wysiwyg-editor';
import ReactQuill from 'react-quill';
import theme from 'react-quill/dist/quill.snow.css';

import TicketMessageStore from '../../../stores/TicketMessageStore';
import TicketMessageActions from '../../../actions/TicketMessageActions';
import { Button } from 'react-bootstrap';

/**
 * Component of ticket message form
 */
class MessageForm extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = TicketMessageStore;
        this.saveMessage = this.saveMessage.bind(this)
    }

    componentWillMount() {
        super.componentWillMount();
        TicketMessageActions.clearMessage();
        if(typeof this.props.ticketId !== 'undefined') {
            TicketMessageActions.changeTicket(this.props.ticketId);
        } else {
            TicketMessageActions.loadMessage(this.props.messageId, this.props.token);
        }
    }

    saveMessage() {
        if(typeof this.props.ticketId !== 'undefined') {
            TicketMessageActions.addMessage(this.props.token);
        } else {
            TicketMessageActions.saveMessage(this.props.token);
        }
    }

    render() {
        if(typeof this.props.ticketId !== 'undefined' || this.state.loaded) {
            return (
                <div className="form-group message-form">
                    <label>Twoja wiadomość:</label>
                    <ReactQuill value={this.state.message.content}
                                onChange={TicketMessageActions.changeContent}/>
                    <Button disabled={this.state.submitted} bsStyle="success" onClick={this.saveMessage}>Zapisz</Button>
                </div>
            );
        } else {
            return null;
        }
    }
}

export default MessageForm;