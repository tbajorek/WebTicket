import React from 'react';
import Reflux from 'reflux';
import createHistory from 'history/createHashHistory';

import InvitationActions from '../actions/InvitationActions';
import AbstractAjaxStore from './AbstractAjaxStore';

const history = createHistory();

/**
 * Store for invitation
 */
class InvitationStore extends AbstractAjaxStore {
    constructor() {
        super();
        this.state = this.getDefaultState();
        this.listenables = InvitationActions;
    }

    getDefaultState() {
        return {
            accountTypes: {},
            invitations: [],
            invitation: {
                email: '',
                accountType: {
                    id: null,
                    name: ''
                },
                position: '',
                hash: '',
                department: {
                    id: null,
                    name: ''
                }
            },
            shown: false,
            submitted: false,
            concreteDepartment: false
        };
    }

    onSetDepartment(departmentId) {
        this.setState({
            invitation: {
                ...this.state.invitation,
                department: {
                    ...this.state.invitation.department,
                    id: parseInt(departmentId)
                }
            },
            concreteDepartment: true
        });
    }

    onChangeEmail(event) {
        event.preventDefault();
        this.setState({
            invitation: {
                ...this.state.invitation,
                email: event.target.value
            }
        });
    }

    onChangeDepartment(event) {
        event.preventDefault();
        this.setState({
            invitation: {
                ...this.state.invitation,
                department: {
                    id: parseInt(event.target.value),
                    name: event.target.options[event.target.selectedIndex].text
                }
            }
        });
    }

    onChangePosition(event) {
        event.preventDefault();
        this.setState({
            invitation: {
                ...this.state.invitation,
                position: event.target.value
            }
        });
    }

    onChangeAccountType(event) {
        event.preventDefault();
        this.setState({
            invitation: {
                ...this.state.invitation,
                accountType: {
                    id: parseInt(event.target.value),
                    name: event.target.options[event.target.selectedIndex].text
                }
            }
        });
    }

    onLoadAccountTypes(token) {
        this.getData('invitation/types', token, (response) => {
            var key = Object.keys(response.data.types)[0];
            this.setState({
                accountTypes: response.data.types,
                invitation: {
                    ...this.state.invitation,
                    accountType: {
                        name: key,
                        id: response.data.types[key]
                    }
                }
            });
        });
    }

    onClear() {
        this.setState(this.getDefaultState());
    }

    onLoadList(departmentId, token) {
        this.getData('department/'+departmentId+'/invitations', token, (response) => {
            if(typeof response.data !== 'undefined' && typeof response.data.invitations !== 'undefined') {
                var invitations = response.data.invitations;
            } else {
                var invitations = [];
            }
            this.setState({
                invitations: invitations
            });
        });
    }

    onAddNew(token) {
        this.setState({
            submitted: true
        });
        this.postData('invitation', token, {
            email: this.state.invitation.email,
            position: this.state.invitation.position,
            accountType: parseInt(this.state.invitation.accountType.id),
            department: parseInt(this.state.invitation.department.id)
        }, (response) => {
            setTimeout(() => {
                if(this.state.concreteDepartment) {
                    history.push('/department/'+this.state.invitation.department.id+'/invitations');
                } else {
                    history.goBack();
                }
            }, 2000);
            this.setState({
                submitted: true
            });
        }, (error) => {
            this.setState({
                submitted: true
            });
        });
    }

    onShow(index) {
        this.setState({
            invitation: this.state.invitations[index],
            shown: true
        });
    }

    onHide() {
        this.setState({
            invitation: this.getDefaultState().invitation,
            shown: false
        });
    }

    onRemove(invitationId, token, departmentId = null) {
        this.deleteData('invitation/'+invitationId, token, (response) => {
            setTimeout(()=> {
                if(departmentId === null) {
                    history.goBack();
                } else {
                    InvitationActions.loadList(departmentId, token);
                }
            }, 2000);
        });
    }
}

export default InvitationStore;