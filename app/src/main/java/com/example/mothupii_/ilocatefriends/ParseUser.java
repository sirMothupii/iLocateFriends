package com.example.mothupii_.ilocatefriends;

/**
 * Created by Mothupii_ on 2017-10-17.
 */

class ParseUser
{

    private static ParseQuery<ParseUser> query;
    private String email;

    public static ParseQuery<ParseUser> getQuery() 
    {
        return query;
    }

    public void get(String latitude)
    {
    }

    public static ParseUser getCurrentUser()
    {

        return null;
    }

    public void put(String isOnline, boolean b)
    {
    }

    public void saveInBackground() 
    {
        
    }

    public static void logInInBackground(String username, String password, LogInCallback logInCallback)
    {
    }

    public String getEmail()
    {
        return email;
    }
}
