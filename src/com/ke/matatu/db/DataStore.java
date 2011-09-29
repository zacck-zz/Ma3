package com.ke.matatu.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataStore {

	private SQLiteDatabase db;

	public DataStore(Context context) {
		helper = new DatabaseHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	public DataStore open() {
		try {
			db = helper.getWritableDatabase();
		} catch (SQLException e) {
			db = helper.getReadableDatabase();
		}

		return this;
	}

	public void close() throws SQLException {
		db.close();
		helper.close();
	}

	public String getCreateTableQuery(String table) {
		return "CREATE TABLE "
				+ table
				+ "(id PRIMARY KEY AUTOINCREMENT, name text, latitude double, longitude double)";
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static final String COL_LOCATION_ID = "location_id";
		private static final String COL_LOCATION_TYPE = "location_type";
		private static final String COL_LATITUDE = "latitude";
		private static final String COL_LONGITUDE = "longitude";

		public DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(String
					.format("CREATE TABLE %s ( INTEGER PRIMARY KEY AUTOINCREMENT, %s text, %s text, %s text, %s text)",
							DATABASE_NAME, COL_LOCATION_ID, COL_LOCATION_TYPE,
							COL_LATITUDE, COL_LONGITUDE));
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
			onCreate(db);
		}
	}

	private DatabaseHelper helper;

	private static final String DATABASE_NAME = "matatu.db";
	private static final int DATABASE_VERSION = 1;

}