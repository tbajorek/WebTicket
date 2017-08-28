import React from 'react';
import Reflux from 'reflux';
import { Link } from 'react-router-dom';
import { Row, Col, Glyphicon } from 'react-bootstrap';

import TicketActions from '../../actions/TicketActions';
import MessageList from '../../components/ticket/message/MessageList';
import MessageForm from '../../components/ticket/message/MessageForm';
import TicketMenu from './TicketMenu';
import RateControl from '../profile/RateControl';

/**
 * Component of ticket preview
 */
const TicketPreview = (props) => {
    const {closed, user, ticket, token} = props;
    if(closed === true) {
        var closedDate = (new Date(ticket.closed)).toDateString();
        var form = null;
    } else {
        var closedDate = '---';
        if(closed === false && (user.id === ticket.author.id || (ticket.recipient != null && user.id === ticket.recipient.id))) {
            var form = (
                <div className="ticket-footer">
                    <MessageForm ticketId={ticket.id} token={token}/>
                </div>
            );
        } else {
            var form = null;
        }
    }
    if(ticket.recipient!=null) {
        var recipientString = ticket.recipient.name+' '+ticket.recipient.surname+' ('+ticket.recipient.position+')';
        recipient = (
            <Link to={'/user/'+ticket.recipient.id}>{recipientString}</Link>
        );
    } else {
        var recipient = '---';
    }
    var rateField = null;
    const inlineStyle = {
        display: 'inline'
    };
    if(ticket.rate != null) {
        rateField = (
            <RateControl style={inlineStyle} value={ticket.rate} />
        );
    }
    var author = (
        <Link to={'/user/'+ticket.author.id}>{ticket.author.name+' '+ticket.author.surname+' ('+ticket.author.position+')'}</Link>
    );
    return (
        <div className="ticket-preview">
            <div className="ticket-header">
                <Row>
                    <h3 style={inlineStyle}>{ticket.title}</h3>
                    {rateField}
                </Row>
                <Row>
                    <Col xs={8} md={8}>
                        <h4><Glyphicon title="Wykonawca" glyph="user" />&nbsp;{recipient}</h4>
                        <h5><Glyphicon title="Departament" glyph="home" />&nbsp;{ticket.department.name}</h5>
                    </Col>
                    <Col xs={4} md={4}>
                        <Row>
                            <Glyphicon title="Utworzono" glyph="pencil" />&nbsp;{(new Date(ticket.created)).toDateString()}
                        </Row>
                        <Row>
                            <Glyphicon title="Zmodyfikowano" glyph="briefcase" />&nbsp;{(new Date(ticket.lastModified)).toDateString()}
                        </Row>
                        <Row>
                            <Glyphicon title="Zamknięto" glyph="lock" />&nbsp;{closedDate}
                        </Row>
                    </Col>
                </Row>
                <Row>
                    <div className="pull-right">
                        <h5><Glyphicon title="Autor" glyph="briefcase" />&nbsp;{author}</h5>
                    </div>
                </Row>
                <TicketMenu {...props} />
            </div>
            <div className="ticket-body">
                <MessageList closed={closed} user={user} ticketId={ticket.id} token={token} />
            </div>
            {form}
        </div>
    );
};

export default TicketPreview;