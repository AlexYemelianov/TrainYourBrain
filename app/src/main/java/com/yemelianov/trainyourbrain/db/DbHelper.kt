package com.yemelianov.trainyourbrain.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context):SQLiteOpenHelper(context, UserDataBase.DATABASE_NAME,
null, UserDataBase.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(UserDataBase.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(UserDataBase.SQL_DELETE_TABLE)
        onCreate(db)
    }
}