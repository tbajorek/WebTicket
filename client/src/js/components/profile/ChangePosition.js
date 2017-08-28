import React from 'react';
import Reflux from 'reflux';
import { Modal, Button } from 'react-bootstrap';

import { checkRole, MANAGER } from '../../tools/CheckRole';
import SettingsStore from '../../stores/SettingsStore';
import SettingsActions from '../../actions/SettingsActions';
import FieldGroup from '../form/FieldGroup';

/**
 * Component of change position
 */
class ChangePosition extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = SettingsStore;
    }
    render() {
        return (
            <Modal show={this.state.shownPositionModal && checkRole(this.props.user, MANAGER)} onHide={SettingsActions.hidePositionModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Zmiana stanowiska</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FieldGroup
                        id="userPosition"
                        type="text"
                        label="Nowe stanowisko"
                        placeholder="Nowe stanowisko"
                        value={this.state.proposedPosition}
                        autoFocus
                        onChange={SettingsActions.changePositionProposal}
                    />
                </Modal.Body>
                <Modal.Footer>
                    <Button bsStyle="success" onClick={SettingsActions.saveNewPosition}>Zmień</Button>
                    <Button disabled={this.state.submitted} onClick={SettingsActions.hidePositionModal}>Zamknij</Button>
                </Modal.Footer>
            </Modal>
        );
    }
}

export default ChangePosition;