package GAMarket;

public class Friends {
    private Integer maxFriends = 100;
    private Integer currentFriends;
    private User[] friendsList = new User[maxFriends];
    private User[] requestsList = new User[maxFriends];

    public User[] getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(User[] friendsList) {
        this.friendsList = friendsList;
    }

    public User[] getRequestsList() {
        return requestsList;
    }

    public void setRequestsList(User[] requestsList) {
        this.requestsList = requestsList;
    }

/*  displayAllFriends function
        for loop
            display all friends username */
    public void displayAllFriends() {
    }

/*     addFriend function
        search User in database
        if user found
            send request 
                if true 
                    add into friend array sorted
                    alphabetically */
	public void addFriend(User newUser) {
    }
    
/* check array if full
		search into array to find location for alphabetical order
			by check each element if is less, greater or equal to
		find location to insert then move all elements down one
			to make space for insertion
	*/
    public void addFriendHelper(User user) {
    }
    
/*     deleteFriend function
        find friend to delete
        if the search comes back with num
        other than -1
            delete User from array
            use removehelper */
	public void deleteFriend(User myUser) {
	}
    
/*     messageFriend function
        display friendsList
        pick friend to message
        create message
        send message */
	public void messageFriend(User myUser) {
	}
    
/*  blockFriend function
        find friend to delete
        if the search comes back with num
        other than -1
            delete User from array
            use removehelper 
        go into other users array to delete
            current users from their array
        checkFriendRequest function
            set the blocked friend to deny
            */
	public void blockFriend(User myUser) {
	}
    
/*  checkFriendRequests function
        display requestsList array with for loop */
    public void checkFriendRequests() {
    }

/*  sendRequest function
        sent friend request to desired users 
        friend request inbox
        if return is yes,
            add friend into array
        else 
            do nothing */
    public boolean sendRequest(User user) {
    }

/*  searchUser function
    use binary search to find desired topic
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
    private Thread searchUser(String topic) {
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
    private int ComparedTo(String toSearch, Thread thread) {
    }

/*  for loop
        remove friend and move all elements towards the removed postions */
	private void removeFriendHelp(int location) {		
    }

}