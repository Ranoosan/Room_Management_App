package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EMAIL + " TEXT UNIQUE, "
                + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1; // Return true if insertion was successful
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_ID},
                COLUMN_EMAIL + "=?",
                new String[]{email},
                null, null, null);

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean validateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_ID},
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password},
                null, null, null);

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }

    public boolean updateUserDetails(String oldEmail, String newEmail, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a ContentValues object to hold the updated data
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", newEmail);  // Update email
        contentValues.put("password", newPassword); // Update password

        // Update the database
        int result = db.update("users", contentValues, "email = ?", new String[]{oldEmail});

        db.close();

        // If the result is greater than 0, the update was successful
        return result > 0;
    }


    // Delete user by email
    public boolean deleteUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_USERS, COLUMN_EMAIL + "=?", new String[]{email});
        return rowsDeleted > 0; // Return true if a user was deleted
    }

    // Retrieve all users
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USERS,
                null, // Retrieve all columns
                null, null, // No WHERE clause
                null, null, null); // No grouping or ordering
    }

    public Cursor getUserDetails(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USERS, // Table name
                null,                // Columns to retrieve (null = all)
                COLUMN_EMAIL + "=?", // WHERE clause
                new String[]{email}, // WHERE arguments
                null, null, null);   // No grouping or ordering
    }

}
