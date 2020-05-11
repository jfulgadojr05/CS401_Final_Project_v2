package app;

public class Admin extends User {
    // Interface would list four buttons that would execute the four methods in the class
    public int adminId;

    public void banUser(User myUser) // passes a user as a parameter to limit their access to a thread
    {
        // code
    }

    public void deleteGame(Game myGame) // passes a game to delete
    {
        // code
    }

    public void deleteThread(Thread myThread) // delete a game thread
    {
        // code
    }

    public void deleteMessage(Message myMsg) // remove's a message made by a user
    {
        // code
    }
}