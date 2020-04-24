package sample.GAMarket.src.app;



public class Login {
    private String username;
    private String password;

    void setUsername(String user){

        username = user;
    }

    void setPassword(String user){

        username = user;
    }

    void createNewUser(String username, String password){
        //to be implemented. method used for a new user registering
    }

    boolean checkLogin(String username, String password){
        //to be implemented. user server authentication method to verify credentials
        return false; //code stub
    }

    void loginRecovery(){
        //to be implemented. assist the user with recovering their account, preferably through email verification
        //but security questions is easier implementation
    }

}
