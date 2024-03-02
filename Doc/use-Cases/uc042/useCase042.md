Use Case: UC042 - Chat with Apprentices

Actors:
- Mentor

Description:
This use case describes the process by which a mentor chats with their apprentices within the RefugeeLink app.

Preconditions:
- The mentor user has logged into their RefugeeLink account.
- The mentor user has access to the chat feature within the app for communicating with their apprentices.

Flow of Events:
1. The mentor user navigates to the chat feature within the RefugeeLink app.
2. The app retrieves a list of the mentor's current apprentices from the database.
3. The app displays the list of apprentices to the mentor user, including essential information such as their name, profile picture, and any relevant details.
4. The mentor user selects an apprentice from the list to initiate a chat session.
5. The app opens a chat interface where the mentor and the selected apprentice can exchange messages in real-time.
6. The mentor user can type and send messages to the apprentice, and receive messages from them.
7. Both the mentor and the apprentice receive notifications for new messages or updates in the chat conversation.
8. The chat history is securely stored within the app and accessible for future reference by both parties.
9. The mentor user can end the chat session and return to the list of apprentices to chat with other apprentices as needed.

Postconditions:
- The mentor has successfully chatted with their apprentice within the RefugeeLink app.
- The chat history is stored securely and accessible for future reference by both the mentor and the apprentice.

Alternative Flow:
- If there are no current apprentices available for chatting, the app displays a message indicating that there are no apprentices to chat with at the moment.
