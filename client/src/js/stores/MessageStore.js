import React from 'react';
import Reflux from 'reflux';

import MessageActions from '../actions/MessageActions';

/**
 * Store for single message
 */
class MessageStore extends Reflux.Store {
    constructor() {
        super();
        this.state = {
            errors: [],
            warnings: [],
            infos: [],
            successes: [],
            isValid: true,
            persist: false,
        };
        this.listenables = MessageActions;
    }

    onAddError(error, persist = false) {
        var errors = this.state.errors;
        errors.push(error);
        this.setState({
            "errors": errors,
            "isValid":false,
            "persist": persist
        });
    }

    onAddWarning(warning, persist = false) {
        var warnings = this.state.warnings;
        warnings.push(warning);
        this.setState({
            "warnings": warnings,
            "isValid":true,
            "persist": persist
        });
    }

    onAddInfo(info, persist = false) {
        var infos = this.state.infos;
        infos.push(info);
        this.setState({
            "infos": infos,
            "isValid":true,
            "persist": persist
        });
    }

    onAddSuccess(success, persist = false) {
        var successes = this.state.successes;
        successes.push(success);
        this.setState({
            "successes": successes,
            "isValid":true,
            "persist": persist
        });
    }

    onClearMessages() {
        if (this.state.persist) {
            this.setState({persist: false});
        } else {
            this.setState({
                "errors": [],
                "warnings": [],
                "infos": [],
                "successes": [],
                "isValid": true
            });
        }
    }
}

MessageStore.id = 'message';

export default MessageStore;