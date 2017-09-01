import React from 'react';
import Reflux from 'reflux';
import { Navbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

import GuestMenu from './user/GuestMenu';
import LoggedMenu from './user/LoggedMenu';

/**
 * Component of user menu
 */
const UserMenu = (props) => {
    "use strict";
    const {user, isLogged} = props;
    if (isLogged) {
        return (<LoggedMenu user={user}/>);
    } else {
        return (<GuestMenu />);
    }
};

export default UserMenu;