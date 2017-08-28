import React from 'react';
import { MenuItem as BootstrapMenuItem } from 'react-bootstrap';

import { setVisible } from '../../../tools/CheckRole';

/**
 * Component of MenuItem container which support visibility based on role
 */
const MenuItem = (props) => {
    "use strict";
    const { user, allowedRole, ...restProps } = props;
    if(setVisible(props)) {
        return (<BootstrapMenuItem {...restProps} />);
    } else {
        return null;
    }
};

export default MenuItem;