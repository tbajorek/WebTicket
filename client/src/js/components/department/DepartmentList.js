import React from 'react';
import Reflux from 'reflux';

import DepartmentStore from '../../stores/DepartmentStore';
import DepartmentActions from '../../actions/DepartmentActions';
import DepartmentListView from './DepartmentListView';

/**
 * Component of department list
 */
class DepartmentList extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = DepartmentStore;
    }
    componentWillMount() {
        super.componentWillMount();
        DepartmentActions.clear();
        DepartmentActions.loadDepartments(this.props.token);
    }

    render() {
        return (
            <DepartmentListView departments={this.state.departments} user={this.props.user} token={this.props.token} />
        );
    }
}

export default DepartmentList;