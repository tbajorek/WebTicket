import React from 'react';
import Reflux from 'reflux';
import createHistory from 'history/createHashHistory';

import AbstractAjaxStore from './AbstractAjaxStore';
import DepartmentActions from '../actions/DepartmentActions';

const history = createHistory();

/**
 * Store for department
 */
class DepartmentStore extends AbstractAjaxStore {
    constructor() {
        super();
        this.state = this.getInitialState();
        this.listenables = DepartmentActions;
    }

    getInitialState() {
        return {
            departments: [],
            department: {
                id: null,
                name: ''
            },
            loaded: false
        };
    }

    onClear() {
        this.setState(this.getInitialState());
    }

    onChangeName(event) {
        event.preventDefault();
        this.setState({
            department: {
                ...this.state.department,
                name: event.target.value
            }
        });
    }

    onLoadDepartments(token) {
        this.getData('department', token, (response) => {
            this.setState({
                departments: response.data.departments
            });
        });
    }

    onLoadOne(departmentId, token) {
        this.getData('department/'+departmentId, token, (response) => {
            this.setState({
                department: response.data,
                loaded: true
            });
        });
    }

    onSave(token) {
        if(this.state.loaded) {
            this.saveExist(token);
        } else {
            this.addNew(token);
        }
    }

    addNew(token) {
        this.postData('department', token, {
            name: this.state.department.name
        }, (response) => {
            this.setState({
                submitted: false
            });
            setTimeout(()=> {
                history.push('/departments');
            }, 2000);
        }, (error) => {
            this.setState({
                submitted: false
            });
        });
    }

    saveExist(token) {
        this.putData('department/'+this.state.department.id, token, {
            name: this.state.department.name
        }, (response) => {
            this.setState({
                submitted: false
            });
            setTimeout(()=> {
                history.push('/department/'+this.state.department.id);
            }, 2000);
        }, (error) => {
            this.setState({
                submitted: false
            });
        });
    }

    onRemove(departmentId, token) {
        this.deleteData('department/'+departmentId, token, (response) => {
            setTimeout(()=> {
                DepartmentActions.loadDepartments(token);
            }, 2000);
        });
    }
}

export default DepartmentStore;