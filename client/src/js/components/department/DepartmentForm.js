import React from 'react';
import Reflux from 'reflux';
import { FormGroup, FormControl, Button, ControlLabel } from 'react-bootstrap';

import DepartmentStore from '../../stores/DepartmentStore';
import DepartmentActions from '../../actions/DepartmentActions';

import FieldGroup from '../form/FieldGroup';
import { checkRole, ADMIN } from '../../tools/CheckRole';

/**
 * Component of department form
 */
class DepartmentForm extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = DepartmentStore;
    }
    componentWillMount() {
        super.componentWillMount();
        DepartmentActions.clear();
        if(typeof this.props.departmentId !== 'undefined') {
            DepartmentActions.loadOne(this.props.departmentId, this.props.token);
        }
    }

    render() {
        return (
            <form>
                <h3>{this.state.loaded?'Edytuj':'Dodaj'} departament</h3>
                <FieldGroup
                    id="departmentName"
                    type="text"
                    label="Nazwa departamentu"
                    placeholder="Nazwa departamentu"
                    value={this.state.department.name}
                    onChange={DepartmentActions.changeName}
                />
                <Button disabled={this.state.submitted} bsStyle="success" onClick={(e)=>{DepartmentActions.save(this.props.token);}}>Zapisz</Button>
            </form>
        );
    }
}

export default DepartmentForm;