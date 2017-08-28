import React, { Component }  from 'react';
import Favicon from 'react-favicon';

import { HashRouter as Router } from 'react-router-dom';

import Header from './Header';
import Content from './Content';
import Messages from './Messages';
import Footer from './Footer';

/**
 * Application container
 */
class Application extends Component {
    render() {
        return (
            <Router>
                <div>
                    <Favicon url={require("../images/favicon.ico")} />
                    <Header />
                    <div className="container">
                        <Messages />
                        <Content />
                        <Footer />
                    </div>
                </div>
            </Router>
        );
    }
}

export default Application;

