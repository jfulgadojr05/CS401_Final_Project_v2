package GAMarket;

public class Thread {

    private Integer maxPost = 50;
    private String topicName;    
    private Integer threadID;
    private Integer gameID;
    private Post[] posts = new Post[maxPost];
    private Integer postCounter;
    private Post originalPost = new Post();


    public Thread() {
        topicName = " ";
        posts = null;
        postCounter = 0;
        originalPost = null;
        return ; 
    }

    // getters & setters
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getThreadID() {
        return threadID;
    }

    public void setThreadID(Integer threadID) {
        this.threadID = threadID;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public Post[] getPosts() {
        return posts;
    }

    public void setPosts(Post[] posts) {
        this.posts = posts;
    }

    public Post getOriginalPost() {
        return originalPost;
    }

    public void setOriginalPost(Post originalPost) {
        this.originalPost = originalPost;
    }
    
    // all other methods below

/*  createThread function
        create title
        create post
        add it into the thread array */
    public void createThread(User user) {
    }

/*  searchByThread function
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
                        search done return Thread */
    public Thread searchByThread(String topic){
        return null;
    }

/*  displayThread function
        set originalPost = post[0]
        originalPost toString
        for loop
            toString every post in the post array
         */
    public void displayThread() {
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
    private int ComparedTo(String toSearch, Thread thread) {
    }

}