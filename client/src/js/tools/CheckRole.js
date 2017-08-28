/**
 * Tools for checking if user has proper privileges
 */
const GUEST = 0;
const WORKER = 1;
const MANAGER = 2;
const ADMIN = 3;

function checkRole(user, role) {
    "use strict";
    return user.type.id >= role;
}

function checkExactRole(user, role) {
    "use strict";
    return user.type.id === role;
}

function setVisible(props) {
    "use strict";
    var visibleFlag = true;
    if(typeof props.user !== 'undefined' && typeof props.allowedRole !== 'undefined') {
        visibleFlag = checkRole(props.user, props.allowedRole);
    }
    return visibleFlag;
}

export {GUEST, WORKER, MANAGER, ADMIN, checkRole, checkExactRole, setVisible};