package sample

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import api.GithubJobsApi
import components.joblist.JobsListPresenter
import components.joblist.model.JobPositionDto
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import presentation.JobsListView
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), JobsListView {

    val progressBar: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }

    private val repository by lazy { (application as MppJobsApplication).jobPositionsRepository }
    private val presenter by lazy { JobsListPresenter(Dispatchers.Main, this, repository) }

    override fun getJobsListSuccess(jobs: List<JobPositionDto>) {
        textView.visibility = View.VISIBLE
        textView.text = jobs.joinToString("\n\n")
        progressBar.visibility = View.GONE
    }

    override fun showProgressIndicator(show: Boolean) {
        if (show) {
            textView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        } else {
            textView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
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