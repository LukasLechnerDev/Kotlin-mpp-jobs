package sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import api.GithubJobsApi
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

actual class Sample {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Android"
}

class MainActivity : AppCompatActivity(), CoroutineScope {

    val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hello()
        Sample().checkMe()
        setContentView(R.layout.activity_main)
        val textView = findViewById(R.id.textView) as TextView
        textView.text = hello()

        val jobsApi = GithubJobsApi()
        launch(Dispatchers.Main) {
            try {
                val result = withContext(Dispatchers.IO) { jobsApi.fetchJobs() }
                textView.text = result
            } catch (e: Exception) {
                textView.text = e.message
            }
        }

    }
}