package ru.smak.dbtest

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "myDB", null, 1)
{

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE test (" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "email text not null" +
                ")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun addRow(name: String, email: String) {
        val values = ContentValues()
        values.put("name", name)
        values.put("email", email)
        val db = writableDatabase
        db.insert("test", null, values)
        db.close()
    }

    fun getAllValues(): List<Pair<String, String>>{
        val db = readableDatabase
        val cursor = db.query("test", null, null, null, null, null, "name")
        val result = mutableListOf<Pair<String, String>>()
        if (cursor.count>0){
            while (cursor.moveToNext()){
                val row = Pair<String, String>(
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("email"))
                )
                result.add(row)
            }
        }
        cursor.close()
        return result

    }
}