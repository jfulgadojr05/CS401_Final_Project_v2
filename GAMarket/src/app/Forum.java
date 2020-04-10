package GAMarket;

public class Forum {
	private static final int LESS = 0;
	private static final int GREATER = 1;
	private static final int EQUAL = 2;
    private static final int NOT_FOUND = -1;
    private Integer maxThreads = 50;
    private Integer maxGames = 500;
    private Game game;
    private Game[] games = new Game[maxGames];
    private Thread[] threads = new Thread[maxThreads];

    // getters & setters
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game[] getGames() {
        return games;
    }

    public void setGames(Game[] games) {
        this.games = games;
    }

    public Thread[] getThreads() {
        return threads;
    }

    public void setThreads(Thread[] threads) {
        this.threads = threads;
    }

/*    toString function
        print game title */
    @Override
    public String toString() {
    }
    
    // all other functions
    
/*     displayAllGames function
        for loop
            display Game title */
    public void displayAllGames(Game[] games) {
    }

/*  displayAllThreads function
        for loop
            display All threads
            allow user to choose */
    public void displayAllThreads(Thread[] threads) {
    }
    
/*  searchByGame function
        use binary search to find desired game
        by comparing the first letter
        init int first, last, midpoint, pos(position)
        init boolean moreToSearch = first <= last and
        found set to false
        while loop
            midpoint = (first + last) / 2
            switch case
                (comparedTo function which will
                return the value of LESS, GREATER,
                or Equal (0,1,2) )
                case LESS 
                    last = midpoint - 1
                    moreToSearch = first <= last
                    break
                case GREATER
                    first = midPoint + 1
                    moreToSearch = first <= last
                    break
                case Equal
                    check if values are equal with compareTo function
                    if check < 0
                        case less code block here
                    else if check > 0
                        case greater code block here
                    else
                        values are equal
                        search done return game */
    public Thread[] searchByGame(Game game){
        return null;
    }

    /*     ComparedTo function (helper)
        compare the two parameters, string toSearch
        and Thread topic string
            if toSearch < thread topic
                return LESS (0)
            else if toSearch > thread topic
                return GREATER(1)
            else
                return equal(2) */
    private Integer ComparedTo(String toSearch, Thread thread) {
        return null;
    }

}