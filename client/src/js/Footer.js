import React from 'react';
import Reflux from 'reflux';

/**
 * Footer container
 */
class Footer extends Reflux.Component {
    render() {
        return (
            <div className="footer">&copy; by Tomasz Bajorek 2017</div>
        );
    }
}

export default Footer;