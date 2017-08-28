import React from 'react';
import { InputGroup, Button, FormGroup, ControlLabel, FormControl } from 'react-bootstrap';

/**
 * Component of field group control in form
 */
const FieldGroup = ({ id, label, help, ...props }) => {
    return (
        <FormGroup controlId={id}>
            <ControlLabel>{label}</ControlLabel>
            <FormControl {...props} />
            {help && <HelpBlock>{help}</HelpBlock>}
        </FormGroup>
    );
};

export default FieldGroup;