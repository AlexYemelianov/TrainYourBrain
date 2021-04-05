package com.yemelianov.trainyourbrain.db

import android.provider.BaseColumns

object UserDataBase : BaseColumns {

    const val TABLE_NAME = "Users"
    const val COLUMN_NAME_USERNAME = "Username"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "UserDB.db"

    const val CREATE_TABLE =
        "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "$COLUMN_NAME_USERNAME TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

}