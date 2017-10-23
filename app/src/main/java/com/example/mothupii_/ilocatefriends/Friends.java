package com.example.mothupii_.ilocatefriends;

/**
 * Created by Mothupii_ on 2017-10-17.
 */

class Friends
{

    private Object user;
    private Object usersFriend;
    private int status;
    private Object ACL;

    public void setUser(ParseUser user)
    {
        this.user = user;
    }

    public void setUsersFriend(ParseUser usersFriend)
    {
        this.usersFriend = usersFriend;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public void setACL(ParseACL ACL)
    {
        this.ACL = ACL;
    }
}
