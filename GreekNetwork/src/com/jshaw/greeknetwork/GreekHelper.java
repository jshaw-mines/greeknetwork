package com.jshaw.greeknetwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GreekHelper extends SQLiteOpenHelper {

	//database info
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "greek.db";
	
	//table names
	public static final String MEMBERS_TABLE = "members";
	public static final String EVENTS_TABLE = "events";
	
	//member columns
	public static final String NAME = "member_name";
	public static final String YEAR = "year";
	public static final String POS = "posistion";
	public static final String COMMENTS = "comments";
	
	//event columns
	public static final String EVENT = "event_name";
	public static final String DATE = "event_date";
	public static final String DETAILS = "details";
	
	//tables
	private static final String CREATE_MEMBERS = "CREATE TABLE " + MEMBERS_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " 
							+ NAME + " TEXT, " + YEAR + " TEXT, "+POS+" TEXT, "+COMMENTS+" TEXT);";
	private static final String CREATE_EVENTS = "CREATE TABLE " + EVENTS_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " 
							+ EVENT + " TEXT, " + DATE + " REAL, "+DETAILS+" TEXT);";
	 
	public GreekHelper(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_MEMBERS);
		db.execSQL(CREATE_EVENTS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE "+ MEMBERS_TABLE+";");
		db.execSQL("DROP TABLE "+ EVENTS_TABLE+";");
		onCreate(db);
	}
	
	public void deleteData()
	{
		getWritableDatabase().delete(MEMBERS_TABLE, null, null);
		getWritableDatabase().delete(EVENTS_TABLE, null, null);
	}
	
	public void deleteOldEvents(long exp)
	{
		String[] temp = {String.valueOf(exp)};
		getWritableDatabase().delete(EVENTS_TABLE, DATE+" < ?", temp);
	}
	
	public void deleteMember(int id)
	{
		String[] temp =  {String.valueOf(id)};
		getWritableDatabase().delete(EVENTS_TABLE, "_id = ?", temp);
	}
	
	public Cursor getMembers()
	{
		return getReadableDatabase().rawQuery("SELECT * FROM "+MEMBERS_TABLE+" ORDER BY member_name ASC", null);	
	}
	
	public Cursor getMember(int id)
	{
		String[] temp =  {String.valueOf(id)};
		return getReadableDatabase().query(MEMBERS_TABLE, null, "_id = ?", temp, null, null, null);	
	}
	
	public Cursor getEvents(long exp)
	{
		String[] temp = {String.valueOf(exp)};
		return getReadableDatabase().query(EVENTS_TABLE, null, DATE+" >= ?", temp, null, null, null);	
	}
	
	public Cursor getEvent(int id)
	{
		String[] temp =  {String.valueOf(id)};
		return getReadableDatabase().query(EVENTS_TABLE, null, "_id = ?", temp, null, null, null);	
	}
	
	public void insertMember(Member m)
	{
		ContentValues values = new ContentValues();
		
		values.put(GreekHelper.NAME, m.getName());
		values.put(GreekHelper.YEAR, m.getYear());
		values.put(GreekHelper.POS, m.getPosition());
		values.put(GreekHelper.COMMENTS, m.getComments());
		
		getWritableDatabase().insert(GreekHelper.MEMBERS_TABLE, null, values);
	}
	
	public void updateMember(Member m) 
	{
		String[] args = {String.valueOf(m.getID())};
		
		ContentValues values = new ContentValues();
		
		values.put(GreekHelper.NAME, m.getName());
		values.put(GreekHelper.YEAR, m.getYear());
		values.put(GreekHelper.POS, m.getPosition());
		values.put(GreekHelper.COMMENTS, m.getComments());
		
		getWritableDatabase().update(MEMBERS_TABLE, values, "_ID = ?", args);		
	}
	
	public void insertEvent(Event e)
	{
		ContentValues values = new ContentValues();
		
		values.put(GreekHelper.EVENT, e.getName());
		values.put(GreekHelper.DATE, e.getDate());
		values.put(GreekHelper.DETAILS, e.getDetails());
		
		getWritableDatabase().insert(GreekHelper.EVENTS_TABLE, null, values);
	}
	
	public void updateEvent(Event e) 
	{
		String[] args = {String.valueOf(e.getID())};
		
		ContentValues values = new ContentValues();
		
		values.put(GreekHelper.EVENT, e.getName());
		values.put(GreekHelper.DATE, e.getDate());
		values.put(GreekHelper.DETAILS, e.getDetails());
		
		getWritableDatabase().update(EVENTS_TABLE, values, "_ID = ?", args);		
	}

	public String getName(Cursor c)
	{
		return c.getString(1);
	}
	
	public String getYear(Cursor c)
	{
		return c.getString(2);
	}
	
	public String getPos(Cursor c)
	{
		return c.getString(3);
	}
	
	public String getComments(Cursor c)
	{
		return c.getString(4);
	}

	public long getDate(Cursor c)
	{
		return c.getLong(2);
	}
	
	public String getDetails(Cursor c)
	{
		return c.getString(3);
	}
}
