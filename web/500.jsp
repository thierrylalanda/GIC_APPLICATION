<%-- 
    Document   : message
    Created on : 29 avr. 2017, 20:25:47
    Author     : messi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <title>error</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"> 

    <link href="temp/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="temp/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />


</head>

<body>

    <div>
        <span>Subject:</span>
        <input id="subject" type="text" />
        <br />
        <span>Content:</span>
        <input id="content" type="text" />
    </div>
    <div>
        <input type="submit" value="Send message" onclick="send()" />
    </div>
    <div id="messages"></div>
    <script type="text/javascript">
        var webSocket =
                new WebSocket('ws://localhost:8080/GCI_APPLICATION/chatApp/mcd123');
        //  Wss: // websocket p12345678 trial.hanatrial.ondemand.com/WebSocket/chatApp/ USERNAME
        webSocket.onerror = function (event) {
            onError(event);
        };

        webSocket.onopen = function (event) {
            onOpen(event);
        };

        webSocket.onmessage = function (event) {
            onMessage(event);
        };

        function onMessage(event) {
            var json = JSON.parse(event.data);
            document.getElementById('messages').innerHTML
                    = '<br />Received server response!'
                    + '<br />Subject: ' + json.subject
                    + '<br />Content: ' + json.content;
        }

        function onOpen(event) {
            alert('Connection established');
        }

        function onError(event) {
            alert('Error');
        }

        function send() {
            var subject = document.getElementById('subject').value;
            var content = document.getElementById('content').value;
            var json = {
                'subject': subject,
                'content': content
            };
            webSocket.send(JSON.stringify(json));
            return false;
        }
        window.addEventListener("load", webSocket, false);
    </script>

    <script src="temp/js/jquery-1.7.2.min.js"></script>
    <script src="temp/js/bootstrap.js"></script>

</body>

</html>