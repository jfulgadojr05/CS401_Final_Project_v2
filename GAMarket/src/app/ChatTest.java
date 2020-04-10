package GAMarket;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class ChatTest {
    /**
     * Rigorous Test.
     */
    
    @Test
    public void testChat() {
        Chat chat = new Chat();
        User foundUser = new User("UserA");
        User searchedUser = new User("UserA");

        // test find User function
        assertEqual(foundUser.getUsername(), chat.findUser(searchedUser.getUsername()));
    }
}
