import React from 'react';
import { DropdownButton, MenuItem } from 'react-bootstrap';
import createHistory from 'history/createHashHistory';
import { checkRole, MANAGER, ADMIN } from '../../tools/CheckRole';

import DepartmentActions from '../../actions/DepartmentActions';
import DepartmentStore from '../../stores/DepartmentStore';

const history = createHistory();

/**
 * Component of department menu
 */
const DepartmentListMenu = (props) => {
    "use strict";
    const { department, token } = props;

    return (
        <DropdownButton title="Akcje" id="bg-nested-dropdown">
            <MenuItem eventKey="1" onClick={(e)=>{history.push('/department/'+department.id);}}>
                Podgląd
            </MenuItem>
            <MenuItem eventKey="2" onClick={(e)=>{history.push('/department/'+department.id+'/tickets');}}>
                Zgłoszenia
            </MenuItem>
            <MenuItem eventKey="3" onClick={(e)=>{history.push('/department/'+department.id+'/invitations');}}>
                Zaproszenia
            </MenuItem>
            <MenuItem eventKey="5" onClick={(e)=>{DepartmentActions.remove(department.id, token);}}>Usuń</MenuItem>
        </DropdownButton>
    );
};

export default DepartmentListMenu;