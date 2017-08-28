import React from 'react';
import Reflux from 'reflux';

import AbstractAjaxStore from './AbstractAjaxStore';
import SettingsActions from '../actions/SettingsActions';
import AuthActions from '../actions/AuthActions';
import MessageActions from '../actions/MessageActions';

/**
 * Store for settings
 */
class SettingsStore extends AbstractAjaxStore {
    constructor() {
        super();
        this.state = {
            name: '',
            surname: '',
            email: '',
            oldPassword: '',
            password: '',
            password2: '',
            department: {},
            position: '',
            rate: 0,
            submitted: false,
            proposedDepartment: null,
            proposedPosition: '',
            shownDepartmentModal: false,
            shownPositionModal: false
        };
        this.requestData = {
            userId: null,
            token: null
        };
        this.listenables = SettingsActions;
    }

    onLoadSettings(userId, token) {
        this.requestData= {
            userId: userId,
            token: token
        };
        this.getData('user/'+userId, token, (response) => {
            this.setState({
                name: response.data.name,
                surname: response.data.surname,
                email: response.data.email,
                department: response.data.department,
                position: response.data.position,
                rate: response.data.rate,
                proposedDepartment: response.data.department.id,
                proposedPosition: response.data.position
            });
        });
    }

    onPerformNewPassword(event) {
        event.preventDefault();
        this.setState({
            submitted: true
        });
        if (this.state.password === this.state.password2) {
            this.putData('user/' + this.requestData.userId + '/password', this.requestData.token, {
                oldPassword: this.state.oldPassword,
                newPassword: this.state.password
            }, (response)=>{
                this.setState({
                    submitted: false
                });
            }, (error) =>{
                this.setState({
                    submitted: false
                });
            });
        } else {
            MessageActions.clearMessages();
            MessageActions.addError('Podane hasła nie są identyczne');
            this.setState({
                submitted: false
            });
        }
        return false;
    }

    onChangeProfileData(event) {
        event.preventDefault();
        this.setState({
            submitted: true
        });
        this.putData('user/' + this.requestData.userId + '/profile', this.requestData.token, {
            name: this.state.name,
            surname: this.state.surname,
            email: this.state.email,
        }, (response)=>{
            this.setState({
                submitted: false
            });
            AuthActions.login(response.data.user, this.requestData.token);
        }, (error) =>{
            this.setState({
                submitted: false
            });
        });
    }

    onChangeEmail(event) {
        this.setState({email: event.target.value});
    }

    onChangeOldPassword(event) {
        this.setState({oldPassword: event.target.value});
    }

    onChangePassword(event) {
        this.setState({password: event.target.value});
    }

    onChangePassword2(event) {
        this.setState({password2: event.target.value});
    }

    onChangeName(event) {
        this.setState({name: event.target.value});
    }

    onChangeSurname(event) {
        this.setState({surname: event.target.value});
    }

    onChangeDepartment(event) {
        this.setState({department: {
            id: event.target.value,
            ...this.state.department
        }});
    }

    onChangePosition(event) {
        this.setState({position: event.target.value});
    }

    onShowDepartmentModal() {
        this.setState({shownDepartmentModal: true});
    }

    onHideDepartmentModal() {
        this.setState({shownDepartmentModal: false});
    }

    onShowPositionModal() {
        this.setState({shownPositionModal: true});
    }

    onHidePositionModal() {
        this.setState({shownPositionModal: false});
    }

    onChangeDepartmentProposal(event) {
        this.setState({proposedDepartment: parseInt(event.target.value)});
    }

    onChangePositionProposal(event) {
        this.setState({proposedPosition: event.target.value});
    }

    onSaveNewDepartment() {
        this.putData('user/' + this.requestData.userId + '/department', this.requestData.token, {
            id: parseInt(this.state.proposedDepartment)
        }, (response)=>{
            this.setState({
                submitted: false
            });
            SettingsActions.hideDepartmentModal();
            setTimeout(()=>{
                SettingsActions.loadSettings(this.requestData.userId, this.requestData.token);
            }, 2000);
        }, (error) =>{
            this.setState({
                submitted: false
            });
            SettingsActions.hideDepartmentModal();
        });
    }

    onSaveNewPosition() {
        this.putData('user/' + this.requestData.userId + '/position', this.requestData.token, {
            position: this.state.proposedPosition
        }, (response)=>{
            this.setState({
                submitted: false
            });
            SettingsActions.hidePositionModal();
            setTimeout(()=>{
                SettingsActions.loadSettings(this.requestData.userId, this.requestData.token);
            }, 2000);
        }, (error) =>{
            this.setState({
                submitted: false
            });
            SettingsActions.hidePositionModal();
        });
    }
}

export default SettingsStore;