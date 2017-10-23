package com.example.mothupii_.ilocatefriends;

/**
 * Created by Mothupii_ on 2017-10-17.
 */

class LocationRequest 
{

    private long interval;
    private long fastestInterval;
    private float smallestDisplacement;

    public static void create()
    {
        
    }

    public void setInterval(long interval) 
    {
        this.interval = interval;
    }

    public void setFastestInterval(long fastestInterval)
    {
        this.fastestInterval = fastestInterval;
    }

    public void setSmallestDisplacement(float smallestDisplacement)
    {
        this.smallestDisplacement = smallestDisplacement;
    }

    public class PRIORITY_HIGH_ACCURACY
    {
    }
}
