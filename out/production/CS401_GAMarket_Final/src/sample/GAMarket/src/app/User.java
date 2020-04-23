package sample.GAMarket.src.app;// any needed imports go here...


public class User{
	private String userName;
	private int userId; 
	private String picturePath;
	private String paymentInfo;
	private String About;
	// private app.GameCollection Id[]; // need app.GameCollection class
	
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
	
//	public app.GameCollection getCollection() // need app.GameCollection class; retrieves list of user's owned games
//	{
//		// code
//	}
	
	public String getPicture() // retrieves file path of user's profile picture and displays the picture
	{
		// code
		return this.picturePath;
	}
	
}