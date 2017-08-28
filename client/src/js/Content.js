import React from 'react';
import Reflux from 'reflux';

import {
    Route,
    Link,
    Switch
} from 'react-router-dom';

import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import ProfilePage from './pages/ProfilePage';
import SettingsPage from './pages/SettingsPage';
import EditSettingsPage from './pages/EditSettingsPage';
import MyTicketsPage from './pages/MyTicketsPage';
import AdoptedTicketsPage from './pages/AdoptedTicketsPage';
import TicketPage from './pages/TicketPage';
import NewTicketPage from './pages/NewTicketPage';
import EditMessagePage from './pages/EditMessagePage';

import TicketsPage from './pages/department/TicketsPage';
import UsersPage from './pages/department/UsersPage';
import InvitationsPage from './pages/department/InvitationsPage';
import NewInvitationPage from './pages/NewInvitationPage';
import RegisterPage from './pages/RegisterPage';
import DepartmentsPage from './pages/DepartmentsPage';
import DepartmentViewPage from './pages/DepartmentViewPage';
import NewDepartmentPage from './pages/NewDepartmentPage';
import EditDepartmentPage from './pages/EditDepartmentPage';

import NotFoundPage from './pages/NotFoundPage';

/**
 * Content container
 */
class Content extends Reflux.Component {
    render() {
        return (
            <div className="content">
                <Switch>
                    <Route exact path="/" component={HomePage}/>
                    <Route path="/login" component={LoginPage}/>
                    <Route exact path="/user/:userId" component={ProfilePage}/>
                    <Route exact path="/settings" component={SettingsPage}/>
                    <Route path="/settings/edit" component={EditSettingsPage}/>
                    <Route path="/tickets/my" component={MyTicketsPage}/>
                    <Route path="/tickets/adopted" component={AdoptedTicketsPage}/>
                    <Route path="/ticket/:ticketId" component={TicketPage}/>
                    <Route path="/message/:messageId/edit" component={EditMessagePage}/>
                    <Route path="/newticket" component={NewTicketPage}/>
                    <Route exact path="/department/:departmentId/tickets" component={TicketsPage}/>
                    <Route exact path="/department/:departmentId/users" component={UsersPage}/>
                    <Route exact path="/department/:departmentId/invitations" component={InvitationsPage}/>
                    <Route path="/newinvitation/:departmentId" component={NewInvitationPage}/>
                    <Route path="/register/:hash" component={RegisterPage}/>
                    <Route path="/departments" component={DepartmentsPage}/>
                    <Route exact path="/department/:departmentId" component={DepartmentViewPage}/>
                    <Route path="/newdepartment" component={NewDepartmentPage}/>
                    <Route path="/department/:departmentId/edit" component={EditDepartmentPage}/>
                    <Route component={NotFoundPage} />
                </Switch>
            </div>
        );
    }
}

export default Content;