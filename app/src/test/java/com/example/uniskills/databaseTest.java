package com.example.uniskills;

import android.content.Context;

import com.example.uniskills.resources.DBHelper;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class databaseTest {

    static Context context;
    DBHelper db = null;


    /** This method tests for a user in a database, it should return false as the password
     * isn't long enough.
     * @author T6
     * @version 1.0
     */
    @Test
    public void TestForUser() {
        db = new DBHelper(databaseTest.context);
        Boolean checkUserPass = db.checkUsernamePassword("user", "pass");
        assertEquals(checkUserPass, false);
    }


    /** This method inserts a user into the database and tests to see if it was inserted properly,
     * if it is done properly then it will return true.
     * @author T6
     * @version 1.0
     */
    @Test
    public void TestForInsertedUser() {
        db = new DBHelper(databaseTest.context);
        db.insertUserData("userOne", "password124");
        Boolean checkUserPass = db.checkUsernamePassword("user", "pass");
        assertEquals(checkUserPass, true);
    }


    /** This method tests to see the method returns the right data type
     * @author T6
     * @version 1.0
     */
    @Test
    public void getTasks() {
        db = new DBHelper(databaseTest.context);

        ArrayList s = db.getCreative();
        ArrayList list = null;

        assertEquals(s.getClass().getName(), list.getClass().getName());
    }

    /** This method tests to see the method returns the right data type
     * @author T6
     * @version 1.0
     */
    @Test
    public void addition() {
        assertEquals(3, 1+2);
    }




}
