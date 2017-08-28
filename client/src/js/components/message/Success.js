import React from 'react';
import {Alert} from 'react-bootstrap';

/**
 * Component of success message
 */
const Success = ({message}) => (
    <Alert bsStyle="success">
        {message}
    </Alert>
);

export default Success;