package sample

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import components.joblist.JobsListPresenter
import presentation.JobsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import common.UiState
import components.joblist.model.JobPosition
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), JobsListView, CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Main

    private val progressBar: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private val adapter = JobListAdapter()
    private val presenter by lazy { JobsListPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = Job()
        presenter.getJobsList()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun render(uiState: UiState<List<JobPosition>>) {
        when (uiState) {
            is UiState.Success -> {
                displayJobList(uiState.data)
            }
            is UiState.Loading -> {
                displayProgress()
            }
            is UiState.Error -> {
                displayError(uiState.throwable)
            }
        }
    }

    private fun displayJobList(jobs: List<JobPosition>) {
        launch {
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            adapter.updateData(jobs)
            progressBar.visibility = View.GONE
        }
    }

    private fun displayProgress() {
        launch {
                recyclerView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
        }
    }

    private fun displayError(error: Throwable?) {
        // show error screen
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        presenter.onDestroy()
    }
}