import React from 'react';
import {Alert} from 'react-bootstrap';

/**
 * Component of warning message
 */
const Warning = ({message}) => (
    <Alert bsStyle="warning">
        {message}
    </Alert>
);

export default Warning;