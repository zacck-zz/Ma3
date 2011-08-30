package com.ke.matatu.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataStore {
	
	private SQLiteDatabase db;
	public DataStore(Context context) {
		databaseHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public DataStore open() {
		try {
			db = databaseHelper.getWritableDatabase();
		} catch (SQLException e) {
			db = databaseHelper.getReadableDatabase();
		}
		
		return this;
	}
	
	public void close() throws SQLException {
		db.close();
	}
	
	public String getCreateTableQuery(String table) {
		return "CREATE TABLE " + table +"(id PRIMARY KEY AUTOINCREMENT, name text, latitude double, longitude double)";
	}
	
	public void insertTaxiOffice(StationeryPoint object) {
		ContentValues values = new ContentValues();
		values.put("name", object.getName());
		values.put("latitude", object.getLatitude());
		values.put("longitude", object.getLongitude());
		db.insert(TAXI_OFFICE_TABLE, null, values);
	}
	
	public Cursor getAllTaxiOffices() {
		return db.query(TAXI_OFFICE_TABLE, new String[] {"id", "latitude", "longitude"},
				null, null, null, null, null);
	}
	
	public void insertMatatuStop(StationeryPoint object) {
		ContentValues values = new ContentValues();
		values.put("name", object.getName());
		values.put("latitude", object.getLatitude());
		values.put("longitude", object.getLongitude());
		db.insert(MATATU_STOP_TABLE, null, values);
	}
	
	public Cursor getAllMatatuStops() {
		return db.query(MATATU_STOP_TABLE, new String[] {"id", "latitude", "longitude"},
				null, null, null, null, null);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + MATATU_STOP_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + TAXI_OFFICE_TABLE);
			onCreate(db);
		}
		
	}
	
	private DatabaseHelper databaseHelper;
	
	private static final String DATABASE_NAME = "matatu.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TAXI_OFFICE_TABLE = "taxi_offices";
	private static final String MATATU_STOP_TABLE = "matatu_stops";
	private static final String DATABASE_CREATE = "CREATE DATABASE";	
}