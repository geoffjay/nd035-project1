'use strict';

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};

/**
 * WebSocket Client
 *
 * 1、WebSocket client receive messages with callback. example：webSocket.onmessage
 * 2、WebSocket client send message to server. example：webSocket.send();
 */
function getWebSocket() {
    var username = getUrlParameter("username");
    var host = document.location.host;

    /**
     * WebSocket client PS：URL shows WebSocket protocal, port number, and then end point.
     */
    var ws = new WebSocket("ws://" + host + "/chat/" + username);

    /**
     * websocket open connection.
     */
    ws.onopen = function (event) {
        console.log('WebSocket open connection');
    };

    /**
     * Server send 1) broadcast message, 2) online users.
     */
    ws.onmessage = function (event) {
        console.log('WebSocket Receives：%c' + event.data, 'color:green');
        //Receive Message from Server
        var message = JSON.parse(event.data) || {};
        var $messageContainer = $('.message-container');
        if (message.type === 'CHAT') {
            $messageContainer.append(
                '<div class="mdui-card" style="margin: 10px 0;">' +
                '<div class="mdui-card-primary">' +
                '<div class="mdui-card-content message-content">' + message.sender + "：" + message.content + '</div>' +
                '</div></div>');
        }
        $('.chat-num').text(message.onlineCount);
        var $cards = $messageContainer.children('.mdui-card:visible').toArray();
        if ($cards.length > 5) {
            $cards.forEach(function (item, index) {
                index < $cards.length - 5 && $(item).slideUp('fast');
            });
        }
    };

    /**
     * Close connection
     */
    ws.onclose = function (event) {
        console.log('WebSocket close connection.');
    };

    /**
     * Exception
     */
    ws.onerror = function (event) {
        console.log('WebSocket exception.');
    };

    return ws;
}

var webSocket = getWebSocket();

/**
 * Send messages to server use webSocket.
 */
function sendMsgToServer() {
    var $message = $('#msg');
    if ($message.val()) {
        webSocket.send(JSON.stringify({
            username: $('#username').text(),
            content: $message.val()
        }));
        $message.val(null);
    }
}

/**
 * Clear screen
 */
function clearMsg() {
    $(".message-container").empty();
}

/**
 * Enter to send message.
 */
document.onkeydown = function (event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    e.keyCode === 13 && sendMsgToServer();
};
