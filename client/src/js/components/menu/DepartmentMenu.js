import React from 'react';
import { NavItem } from 'react-bootstrap';
import NavDropdown from '../../components/menu/element/NavDropdown';
import LinkContainer from '../../components/menu/element/LinkContainer';
import { MANAGER } from '../../tools/CheckRole';

/**
 * Component of department menu
 */
const DepartmentMenu = ({user, allowedRole}) => {
    if(typeof user.department === 'undefined' || typeof user.department.id === 'undefined') {
        return null;
    }
    return (
        <NavDropdown title="Departament" id="basic-nav-dropdown" user={user} allowedRole={allowedRole}>
            <LinkContainer to={'/department/'+user.department.id+'/tickets'}>
                <NavItem>
                    Zgłoszenia
                </NavItem>
            </LinkContainer>
            <LinkContainer to={'/department/'+user.department.id+'/users'}>
                <NavItem>
                    Pracownicy
                </NavItem>
            </LinkContainer>
            <LinkContainer to={'/department/'+user.department.id+'/invitations'} user={user} allowedRole={MANAGER}>
                <NavItem>
                    Zaproszenia
                </NavItem>
            </LinkContainer>
        </NavDropdown>
    );
};

export default DepartmentMenu;