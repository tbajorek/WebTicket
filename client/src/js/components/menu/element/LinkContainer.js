import React from 'react';
import { LinkContainer as BootstrapLinkContainer } from 'react-router-bootstrap';

import { setVisible } from '../../../tools/CheckRole';

/**
 * Component of Link container which support visibility based on role
 */
const LinkContainer = (props) => {
    "use strict";
    const { user, allowedRole, ...restProps } = props;
    if(setVisible(props)) {
        return (<BootstrapLinkContainer {...restProps} />);
    } else {
        return null;
    }
};

export default LinkContainer;