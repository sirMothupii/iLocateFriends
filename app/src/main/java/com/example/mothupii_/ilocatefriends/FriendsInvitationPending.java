package com.example.mothupii_.ilocatefriends;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.service.autofill.SaveCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * FriendsInvitationPendingActivity
 */
public class FriendsInvitationPending extends Activity
{

    private static String ACTIVITY = "FriendsInvitationPendingActivity";
    private static int PENDING = 1;
    private static int ACCEPTED = 2;
    private static int DECLINED = 3;
    private static Context context;
    private ListView pendingInvitesListView;
    private ArrayList<String> pendingInvites = null;
    private TextView emailAddress;
    private InvitationPendingListViewAdapter adapter;

    /**
     * onCreate()
     *
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_friends_invitation_pending);

        Intent intent = getIntent();
        this.pendingInvites = intent.getStringArrayListExtra("pendingInvites");

        Log.d(ACTIVITY, "Inside pop up screen.");
        for (int i = 0; i < this.pendingInvites.size(); i++) {
            Log.d(ACTIVITY, this.pendingInvites.get(i));
        }

        this.pendingInvitesListView = (ListView) findViewById(android.R.id.list);
        this.adapter = new InvitationPendingListViewAdapter(this, this.getStringArray(this.pendingInvites));
        this.pendingInvitesListView.setAdapter((ListAdapter) this.adapter);

        Button close_button = (Button) findViewById(R.id.close_button);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * onPause()
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ACTIVITY, "onPause");
    }

    /**
     * onStart()
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(ACTIVITY, "onStart");
    }

    /**
     * onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ACTIVITY, "onResume");
    }

    /**
     * onStop()
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(ACTIVITY, "onStop");
    }

    /**
     * onDestroy()
     */
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ACTIVITY, "onDestroy");
    }

    /**
     * getStringArray()
     *
     * @param list
     * @return String[]
     */
    private String[] getStringArray(ArrayList<String> list) {
        String[] pendingInvites = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            pendingInvites[i] = list.get(i);
        }
        return pendingInvites;
    }

    /**
     * acceptClickListener - called form the Adapter
     */
    public View.OnClickListener acceptClickListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View view)
        {
            //final View v = view;
            int position = pendingInvitesListView.getPositionForView((View) view.getParent());

            String email = (String) pendingInvitesListView.getAdapter().getItem(position);
            try {
                acceptInvitation(email);
            } catch (ParseException e)
            {
                e.printStackTrace();
            }

            View parent = (View) ((View) ((View) view.getParent()).getParent()).getParent();
            parent.setVisibility(View.GONE);

        }

    };

    /**
     * declineClickListener - called form the Adapter
     */
    public View.OnClickListener declineClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final View v = view;
            final int position = pendingInvitesListView.getPositionForView((View) v.getParent());

            String email = (String) pendingInvitesListView.getAdapter().getItem(position);
            declineInvitation(email);

            View parent = (View) ((View) ((View) view.getParent()).getParent()).getParent();
            parent.setVisibility(View.GONE);
        }
    };

    /**
     * acceptInvitation()
     *
     * @param email
     */
    public void acceptInvitation(String email) throws ParseException
    {
        Log.d(ACTIVITY, "ACCEPT");
        this.saveToFriends(this.getUserByEmail(email), ACCEPTED);
        this.changeFriendsInvitationsStatusTo(this.getUserByEmail(email), ParseUser.getCurrentUser().getEmail(), ACCEPTED);
        this.toastIt("Friend invitation accepted.");
    }

    /**
     * declineInvitation()
     *
     * @param email
     */
    public void declineInvitation(String email)
    {
        Log.d(ACTIVITY, "DECLINE");
        try {
            this.changeFriendsInvitationsStatusTo(this.getUserByEmail(email), ParseUser.getCurrentUser().getEmail(), DECLINED);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        this.toastIt("Friend invitation declined.");
    }

    /**
     * saveToFriends() - save to Friends table
     */
    public void saveToFriends(ParseUser friendUser, int status) {
        // getFriends(ParseUser user, ParseUser friend)
        if (this.getFriends(ParseUser.getCurrentUser(), friendUser) == null) {
            Friends friends = new Friends();
            friends.setUser(ParseUser.getCurrentUser());
            friends.setUsersFriend(friendUser);
            friends.setStatus(status);

            ParseACL friendAcl = new ParseACL();
            friendAcl.setPublicReadAccess(true);
            friendAcl.setPublicWriteAccess(true);
            friends.setACL(friendAcl);

            friendUser.saveInBackground();
        } else {
            Log.d(ACTIVITY, "User already in Friends table!");
        }

        // Now add the other way
        // getFriends(ParseUser friend, ParseUser user)
        if (this.getFriends(friendUser, ParseUser.getCurrentUser()) == null) {
            Friends friends = new Friends();
            friends.setUser(friendUser);
            friends.setUsersFriend(ParseUser.getCurrentUser());
            friends.setStatus(status);

            ParseACL friendAcl = new ParseACL();
            friendAcl.setPublicReadAccess(true);
            friends.setACL(friendAcl);

            friendUser.saveInBackground();
        } else {
            Log.d(ACTIVITY, "User already in Friends table!");
        }
    }

    /**
     * changeFriendsInvitationsStatusTo()
     *
     * @param user
     * @param friendEmail
     * @param status
     */
    public void changeFriendsInvitationsStatusTo(ParseUser user, String friendEmail, int status) throws ParseException {
        Log.d(ACTIVITY, "Inviter Email: " + user.getEmail());
        Log.d(ACTIVITY, "Friend Email: " + friendEmail);
        FriendInvitation friendInvitation = null;
        ParseQuery query = ParseQuery.getQuery("FriendInvitation");
        query.whereEqualTo("user", user);
        query.whereEqualTo("friend", friendEmail);
        friendInvitation = (FriendInvitation) query.getFirst();
        Log.d(ACTIVITY, "INVITER: " + friendInvitation.getUser());
        friendInvitation.setStatus(status);
        user.saveInBackground();

    }

    /**
     * getUserByEmail() - Assigns user to this.user so we don't do the same query again
     *
     * @param emailAddress
     * @return ParseUser
     */
    private ParseUser getUserByEmail(String emailAddress) {
        ParseUser user = null;
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", emailAddress);
        user = (ParseUser) query.getFirst();
        return user;
    }

    /**
     * getFriends()
     *
     * @param user
     * @param friend
     * @return Friends
     */
    private Friends getFriends(ParseUser user, ParseUser friend)
    {
        Friends friends = null;
        ParseQuery query = ParseQuery.getQuery("Friends");
        query.whereEqualTo("user", String.valueOf(user));
        query.whereEqualTo("usersFriend", String.valueOf(friend));
        friends = (Friends) query.getFirst();
        return friends;
    }

    /**
     * capitalizeString()
     *
     * @param str
     * @return String
     */
    public String capitalizeString(String str)
    {
        String newString = str.substring(0, 1).toUpperCase() + str.substring(1);
        return newString;
    }

    /**
     * toastIt() - toast used for form verification
     *
     * @param message
     */
    private void toastIt(String message) {
        Log.d(ACTIVITY, message);
        Toast t = Toast.makeText(getApplicationContext(), this.capitalizeString(message), Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }
}
