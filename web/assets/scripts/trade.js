document.getElementById('contactUser').addEventListener('click', function () {
                // Show the chat box
                $('#chatBox').fadeIn();
            });

            // Add an event listener to the close button inside the chat box
            document.querySelector('#chatBox .close').addEventListener('click', function () {
                // Hide the chat box
                $('#chatBox').fadeOut();
            });
            document.getElementById('sendButton').addEventListener('click', function () {
                var message = document.getElementById('messageInput').value;
                var chatMessages = document.querySelector('.chat-messages');
                var messageDiv = document.createElement('div');
                messageDiv.className = 'message';
                messageDiv.textContent = message;
                chatMessages.appendChild(messageDiv);
                document.getElementById('messageInput').value = '';
            });