import React from 'react';
import Reflux from 'reflux';
import { Table, Button, Glyphicon } from 'react-bootstrap';
import { MANAGER, ADMIN, checkRole } from '../../tools/CheckRole';
import RateControl from './RateControl';
import ChangeDepartment from './ChangeDepartment';
import ChangePosition from './ChangePosition';

import SettingsActions from '../../actions/SettingsActions';
import SettingsStore from '../../stores/SettingsStore';

/**
 * Component of provile view
 */
class ProfileView extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = SettingsStore;
    }

    componentWillMount() {
        super.componentWillMount();
        SettingsActions.loadSettings(this.props.userId, this.props.token);
    }

    render() {
        var additional = null;
        var editPosition = null;
        if((checkRole(this.props.currentUser, MANAGER) && this.props.currentUser.department.id===this.state.department.id) || checkRole(this.props.currentUser, ADMIN)) {
            additional = (<tr>
                <td>Ocena</td>
                <td>
                    <RateControl value={this.state.rate} allowHalf />
                </td>
            </tr>);
            editPosition = (
                <div>
                    <Button bsStyle="success" onClick={SettingsActions.showPositionModal}>
                        <Glyphicon glyph="edit" />&nbsp;Edytuj
                    </Button>
                </div>
            );
        }
        var editDepartment = null;
        if(checkRole(this.props.currentUser, ADMIN)) {
            editDepartment = (
                <div>
                    <Button bsStyle="success" onClick={SettingsActions.showDepartmentModal}>
                        <Glyphicon glyph="edit" />&nbsp;Edytuj
                    </Button>
                </div>
            );
        }
        return (
            <div>
                <Table striped bordered condensed hover>
                    <tbody>
                    <tr>
                        <td>Imię</td>
                        <td>{this.state.name}</td>
                    </tr>
                    <tr>
                        <td>Nazwisko</td>
                        <td>{this.state.surname}</td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>{this.state.email}</td>
                    </tr>
                    <tr>
                        <td>Departament</td>
                        <td>{this.state.department.name}&nbsp;{ editDepartment }</td>
                    </tr>
                    <tr>
                        <td>Stanowisko</td>
                        <td>{this.state.position}&nbsp;{ editPosition }</td>
                    </tr>
                    { additional }
                    </tbody>
                </Table>
                <ChangeDepartment user={this.props.currentUser} token={this.props.token} />
                <ChangePosition user={this.props.currentUser} token={this.props.token} />
            </div>
        );
    }

}

export default ProfileView;