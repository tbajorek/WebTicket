import React from 'react';
import Reflux from 'reflux';

import Menu from './components/menu/Menu';

/**
 * Header container
 */
class Header extends Reflux.Component {
    render() {
        return (
            <div className="header">
                <Menu />
            </div>
        );
    }
}

export default Header;