import React from 'react';
import { Link } from 'react-router-dom';
import AbstractPage from './AbstractPage';
import { Jumbotron } from 'react-bootstrap';
import Error from './../components/message/Error';
import { GUEST } from './../tools/CheckRole';

import MessageActions from '../actions/MessageActions';

/**
 * Component of page with not found message
 */
class NotFoundPage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = GUEST;
    }

    componentDidMount() {
        MessageActions.addError("Nie znaleziono strony, której szukasz");
    }

    safeRender() {
        return (
            <div className="notfoundpage">
                <Jumbotron>
                    <h2>Błąd 404</h2>
                    <p>Ups... Chyba się zgubiłeś. Sprawdź jeszcze raz adres. Możesz też wrócić na <Link to={'/'}>stronę główną</Link>.</p>
                </Jumbotron>
            </div>
        );
    }
}

export default NotFoundPage;