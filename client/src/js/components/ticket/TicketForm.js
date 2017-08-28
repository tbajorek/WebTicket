import React from 'react';
import Reflux from 'reflux';
import { FormGroup, Button, ControlLabel } from 'react-bootstrap';
import FieldGroup from '../form/FieldGroup';
import SelectDepartment from '../department/SelectDepartment';

import TicketStore from '../../stores/TicketStore';
import TicketActions from '../../actions/TicketActions';

/**
 * Component of single ticket form
 */
class TicketForm extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = TicketStore;
    }

    componentWillMount() {
        super.componentWillMount();
        TicketActions.clearTicket();
        window.added = false;
        if(typeof this.props.ticketId !== 'undefined') {
            TicketActions.loadTicket(this.props.ticketId, this.props.token);
        }
    }

    render() {
        const { title, department } = this.state.ticket;
        return (
            <form>
                <h3>Twoje zgłoszenie</h3>
                    <FieldGroup
                        id="ticketTitle"
                        type="text"
                        label="Tytuł"
                        placeholder="Tytuł"
                        value={title}
                        onChange={TicketActions.changeTitle}
                    />
                    <FormGroup controlId="formControlsSelect">
                        <ControlLabel>Docelowy departament</ControlLabel>
                        <SelectDepartment selected={this.props.departmentId} token={this.props.token} onChange={(e)=>{e.stopPropagation();TicketActions.changeDepartment(e);return false;}} />
                    </FormGroup>
                <Button disabled={this.state.submitted || department.id===null} bsStyle="success" onClick={(e)=>{e.stopPropagation();TicketActions.addTicket(this.props.token);return false;}}>Zapisz</Button>
            </form>
        );
    }
}

export default TicketForm;