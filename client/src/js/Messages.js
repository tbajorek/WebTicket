import React from 'react';
import Reflux from 'reflux';

import MessageStore from './stores/MessageStore';
import Error from './components/message/Error';
import Warning from './components/message/Warning';
import Info from './components/message/Info';
import Success from './components/message/Success';

/**
 * Messages container
 */
class Messages extends Reflux.Component {
    constructor(props)
    {
        super(props);
        this.store = MessageStore;
    }

    render() {
        var messages = [];
        for (var i in this.state.errors) {
            messages.push(<Error message={this.state.errors[i]} key={i+1} />);
        }
        for (var i in this.state.successes) {
            messages.push(<Success message={this.state.successes[i]} key={2*(i+1)} />);
        }
        for (var i in this.state.warnings) {
            messages.push(<Warning message={this.state.warnings[i]} key={3*(i+1)} />);
        }
        for (var i in this.state.infos) {
            messages.push(<Info message={this.state.infos[i]} key={4*(i+1)} />);
        }
        return (
            <div className="messages">
                {messages}
            </div>
        );
    }
}

export default Messages;