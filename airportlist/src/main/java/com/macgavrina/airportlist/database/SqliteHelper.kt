package com.macgavrina.airportlist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri


class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SumTable.Requests.CREATION_REQUEST)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SumTable.Requests.DROP_REQUEST)
        onCreate(db)
    }

    companion object {

        val CONTENT_AUTHORITY = "com.macgavrina.loaders"

        val BASE_CONTENT_URI = Uri.parse("content://$CONTENT_AUTHORITY")

        private val DATABASE_NAME = "com.macgavrina.database.db"

        private val DATABASE_VERSION = 1
    }
}