package com.yemelianov.trainyourbrain.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(context: Context) {

    val dbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = dbHelper.writableDatabase
    }

    fun writeDB(userScore: String) {
        val values = ContentValues().apply {
            put(UserDataBase.COLUMN_NAME_USERNAME, userScore)
        }
        db?.insert(UserDataBase.TABLE_NAME, null, values)
    }

    fun readDB(): ArrayList<String> {
        val dbList = ArrayList<String>()
        val cursor = db?.query(
            UserDataBase.TABLE_NAME,
            null, null, null, null, null, null
        )
        while (cursor?.moveToNext()!!) {
            val dbData =
                cursor.getString(cursor.getColumnIndexOrThrow(UserDataBase.COLUMN_NAME_USERNAME))
            dbList.add(dbData.toString())
        }
        cursor.close()
        return dbList
    }

    fun closeDB() {
        dbHelper.close()
    }

}