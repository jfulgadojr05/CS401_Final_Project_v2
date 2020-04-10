package sample.GAMarket.src.app;// any needed imports go here...


public class User{
	private String userName;
	private int userId; 
	private String picturePath;
	private String paymentInfo;
	private String About;
	// private GameCollection Id[];
	
	// attributes for Admin
	private boolean isAdmin;

	// attributes for devUser
	private boolean isDevUser; 

	
	// methods
	public String getUsername() 
	{
		// code
		return this.userName;
	}
	
//	public GameCollection getCollection()
//	{
//		// code
//	}
	
	public String getPicture()
	{
		// code
		return this.picturePath;
	}
	
}