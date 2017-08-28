import React from 'react';
import AbstractPage from './AbstractPage';
import { WORKER } from '../tools/CheckRole';

import MessageForm from '../components/ticket/message/MessageForm';

/**
 * Component of page with edition of ticket message
 */
class EditMessagePage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = WORKER;
    }

    safeRender() {
        return (
            <div>
                <h3>Edytuj wiadomość</h3>
                <MessageForm messageId={this.props.match.params.messageId} token={this.state.token} />
            </div>
        );
    }
}

export default EditMessagePage;