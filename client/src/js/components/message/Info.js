import React from 'react';
import {Alert} from 'react-bootstrap';

/**
 * Component of info message
 */
const Info = ({message}) => (
    <Alert bsStyle="info">
        {message}
    </Alert>
);

export default Info;