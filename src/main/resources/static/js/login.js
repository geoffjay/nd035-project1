'use strict';

/**
 * Login
 */
function login() {
    var username = document.getElementById("username").value.trim();
    var host = document.location.host;
    var url = "http://" + host + "/index?username=" + username;
    $(location).attr("href", url);
}

/**
 * Enter to login.
 */
document.onkeydown = function (event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    e.keyCode === 13 && login(username);
};
