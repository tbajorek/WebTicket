import React from 'react';
import {Alert} from 'react-bootstrap';

/**
 * Component of error message
 */
const Error = ({message}) => (
    <Alert bsStyle="danger">
        {message}
    </Alert>
);

export default Error;