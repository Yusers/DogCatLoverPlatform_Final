<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style media="screen" type="text/css">
        .chat {
            width: 100%;
            height: 200px;
            border: 1px solid silver;
            overflow-y: scroll;
            padding: 10px;
        }
        .message-container {
            margin-bottom: 10px;
        }
        .my-message {
            text-align: right;
            background-color: #DDEEFF;
        }
        .other-message {
            text-align: left;
            background-color: #F4F4F4;
        }
        .message-content {
            display: inline-block;
            max-width: 70%;
            border-radius: 10px;
            padding: 5px 10px;
        }
        #msg {width: 80%;}
        h1 {text-align: center;}
        .send-button {
            width: 18%;
        }
    </style>
</head>
<script type="text/javascript">
    var wsUrl;
    if (window.location.protocol == 'http:') {
        wsUrl = 'ws://';
    } else {
        wsUrl = 'wss://';
    }
    var ws = new WebSocket(wsUrl + window.location.host + "/DogCatLoverPlatform/chat");
    
    var username = prompt("Please enter your username:");
    
    ws.onmessage = function(event) {
        var mySpan = document.getElementById("chat");
        mySpan.innerHTML += event.data + "<br/>";
    };
    
    ws.onerror = function(event){
        console.log("Error ", event)
    } 
    
    function sendMsg() {
        var msg = document.getElementById("msg").value;
        if(msg) {
            ws.send(username + ": " + msg);
            appendMessage(username + ": " + msg, true);
        }
        document.getElementById("msg").value = "";
    }
    
    function appendMessage(message, isMyMessage) {
        var chatBox = document.getElementById('chat');
        var messageContainer = document.createElement('div');
        var messageContent = document.createElement('div');
        messageContent.innerText = message;
        messageContainer.appendChild(messageContent);
        
        if (isMyMessage) {
            messageContainer.classList.add('my-message');
        } else {
            messageContainer.classList.add('other-message');
        }
        
        chatBox.appendChild(messageContainer);
    }
</script>
<body>
    <h1>Live Chat updates</h1>

    <div>
        <div id="chat" class="chat"></div>
        <div class="message-container">
            <input type="text" name="msg" id="msg" placeholder="Enter message here"/>
            <input type="button" class="send-button" value="Send" onclick="sendMsg();">
        </div>
    </div>
</body>
</html>
