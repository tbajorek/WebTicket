import React from 'react';
import Reflux from 'reflux';

import Select from '../form/Select';
import DepartmentActions from '../../actions/DepartmentActions';
import DepartmentStore from '../../stores/DepartmentStore';

/**
 * Component of select department control
 */
class SelectDepartment extends Reflux.Component {
    constructor(props) {
        super(props);
        this.store = DepartmentStore;
    }
    componentWillMount() {
        super.componentWillMount();
        DepartmentActions.loadDepartments(this.props.token);
    }
    prepareList(departments) {
        var outputList = [
            {
                value: null,
                name: 'wybierz...'
            }
        ];
        for(var i in departments) {
            outputList.push({
                value: departments[i].id,
                name: departments[i].name
            });
        }
        return outputList;
    }
    render() {
        return (
            <Select disabled={this.props.disabled} onChange={this.props.onChange} disabled={typeof this.props.selected !== 'undefined'} options={this.prepareList(this.state.departments)} value={this.props.selected} />
        );
    }
}

export default SelectDepartment;