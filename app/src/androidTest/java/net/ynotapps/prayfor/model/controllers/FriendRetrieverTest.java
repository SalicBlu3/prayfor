//package net.ynotapps.prayfor.model.controllers;
//
//import android.test.ActivityInstrumentationTestCase2;
//
//import junit.framework.TestCase;
//
//import net.ynotapps.prayfor.ui.activities.DummyActivity;
//import net.ynotapps.prayfor.model.dto.Friend;
//import net.ynotapps.prayfor.model.dto.FriendGroup;
//import net.ynotapps.prayfor.model.dto.FriendGroupMap;
//
//import org.junit.Test;
//
//import java.util.List;
//
//public class FriendRetrieverTest extends TestCase {
//
//    @Test
//    public void testRetrieveNoFriendsFromGroup() throws Exception {
//        FriendGroup friendGroup = new FriendGroup("FriendGroup");
//        friendGroup.save();
//        List<Friend> retrievedFriendsList = FriendRetriever.retrieveAllFriendsFromGroup(friendGroup);
//        assertEquals(0, retrievedFriendsList.size());
//    }
//
//    @Test
//    public void testRetrieveManyFriendsFromManyGroups() throws Exception {
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
//    }
//
//    @Test
//    public void testRetrieveAllGroupsThatContainFriend() throws Exception {
//
//    }
//}