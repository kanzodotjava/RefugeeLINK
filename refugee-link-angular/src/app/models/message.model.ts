export interface Message {
    id?: number; // optional because it's set by the server
    senderUsername: string;
    receiverUsername: string;
    content: string;
    sentAt?: string; // depending on how you handle dates
  }