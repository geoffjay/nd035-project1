'use strict';

/**
 * Login
 */
function login() {
    var username = document.getElementById("username").value.trim();
    var host = document.location.host;
    var url = "/index?username=" + username;
    document.location.href = url;
}

/**
 * Enter to login.
 */
$(document).ready(function() {
    $(document).keypress(function(e) {
        if (e.which === 13) {
            e.preventDefault();
            login();
        }
    })
})
