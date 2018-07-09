package com.macgavrina.airportlist.database

import java.nio.file.Files.delete
import com.macgavrina.airportlist.data.Airport
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.macgavrina.airportlist.data.SumResult


class SumTable (var context: Context) {

    val URI = SQLiteHelper.BASE_CONTENT_URI.buildUpon().appendPath(Requests.TABLE_NAME).build()

    fun save(sumResult: SumResult) {
        context.getContentResolver().insert(URI, toContentValues(sumResult))
    }

    fun toContentValues(sumResult: SumResult): ContentValues {
        val values = ContentValues()
        values.put(Columns.A, sumResult.a)
        values.put(Columns.B, sumResult.b)
        values.put(Columns.C, sumResult.c)
        return values
    }

    fun fromCursor(cursor: Cursor): SumResult {
        val a = cursor.getInt(cursor.getColumnIndex(Columns.A))
        val b = cursor.getInt(cursor.getColumnIndex(Columns.B))
        val c = cursor.getInt(cursor.getColumnIndex(Columns.C))
        return SumResult(a, b, c)
    }

    public fun clear() {
        context.getContentResolver().delete(URI, null, null)
    }

    interface Columns {
        companion object {
            val A = "a"
            val B = "b"
            val C = "c"
        }
    }

    interface Requests {
        companion object {

            val TABLE_NAME =SumTable::class.java.simpleName

            val CREATION_REQUEST = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    Columns.A + " INTEGER, " +
                    Columns.B + " INTEGER, " +
                    Columns.C + " INTEGER" + ");"

            val DROP_REQUEST = "DROP TABLE IF EXISTS $TABLE_NAME"
        }
    }

}