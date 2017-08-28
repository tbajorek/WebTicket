import React from 'react';
import Reflux from 'reflux';
import { FormGroup, FormControl, Button, ControlLabel } from 'react-bootstrap';

import InvitationStore from '../../stores/InvitationStore';
import InvitationActions from '../../actions/InvitationActions';

import FieldGroup from '../form/FieldGroup';
import SelectDepartment from '../department/SelectDepartment';
import { checkRole, ADMIN } from '../../tools/CheckRole';

/**
 * Component of invitation form
 */
class InvitationForm extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = InvitationStore;
    }
    componentWillMount() {
        super.componentWillMount();
        InvitationActions.clear();
        if(typeof this.props.departmentId !== 'undefined') {
            InvitationActions.setDepartment(this.props.departmentId);
        }
        InvitationActions.loadAccountTypes(this.props.token);
    }

    render() {
        var types = [];
        for(var i in this.state.accountTypes) {
            types.push(
                <option key={this.state.accountTypes[i]} value={this.state.accountTypes[i]}>{i}</option>
            );
        }
        return (
            <form>
                <h3>Nowe zaproszenie</h3>
                <FieldGroup
                    id="invitationEmail"
                    type="email"
                    label="Adres e-mail"
                    placeholder="Adres e-mail"
                    value={this.state.invitation.email}
                    onChange={InvitationActions.changeEmail}
                />
                <FieldGroup
                    id="invitationPosition"
                    type="text"
                    label="Stanowisko"
                    placeholder="Stanowisko"
                    value={this.state.invitation.position}
                    onChange={InvitationActions.changePosition}
                />
                <FormGroup controlId="invitationDepartment">
                    <ControlLabel>Docelowy departament</ControlLabel>
                    <SelectDepartment disabled={!checkRole(this.props.user, ADMIN)} selected={this.state.invitation.department.id} token={this.props.token} onChange={InvitationActions.changeDepartment} />
                </FormGroup>
                <FormGroup controlId="invitationAccountType">
                    <ControlLabel>Typ konta</ControlLabel>
                    <FormControl componentClass="select" placeholder="select" onChange={InvitationActions.changeAccountType}>
                        {types}
                    </FormControl>
                </FormGroup>
                <Button disabled={this.state.submitted || this.state.invitation.department.id===null} bsStyle="success" onClick={(e)=>{e.stopPropagation();InvitationActions.addNew(this.props.token);return false;}}>Dodaj zaproszenie</Button>
            </form>
        );
    }
}

export default InvitationForm;