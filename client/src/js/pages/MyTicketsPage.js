import React from 'react';
import { Button, Glyphicon } from 'react-bootstrap';
import { withRouter } from 'react-router';
import AbstractPage from './AbstractPage';
import { WORKER } from '../tools/CheckRole';

import MyTicketList from '../components/ticket/MyTicketList';
import '../../css/mytickets.css';

/**
 * Component of page with own tickets list
 */
class MyTicketsPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = WORKER;
    }

    safeRender() {
        return (
            <div className="mytickets container">
                <h2>Twoje zgłoszenia</h2>
                <Button bsStyle="success" className="pull-right" onClick={(e) => this.props.history.push('/newticket')}>
                    <Glyphicon glyph="plus" />&nbsp;Dodaj nowe zgłoszenie
                </Button>
                <MyTicketList user={this.state.user} token={this.state.token} />
            </div>
        );
    }
}

export default withRouter(MyTicketsPage);