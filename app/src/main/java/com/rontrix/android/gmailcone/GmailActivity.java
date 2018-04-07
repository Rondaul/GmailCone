package com.rontrix.android.gmailcone;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.rontrix.android.gmailcone.adapter.MessagesAdapter;
import com.rontrix.android.gmailcone.model.Message;

import java.util.ArrayList;
import java.util.List;

public class GmailActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, MessagesAdapter.MessageAdapterListener, NavigationView.OnNavigationItemSelectedListener {

    private List<Message> messages = new ArrayList<>();
    private RecyclerView recyclerView;
    private MessagesAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        messages.add(new Message(1, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", false, false, getRandomMaterialColor("400")));
        messages.add(new Message(2, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", false, true, getRandomMaterialColor("400")));
        messages.add(new Message(3, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", false, false, getRandomMaterialColor("400")));
        messages.add(new Message(4, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", true, false, getRandomMaterialColor("400")));
        messages.add(new Message(5, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", false, false, getRandomMaterialColor("400")));
        messages.add(new Message(6, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", true, true, getRandomMaterialColor("400")));
        messages.add(new Message(7, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", false, false, getRandomMaterialColor("400")));
        messages.add(new Message(8, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", true, false, getRandomMaterialColor("400")));
        messages.add(new Message(9, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", true, false, getRandomMaterialColor("400")));
        messages.add(new Message(10, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", false, false, getRandomMaterialColor("400")));
        messages.add(new Message(11, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", true, false, getRandomMaterialColor("400")));
        messages.add(new Message(12, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", true, true, getRandomMaterialColor("400")));
        messages.add(new Message(13, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", false, false, getRandomMaterialColor("400")));
        messages.add(new Message(14, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", true, true, getRandomMaterialColor("400")));
        messages.add(new Message(15, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", true, false, getRandomMaterialColor("400")));
        messages.add(new Message(15, "Ron Daulagupu", "Important meeting at 2 p.m" , "Hey, I just mail to inform you about the meeting we have on Sunday at 2 p.m. I want you to be ready with your presentation. Thank you.",
                "10:57 AM", "R", true, false, getRandomMaterialColor("400")));

        mAdapter = new MessagesAdapter(this, messages, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        actionModeCallback = new ActionModeCallback();

        // show loader and fetch messages
        /*swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        getInbox();
                    }
                }
        );*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    private void getInbox() {
//        swipeRefreshLayout.setRefreshing(true);
//
//        ApiInterface apiService =
//                ApiClient.getClient().create(ApiInterface.class);
//
//        Call<List<Message>> call = apiService.getInbox();
//        call.enqueue(new Callback<List<Message>>() {
//            @Override
//            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
//                // clear the inbox
//                messages.clear();
//
//                // add all the messages
//                // messages.addAll(response.body());
//
//                // TODO - avoid looping
//                // the loop was performed to add colors to each message
//                for (Message message : response.body()) {
//                    // generate a random color
//                    message.setColor(getRandomMaterialColor("400"));
//                    messages.add(message);
//                }
//
//                mAdapter.notifyDataSetChanged();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(Call<List<Message>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Unable to fetch json: " + t.getMessage(), Toast.LENGTH_LONG).show();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
//    }

    private int getRandomMaterialColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = getResources().getIdentifier("mdcolor_" + typeColor, "array", getPackageName());

        if (arrayId != 0) {
            TypedArray colors = getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search...", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
        int count = mAdapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    @Override
    public void onRefresh() {

        // swipe refresh is performed, fetch the messages again
        //getInbox();

    }

    @Override
    public void onIconClicked(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }

        toggleSelection(position);

    }

    @Override
    public void onIconImportantClicked(int position) {
        // Star icon is clicked,
        // mark the message as important
        Message message = messages.get(position);
        message.setImportant(!message.isImportant());
        messages.set(position, message);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMessageRowClicked(int position) {
        // verify whether action mode is enabled or not
        // if enabled, change the row state to activated
        if (mAdapter.getSelectedItemCount() > 0) {
            enableActionMode(position);
        } else {
            // read the message which removes bold from the row
            Message message = messages.get(position);
            message.setRead(true);
            messages.set(position, message);
            mAdapter.notifyDataSetChanged();

            Toast.makeText(getApplicationContext(), "Read: " + message.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRowLongClicked(int position) {
        // long press is performed, enable action mode
        enableActionMode(position);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);

            // disable swipe refresh if action mode is enabled
            swipeRefreshLayout.setEnabled(false);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    // delete all the selected messages
                    deleteMessages();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mAdapter.clearSelections();
            swipeRefreshLayout.setEnabled(true);
            actionMode = null;
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.resetAnimationIndex();
                    // mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    // deleting the messages from recycler view
    private void deleteMessages() {
        mAdapter.resetAnimationIndex();
        List<Integer> selectedItemPositions =
                mAdapter.getSelectedItems();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            mAdapter.removeData(selectedItemPositions.get(i));
        }
        mAdapter.notifyDataSetChanged();
    }
}
