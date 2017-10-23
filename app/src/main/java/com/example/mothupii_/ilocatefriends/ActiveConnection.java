package com.example.mothupii_.ilocatefriends;

/**
 * Created by Mothupii_ on 2017-10-17.
 */

//@ParseClassName("ActiveConnection")
public class ActiveConnection
{

    /**
     * setInvitorEmail()
     * @param value
     */
    public void setInvitorEmail(int value)
    {
        put("invitorEmail", value);
    }

    private void put(String invitorEmail, int value)
    {

    }

    /**
     * getInvitorEmail()
     * @return String
     */
    public String getInvitorEmail() {
        return getString("invitorEmail");
    }

    private String getString(String invitorEmail)
    {

        return invitorEmail;
    }

    /**
     * setFriendEmail()
     * @param value
     */
    public void setFriendEmail(int value)
    {
        put("friendEmail", value);
    }

    /**
     * getFriendEmail()
     * @return String
     */
    public String getFriendEmail()
    {
        return getString("friendEmail");
    }

    /**
     * setStatus()
     * @param value
     */
    public void setStatus(int value)
    {
        put("status", value);
    }

    /**
     * getStatus() - get invitation status
     * 1 - pending
     * 2 - connected
     * 3 - declined
     * @return int status code
     */
    public int getStatus()
    {
        return getInt("status");
    }

    private int getInt(String status)
    {

        return 0;
    }


}
