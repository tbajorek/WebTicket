import React from 'react';
import Reflux from 'reflux';
import { Row, Col, Glyphicon, Button } from 'react-bootstrap';
import createHistory from 'history/createHashHistory';

import { checkRole, ADMIN } from '../../tools/CheckRole';
import DepartmentStore from '../../stores/DepartmentStore';
import DepartmentActions from '../../actions/DepartmentActions';
import DepartmentUserList from '../../components/department/DepartmentUserList';

const history = createHistory();

/**
 * Component of department view
 */
class DepartmentView extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = DepartmentStore;
    }
    componentWillMount() {
        super.componentWillMount();
        DepartmentActions.clear();
        DepartmentActions.loadOne(this.props.departmentId, this.props.token);
    }

    render() {
        if(this.state.loaded===true) {
            var editButton = null;
            if(checkRole(this.props.user, ADMIN)) {
                editButton = (
                    <Row>
                        <Button bsStyle="success" className="pull-right" onClick={(e) => history.push('/department/'+this.state.department.id+'/edit')}>
                            <Glyphicon glyph="pencil" />&nbsp;Edytuj
                        </Button>
                    </Row>
                );
            }
            return (
                <div className="department-view">
                    <h2>Informacje o departamencie</h2>
                    <div className="department-information">
                        <Row>
                            <Col xs={6} md={6}>
                                Nazwa:
                            </Col>
                            <Col xs={6} md={6}>
                                {this.state.department.name}
                            </Col>
                        </Row>
                        { editButton }
                    </div>
                    <Row>
                        <h4>Pracownicy</h4>
                        <DepartmentUserList departmentId={this.state.department.id} user={this.props.user} token={this.props.token} />
                    </Row>
                </div>
            );
        } else {
            return null;
        }
    }
}

export default DepartmentView;