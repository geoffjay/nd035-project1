'use strict';

/**
 * Login
 */
function login() {
    var username = document.getElementById("username").value.trim();
    var host = document.location.host;
    var url = "http://" + host + "/index?username=" + username;
    //console.log(url);
    $(location).attr("href", url);
}

/**
 * Enter to login.
 */
document.onkeydown = function (event) {
    // FIXME: this prevents redirect somehow
    var e = event || window.event || arguments.callee.caller.arguments[0];
    //console.log(e);
    e.keyCode === 13 && login();
};
