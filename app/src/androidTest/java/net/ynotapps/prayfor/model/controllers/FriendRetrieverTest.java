package net.ynotapps.prayfor.model.controllers;

import com.orm.SugarApp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestLifecycleApplication;
import org.robolectric.annotation.Config;

import java.lang.reflect.Method;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./app/src/main/AndroidManifest.xml", emulateSdk = 18)
public class FriendRetrieverTest extends SugarApp implements TestLifecycleApplication {

    @Test
    public void testRetrieveNoFriendsFromGroup() throws Exception {
        // TODO: find out how to get Sugar ORM to talk nice with Robolectric
//        FriendGroup.deleteAll(FriendGroup.class);
//        FriendGroup friendGroup = new FriendGroup("FriendGroup");
//        friendGroup.save();
//        List<Friend> retrievedFriendsList = FriendRetriever.retrieveAllFriendsFromGroup(friendGroup);
//        assertEquals(0, retrievedFriendsList.size());
    }

    @Test
    public void testRetrieveManyFriendsFromManyGroups() throws Exception {
//        Friend.deleteAll(Friend.class);
//        Friend friend1 = new Friend("Friend1");
//        Friend friend2 = new Friend("Friend2");
//        Friend friend3 = new Friend("Friend3");
//        Friend friend4 = new Friend("Friend4");
//        Friend friend5 = new Friend("Friend5");
//
//        friend1.save();
//        friend2.save();
//        friend3.save();
//        friend4.save();
//        friend5.save();
//
//        FriendGroup friendGroup1 = new FriendGroup("FriendGroup1");
//        FriendGroup friendGroup2 = new FriendGroup("FriendGroup2");
//
//        friendGroup1.save();
//        friendGroup2.save();
//
//        new FriendGroupMap(friend1, friendGroup1).save();
//        new FriendGroupMap(friend2, friendGroup1).save();
//        new FriendGroupMap(friend3, friendGroup2).save();
//        new FriendGroupMap(friend4, friendGroup2).save();
//        new FriendGroupMap(friend5, friendGroup2).save();
//
//        List<Friend> friendsFromGroup1 = FriendRetriever.retrieveAllFriendsFromGroup(friendGroup1);
//        List<Friend> friendsFromGroup2 = FriendRetriever.retrieveAllFriendsFromGroup(friendGroup2);
//        assertEquals(2, friendsFromGroup1.size());
//        assertEquals(3, friendsFromGroup2.size());
    }

    @Test
    public void testRetrieveAllGroupsThatContainFriend() throws Exception {

    }

    @Override
    public void beforeTest(Method method) {

    }

    @Override
    public void prepareTest(Object o) {

    }

    @Override
    public void afterTest(Method method) {

    }
}