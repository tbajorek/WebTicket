import React from 'react';
import AbstractPage from './AbstractPage';
import '../../css/profile.css';
import { WORKER } from '../tools/CheckRole';

import ProfileView from '../components/profile/ProfileView';

/**
 * Component of page with user profile
 */
class ProfilePage extends AbstractPage {
    constructor(props) {
        super(props);
        this.allowedRole = WORKER;
    }

    safeRender() {
        return (
            <div className="homepage">
                <h2>Profil użytkownika</h2>
                <ProfileView userId={this.props.match.params.userId} currentUser={this.state.user} token={this.state.token} />
            </div>
        );
    }
}

export default ProfilePage;