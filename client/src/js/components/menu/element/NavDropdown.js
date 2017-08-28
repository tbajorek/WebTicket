import React from 'react';
import { NavDropdown as BootstrapNavDropdown } from 'react-bootstrap';

import { setVisible } from '../../../tools/CheckRole';

/**
 * Component of NavDropdown container which support visibility based on role
 */
const NavDropdown = (props) => {
    "use strict";
    const { user, allowedRole, ...restProps } = props;
    if(setVisible(props)) {
        return (<BootstrapNavDropdown {...restProps} />);
    } else {
        return null;
    }
};

export default NavDropdown;