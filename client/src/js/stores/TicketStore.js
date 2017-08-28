import React from 'react';
import Reflux from 'reflux';
import createHistory from 'history/createHashHistory';

import AbstractAjaxStore from './AbstractAjaxStore';
import TicketActions from '../actions/TicketActions';
import TicketListActions from '../actions/TicketListActions';
import MessageActions from '../actions/MessageActions';

const history = createHistory();

/**
 * Store for single tickets
 */
class TicketStore extends AbstractAjaxStore {
    constructor() {
        super();
        this.state = this.getInitialState();
        this.listenables = TicketActions;
    }

    getInitialState() {
        return {
            ticket: {
                title: '',
                department: {
                    id: null,
                    name: null
                }
            },
            closed: false,
            loaded: false,
            submitted: false,
            shownRate: false,
            ratedTicket: null,
            proposedRate: null
        };
    }

    onClearTicket() {
        this.setState(this.getInitialState());
    }

    onChangeTitle(event) {
        this.setState({
            ticket: {
                ...this.state.ticket,
                title: event.target.value
            }
        });
    }

    onChangeDepartment(event) {
        this.setState({
            ticket: {
                ...this.state.ticket,
                department: {
                    id: parseInt(event.target.value),
                    name: event.target.options[event.target.selectedIndex].text
                }
            }
        });
    }

    onLoadTicket(ticketId, token) {
        this.getData('ticket/'+ticketId, token, (response) => {
            if(typeof response.data.id !== 'undefined') {
                this.setState({
                    ticket: response.data,
                    loaded: true,
                    closed: (response.data.closed | 0) > 0
                });
            } else {
                MessageActions.addError('Serwer nie zwrócił informacji o tym zgłoszeniu');
            }
        });
    }

    onAddTicket(token) {
        if(!window.added) {
            window.added = true;
            this.postData('ticket', token, {
                title: this.state.ticket.title,
                department: this.state.ticket.department.id
            }, (response) => {
                this.setState({
                    submitted: false,
                    ticket: response.data
                });
                setTimeout(()=> {
                    history.push('/ticket/' + this.state.ticket.id);
                }, 3000);
            }, (error) => {
                this.setState({
                    submitted: false
                });
            });
        }
    }

    onCloseTicket(ticketId, token) {
        var context = this;
        this.putData('ticket/'+ticketId+'/close', token, {}, (response) => {
            setTimeout(()=>{context.onLoadTicket(ticketId, token);}, 3000);
        });
    }

    onTakeTicket(ticketId, token) {
        var context = this;
        this.putData('ticket/'+ticketId+'/take', token, {}, (response) => {
            setTimeout(()=>{context.onLoadTicket(ticketId, token);}, 3000);
        });
    }

    onRemoveTicket(ticketId, token, action) {
        if(!window.removed) {
            window.removed = true;
            this.deleteData('ticket/' + ticketId, token, (response) => {
                setTimeout(()=> {
                    switch (action) {
                        case 'back':
                            history.goBack();
                            break;
                        case 'my':
                            TicketListActions.loadMyTickets(token);
                            break;
                        case 'adopted':
                            TicketListActions.loadAdoptedTickets(token);
                        default:
                            TicketListActions.loadForDepartment(action, token);
                    }
                }, 3000);
            });
        }
    }

    onShowRate(ticketId) {
        this.setState({
            ratedTicket: ticketId,
            shownRate: true
        });
    }

    onSetRate(value) {
        this.setState({
            proposedRate: value
        });
    }

    onHideRate() {
        this.setState({
            ratedTicket: null,
            shownRate: false
        });
    }

    onRate(token) {
        if(!window.rated) {
            window.rated = true;
            this.putData('ticket/' + this.state.ticket.id + '/rate', token, {
                rate: this.state.proposedRate
            }, (response) => {
                TicketActions.hideRate();
                setTimeout(()=> {
                    TicketActions.loadTicket(this.state.ticket.id, token);
                }, 3000);
            });
        }
    }
}

export default TicketStore;