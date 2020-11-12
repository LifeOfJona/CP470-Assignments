package com.example.androidassignments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.CallLog;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Messages.db";
    public static final String TABLE_NAME = "chatTable";
    private static final int VERSION_NUM = 2;
    public static final String ACTIVITY_NAME = "ChatDatabaseHelper";
    public static final String KEY_ID = "KEY_ID"; // Autoincrement
    public static final String KEY_MESSAGE = "KEY_MESSAGE";


    // Table/Database creation statement
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NAME + "(" + KEY_ID
            + " integer primary key autoincrement, " + KEY_MESSAGE
            + " text not null);";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        Log.i("ChatDatabaseHelper", "Calling onCreate");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        switch (oldVersion) {
//            case 3:
//                db.execSQL("UPDATE " + TABLE_NAME + " SET READ=1");
//            case 4:
//                db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD "
//                        + ChatConstants.PACKET_ID + " TEXT");
//                break;
//            case 5:
//                db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD "
//                        + );
//                break;
//            default:
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.w(ChatDatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion);
    }
}



