import React from 'react';
import { Glyphicon, Button } from 'react-bootstrap';
import createHistory from 'history/createHashHistory';

import TicketMessageActions from '../../../actions/TicketMessageActions';

const history = createHistory();

/**
 * Component of ticket message view
 */
const MessageView = (props) => {
    const {closed, user, message, token} = props;
    var position = 'left';
    var color = 'FA6F57';
    var pullName = '';
    var pullTime = ' pull-right';
    var menu = null;
    if(closed !== true) {
        menu = (
            <div className="message-menu clearfix pull-right">
                <Button bsStyle="warning" onClick={(e)=>{history.push('/message/'+message.id+"/edit");}}>
                    <Glyphicon title="Edytuj" glyph="edit"/>&nbsp;Edytuj
                </Button>
                <Button bsStyle="danger" onClick={(e) => {TicketMessageActions.removeMessage(message.id, token)}}>
                    <Glyphicon title="Usuń" glyph="trash"/>&nbsp;Usuń
                </Button>
            </div>
        );
    }
    if(user.id!==message.author.id) {
        position = 'right';
        color = '55C1E7';
        pullName = ' pull-right';
        pullTime = '';
        menu = false;
    }
    var letters = message.author.name.charAt(0)+message.author.surname.charAt(0);
    return (
        <li className={position+' clearfix'}>
            <span className={'message-img pull-'+position}>
                <img src={'http://placehold.it/50/'+color+'/fff&text='+letters} alt={message.author.name+' '+message.author.surname} className="img-circle" />
            </span>
            <div className="message-body clearfix">
                <div className="header">
                    <strong className={'primary-font'+pullName}>{message.author.name+' '+message.author.surname}</strong>
                    <small className={'text-muted'+pullTime}>
                        <Glyphicon title="Wysłano" glyph="time" />&nbsp;{(new Date(message.created)).toDateString()}
                    </small>
                </div>
                <p dangerouslySetInnerHTML={{__html: message.content}}></p>
            </div>
            { menu }
        </li>
    );
};

export default MessageView;