import React from 'react';
import Reflux from 'reflux';
import { Navbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

import GuestMenu from './user/GuestMenu';
import LoggedMenu from './user/LoggedMenu';

/**
 * Component of user menu
 */
class UserMenu extends Reflux.Component {
    render() {
        if (this.props.isLogged) {
            return (<LoggedMenu user={this.props.user}/>);
        } else {
            return (<GuestMenu />);
        }
    }
}

export default UserMenu;