package sample

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import components.joblist.JobsListPresenter
import presentation.JobsListView
import androidx.appcompat.app.AppCompatActivity
import components.joblist.model.JobPositionDto
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), JobsListView, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val progressBar: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }
    val textView: TextView by lazy { findViewById<TextView>(R.id.textView)}

    private val presenter by lazy { JobsListPresenter(this) }

    override fun getJobsListSuccess(jobs: List<JobPositionDto>) {
        launch {
            textView.visibility = View.VISIBLE
            textView.text = jobs.joinToString("\n\n")
            progressBar.visibility = View.GONE
        }
    }

    override fun showProgressIndicator(show: Boolean) {
        launch {
            if (show) {
                textView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            } else {
                textView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun showError(error: Throwable) {

    }

    // val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.getJobsList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}