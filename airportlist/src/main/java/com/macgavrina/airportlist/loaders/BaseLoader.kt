import android.content.Context
import android.database.Cursor
import android.support.v4.content.AsyncTaskLoader
import java.io.IOException
import android.os.AsyncTask.execute
import com.macgavrina.airportlist.data.Airport
import com.macgavrina.airportlist.database.SumTable
import com.macgavrina.airportlist.services.SumService
import com.macgavrina.airportlist.services.SumService.ApiFactory



abstract class BaseLoader(context: Context) : AsyncTaskLoader<Cursor>(context) {

    protected override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): Cursor? {
        try {
            return apiCall()
        } catch (e: IOException) {
            return null
        }

    }

    @Throws(IOException::class)
    protected fun apiCall(): Cursor? {
        val service = SumService.create()
        val call = service.performPostCallWithQuery(0, 0)
        val sumResult = call.execute().body()
        val sumTable = SumTable(context)
        if (sumResult != null) {
            sumTable.save(sumResult)
        }
        return context.contentResolver.query(sumTable.URI, null, null, null, null)
    }
}