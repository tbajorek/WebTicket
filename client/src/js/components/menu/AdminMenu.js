import React from 'react';
import { NavItem } from 'react-bootstrap';
import NavDropdown from '../../components/menu/element/NavDropdown';
import LinkContainer from '../../components/menu/element/LinkContainer';
import { ADMIN } from '../../tools/CheckRole';

/**
 * Component of admin menu
 */
const DepartmentMenu = ({user}) => (
    <NavDropdown title="Administracja" id="basic-nav-dropdown" user={user} allowedRole={ADMIN}>
        <LinkContainer to={'/departments'}>
            <NavItem>
                Lista departamentów
            </NavItem>
        </LinkContainer>
    </NavDropdown>
);

export default DepartmentMenu;