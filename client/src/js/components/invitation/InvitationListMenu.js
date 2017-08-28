import React from 'react';
import { DropdownButton, MenuItem } from 'react-bootstrap';
import { checkRole, MANAGER, ADMIN } from '../../tools/CheckRole';
import InvitationActions from '../../actions/InvitationActions';
import InvitationStore from '../../stores/InvitationStore';

/**
 * Component of invitation list menu
 */
const InvitationListMenu = (props) => {
    "use strict";
    const { invitation, user, token, index } = props;

    if(checkRole(user, ADMIN) || (checkRole(user, MANAGER) && user.department.id === invitation.department.id)) {
        return (
            <DropdownButton title="Akcje" id="bg-nested-dropdown">
                <MenuItem eventKey="1" onClick={(e)=>{InvitationActions.show(index);}}>
                    Podgląd
                </MenuItem>
                <MenuItem eventKey="5" onClick={(e)=>{InvitationActions.remove(invitation.id, token, invitation.department.id);}}>Usuń</MenuItem>
            </DropdownButton>
        );
    } else {
        return null;
    }
};

export default InvitationListMenu;