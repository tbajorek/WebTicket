import React from 'react';
import Reflux from 'reflux';

/**
 * Actions for department
 */
const DepartmentActions = Reflux.createActions([
    'clear',
    'loadDepartments',
    'loadOne',
    'changeName',
    'save',
    'remove'
]);

export default DepartmentActions;