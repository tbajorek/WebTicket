import React from 'react';
import { NavItem, MenuItem } from 'react-bootstrap';
import NavDropdown from '../../components/menu/element/NavDropdown';
import LinkContainer from '../../components/menu/element/LinkContainer';

/**
 * Component of ticket menu
 */
const TicketMenu = ({user, allowedRole}) => (
    <NavDropdown title="Zgłoszenia" id="basic-nav-dropdown" user={user} allowedRole={allowedRole}>
        <LinkContainer to="/tickets/my">
            <NavItem>
                Własne
            </NavItem>
        </LinkContainer>
        <LinkContainer to="/tickets/adopted">
            <NavItem>
                Obsługiwane
            </NavItem>
        </LinkContainer>
        <MenuItem divider />
        <LinkContainer to="/newticket">
            <NavItem>
                Dodaj nowe
            </NavItem>
        </LinkContainer>
    </NavDropdown>
);

export default TicketMenu;