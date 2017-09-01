import React from 'react';
import Reflux from 'reflux';
import { Navbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

/**
 * Component of guest menu
 */
const GuestMenu = () => (
    <Nav pullRight>
        <LinkContainer to="/login">
            <NavItem>
                Zaloguj
            </NavItem>
        </LinkContainer>
    </Nav>
);


export default GuestMenu;