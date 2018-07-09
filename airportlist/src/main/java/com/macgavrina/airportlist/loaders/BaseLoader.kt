package com.macgavrina.airportlist.loaders

import android.content.Context
import android.database.Cursor
import android.support.v4.content.Loader

open class BaseLoader(context: Context) : Loader<Cursor>(context) {

    private var mCursor: Cursor? = null

    override fun deliverResult(cursor: Cursor?) {
        if (isReset()) {
            if (cursor != null) {
                cursor!!.close()
            }
            return
        }
        val oldCursor = mCursor
        mCursor = cursor

        if (isStarted()) {
            super.deliverResult(cursor)
        }

        if (oldCursor != null && oldCursor !== cursor && !oldCursor!!.isClosed()) {
            oldCursor!!.close()
        }
    }

    override protected fun onStartLoading() {
        if (mCursor != null) {
            deliverResult(mCursor)
        } else {
            forceLoad()
        }
    }

    override protected fun onReset() {
        if (mCursor != null && !mCursor!!.isClosed()) {
            mCursor!!.close()
        }
        mCursor = null
    }

}
