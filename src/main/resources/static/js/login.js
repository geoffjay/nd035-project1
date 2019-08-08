'use strict';

var username = null;

/**
 * Login
 */
function login() {
    // TODO: complete the login function
    username = document.querySelector('#username').value.trim();
    //$.post("loginUser", {username: username}, function(result){});
}

/**
 * Enter to login.
 */
document.onkeydown = function (event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    e.keyCode === 13 && login();
};
