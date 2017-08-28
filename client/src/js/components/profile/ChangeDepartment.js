import React from 'react';
import Reflux from 'reflux';
import { Modal, Button } from 'react-bootstrap';

import { checkRole, ADMIN } from '../../tools/CheckRole';
import SettingsStore from '../../stores/SettingsStore';
import SettingsActions from '../../actions/SettingsActions';
import SelectDepartment from '../department/SelectDepartment';

/**
 * Component of change department
 */
class ChangeDepartment extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = SettingsStore;
    }

    render() {
        return (
            <Modal show={this.state.shownDepartmentModal && checkRole(this.props.user, ADMIN)} onHide={SettingsActions.hideDepartmentModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Zmiana departamentu</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>Nowy departament:</p>
                    <SelectDepartment token={this.props.token} selected={this.state.proposedDepartment} onChange={SettingsActions.changeDepartmentProposal} />
                </Modal.Body>
                <Modal.Footer>
                    <Button bsStyle="success" onClick={SettingsActions.saveNewDepartment}>Zmień</Button>
                    <Button disabled={this.state.submitted} onClick={SettingsActions.hideDepartmentModal}>Zamknij</Button>
                </Modal.Footer>
            </Modal>
        );
    }
}

export default ChangeDepartment;