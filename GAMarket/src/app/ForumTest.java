package GAMarket;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class ForumTest {
    /**
     * Rigorous Test.
     */
    
    @Test
    public void testChat() {
        Forum forum = new Forum();
        Game game = new Game("Title1");
        Game game2 = new Game("Title2");

        // test find User function
        assertEqual(game2.getTitle(), forum.searchByGame(game2.getTitle()));
    }
}
