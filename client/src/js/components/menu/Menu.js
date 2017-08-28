import React from 'react';
import Reflux from 'reflux';
import { NavLink } from 'react-router-dom';
import { Navbar, Nav, NavItem, MenuItem } from 'react-bootstrap';
import LinkContainer from '../../components/menu/element/LinkContainer';

import { WORKER } from '../../tools/CheckRole';

import AuthStore from '../../stores/AuthStore';
import TicketMenu from './TicketMenu';
import DepartmentMenu from './DepartmentMenu';
import AdminMenu from './AdminMenu';
import UserMenu from './UserMenu';

/**
 * Component of general menu
 */
class Menu extends Reflux.Component {
    constructor(props)
    {
        super(props);
        this.store = AuthStore;
    }

    render() {
        return (
            <Navbar inverse collapseOnSelect>
                <Navbar.Header>
                    <Navbar.Brand>
                        <NavLink to="/">
                            WebTicket
                        </NavLink>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>
                <Navbar.Collapse>
                    <Nav>
                        <TicketMenu user={this.state.user} allowedRole={WORKER} />
                        <DepartmentMenu user={this.state.user} allowedRole={WORKER} />
                        <AdminMenu user={this.state.user} />
                    </Nav>
                    <UserMenu user={this.state.user} isLogged={this.state.isLogged} />
                </Navbar.Collapse>
            </Navbar>
        );
    }
}

export default Menu;