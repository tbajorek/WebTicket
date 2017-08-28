import React from 'react';
import Reflux from 'reflux';
import { Modal, Button } from 'react-bootstrap';

import TicketStore from '../../stores/TicketStore';
import TicketActions from '../../actions/TicketActions';
import RateControl from '../profile/RateControl';

/**
 * Component of ticket rate
 */
class TicketRate extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = TicketStore;
    }

    componentWillMount() {
        super.componentWillMount();
        window.rated = false;
    }

    render() {
        if(this.state.ticket.recipient != null) {
            return (
                <Modal show={this.props.shown} onHide={TicketActions.hideRate}>
                    <Modal.Header closeButton>
                        <Modal.Title>Oceń wykonanie zgłoszenia</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <p>Jak oceniasz wykonanie zgłoszenia
                            przez {this.state.ticket.recipient.name + ' ' + this.state.ticket.recipient.surname}?</p>
                        <RateControl value={this.state.proposedRate}
                                     onChange={(rate) => {TicketActions.setRate(rate);}}/>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button bsStyle="success" onClick={(e)=>{TicketActions.rate(this.props.token);}}>Wystaw
                            ocenę</Button>
                        <Button onClick={TicketActions.hideRate}>Zamknij</Button>
                    </Modal.Footer>
                </Modal>
            );
        } else {
            return null;
        }
    }
}

export default TicketRate;