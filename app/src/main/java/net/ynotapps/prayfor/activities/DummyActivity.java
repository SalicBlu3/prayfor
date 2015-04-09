package net.ynotapps.prayfor.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import net.ynotapps.prayfor.R;
import net.ynotapps.prayfor.model.dto.Friend;
import net.ynotapps.prayfor.model.dto.FriendGroup;
import net.ynotapps.prayfor.model.dto.FriendGroupMap;
import net.ynotapps.prayfor.views.dialogs.NewFriendDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DummyActivity extends ActionBarActivity {

    @InjectView(R.id.feedback)
    TextView feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        ButterKnife.inject(this);
    }


    @OnClick(R.id.btCreateFriend)
    public void createFriend(View view) {

        View form = View.inflate(this, R.layout.dialog_new_friend, null);
        final MaterialEditText friendName = ButterKnife.findById(form, R.id.editFriendName);
        final MaterialEditText friendGroup = ButterKnife.findById(form, R.id.editFriendGroup);

        // Open Dialog
        new NewFriendDialog(this).show();
    }

    @OnClick(R.id.btFriendGroupView)
    public void goFriendGroupView(View view) {
        Intent i = new Intent(this, ViewFriendsActivity.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dummy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
