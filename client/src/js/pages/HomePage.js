import React from 'react';
import AbstractPage from './AbstractPage';
import { Jumbotron } from 'react-bootstrap';
import { GUEST } from '../tools/CheckRole';

/**
 * Component of page with home page
 */
class HomePage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = GUEST;
    }

    safeRender() {
        return (
            <div className="homepage">
                <Jumbotron>
                    <img className="logo" src={require("../../images/logo.png")} />
                    <h2>Witaj w systemie WebTicket</h2>
                    <p>Teraz możesz tworzyć i zarządzać zgłoszeniami wewnątrz przedsiębiorstwa. Skróć swój czas i zaoszczędź konkretne pieniądze.</p>
                </Jumbotron>
            </div>
        );
    }
}

export default HomePage;