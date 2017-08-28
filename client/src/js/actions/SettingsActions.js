import React from 'react';
import Reflux from 'reflux';

/**
 * Actions for settings
 */
const SettingsActions = Reflux.createActions([
    'loadSettings',
    'changeEmail',
    'changeName',
    'changeSurname',
    'changeOldPassword',
    'changePassword',
    'changePassword2',
    'changeDepartment',
    'changePosition',
    'performNewPassword',
    'changeProfileData',
    'showDepartmentModal',
    'hideDepartmentModal',
    'showPositionModal',
    'hidePositionModal',
    'changeDepartmentProposal',
    'changePositionProposal',
    'saveNewDepartment',
    'saveNewPosition'
]);

export default SettingsActions;