package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FeedbackDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FeedbackDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FEEDBACK = "feedback";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FEEDBACK_TEXT = "feedback_text";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    public FeedbackDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FEEDBACK_TABLE = "CREATE TABLE " + TABLE_FEEDBACK + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FEEDBACK_TEXT + " TEXT, "
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(CREATE_FEEDBACK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        onCreate(db);
    }

    public void addFeedback(String feedbackText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FEEDBACK_TEXT, feedbackText);
        db.insert(TABLE_FEEDBACK, null, values);
        db.close();
    }

    public List<String> getAllFeedback() {
        List<String> feedbackList = new ArrayList<>();
        String selectQuery = "SELECT " + COLUMN_FEEDBACK_TEXT + " FROM " + TABLE_FEEDBACK + " ORDER BY " + COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                feedbackList.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FEEDBACK_TEXT)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return feedbackList;
    }
}
