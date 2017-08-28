import React from 'react';
import Reflux from 'reflux';
import { Navbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

import AuthActions from '../../../actions/AuthActions';

/**
 * Component of logged menu
 */
const LoggedMenu = ({user}) => (
    <Nav pullRight>
        <NavDropdown title="Użytkownik" id="basic-nav-dropdown">
            <MenuItem>Witaj {user.name} {user.surname}</MenuItem>
            <MenuItem divider />
            <LinkContainer to="/settings">
                <NavItem>
                    Ustawienia konta
                </NavItem>
            </LinkContainer>
            <MenuItem onClick={AuthActions.logout}>Wyloguj</MenuItem>
        </NavDropdown>

    </Nav>
);


export default LoggedMenu;