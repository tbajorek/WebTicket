import React from 'react';
import Rate from 'rc-rate';
import { Label } from 'react-bootstrap';

/**
 * Component of rate control
 */
const RateControl = (props) => {
    "use strict";
    const { style, ...other} = props;
    const allStyles = Object.assign({}, style, {
        marginLeft: "5px"
    });
    return (
        <div style={allStyles}>
            <Rate {...other} />&nbsp;
            <Label bsStyle="info">{props.value}</Label>
        </div>
    );
};

export default RateControl;