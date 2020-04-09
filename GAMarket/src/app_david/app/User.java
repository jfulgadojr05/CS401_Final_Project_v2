package app;// any needed imports go here...

public class User{
	
	private String userName;
	private int userId;
	private String picturePath;
	private String paymentInfo;
	private String About;
//	private Game Collection Id[];
	
	// attributes for Admin
	public int adminId;
	private boolean isAdmin;
	
	// attributes for devUser
	private boolean isDevUser; 
	private int collectionId;
	private float income; 
//	private Game Collection developedGames[];
	
	// methods
	public String getUsername() 
	{
		return "Testing";
		// code
	}
	
//	public Game Collection void getCollection()
//	{
//		// code
//	}
	
	public String getPicture()
	{
		return "test";
		// code
	}
	
	// methods for Admin
	public void banUser(User myUser)
	{
		// code
	}
	
	public void deleteGame(Game myGame) 
	{
		// code
	}
	
	public void deleteThread(java.lang.Thread myThread)
	{
		// code
	}
	
	public void deleteMessage(Message myMsg)
	{
		// code
	}
	
	// methods for devUser
	public Message postGames(String gameData)
	{
		Message test= new Message("Jerome", "Good bye");
		return test;
	}
	
	public Message editTheirGames(String gameData)
	{
		Message test= new Message("Jerome", "Good bye");
		return test;
		// code
	}
	
	public Message deleteTheirGames(int gameId) 
	{
		Message test= new Message("Jerome", "Good bye");
		return test;
	}
	
	public void deleteLocalDevGameThreads()
	{
		// code
	}
	
}