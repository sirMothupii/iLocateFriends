package com.example.mothupii_.ilocatefriends;

/**
 * Created by Mothupii_ on 2017-10-17.
 */

class ConnectionResult 
{

    public static final int SUCCESS = 1;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 0;
    public static final int SERVICE_DISABLED = 0;
    public static int SERVICE_MISSING;
    private int errorCode;

    public int getErrorCode()
    {
        return errorCode;
    }
}
