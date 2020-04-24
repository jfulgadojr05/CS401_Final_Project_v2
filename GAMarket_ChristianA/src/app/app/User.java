package app.app;// any needed imports go here...


public class User{
	private String userName;
	private int userId; 
	private String picturePath;
	private String paymentInfo;
	private String About;
	// private GameCollection Id[]; // need GameCollection class
	
	// attributes for Admin
	private boolean isAdmin;

	// attributes for devUser
	private boolean isDevUser; 

	
	// methods
	public String getUsername() // retrieves user's username
	{
		// code
		return this.userName;
	}
	
//	public GameCollection getCollection() // need GameCollection class; retrieves list of user's owned games
//	{
//		// code
//	}
	
	public String getPicture() // retrieves file path of user's profile picture and displays the picture
	{
		// code
		return this.picturePath;
	}
	
}