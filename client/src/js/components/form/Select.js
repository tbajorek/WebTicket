import React from 'react';
import { FormControl } from 'react-bootstrap';

/**
 * Component of select control in form
 */
const Select = ({ options, value, onChange }) => {
    var selectOptions = [];
    for(var i in options) {
        selectOptions.push(<option key={i} value={options[i].value}>{options[i].name}</option>);
    }
    return (
        <FormControl value={value} componentClass="select" placeholder="select" onChange={onChange}>
            {selectOptions}
        </FormControl>
    );
};

export default Select;