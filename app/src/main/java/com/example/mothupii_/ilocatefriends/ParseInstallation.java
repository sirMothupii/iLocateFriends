package com.example.mothupii_.ilocatefriends;

/**
 * Created by Mothupii_ on 2017-10-17.
 */

class ParseInstallation
{
    private static ParseInstallation currentInstallation;
    private ParseACL ACL;

    public static ParseInstallation getCurrentInstallation()
    {
        return currentInstallation;
    }

    public void setACL(ParseACL ACL)
    {
        this.ACL = ACL;
    }

    public void saveInBackground()
    {
    }

    public void put(String user, ParseUser currentUser)
    {

    }
}
