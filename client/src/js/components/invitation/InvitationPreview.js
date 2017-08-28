import React from 'react';
import { Modal, Button } from 'react-bootstrap';

import InvitationStore from '../../stores/InvitationStore';
import InvitationActions from '../../actions/InvitationActions';
import InvitationDetails from './InvitationDetails';

/**
 * Component of invitation preview
 */
const InvitationPreview = (props) => {
    "use strict";
    const {shown, invitation } = props;
    if(shown) {
        return (
            <Modal show={shown} onHide={InvitationActions.hide}>
                <Modal.Header closeButton>
                    <Modal.Title>Podgląd zaproszenia</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <InvitationDetails invitation={invitation} />
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={InvitationActions.hide}>Zamknij</Button>
                </Modal.Footer>
            </Modal>
        );
    } else {
        return null;
    }
};

export default InvitationPreview;