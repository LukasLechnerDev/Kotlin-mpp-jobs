package sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import components.joblist.model.JobPosition

class JobListAdapter: RecyclerView.Adapter<JobListAdapter.ViewHolder>(){

    private val jobList: MutableList<JobPosition> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, null)
            .let {
                ViewHolder(it,
                    it.findViewById(R.id.textViewTitle) as TextView,
                    it.findViewById(R.id.textViewCompany) as TextView,
                    it.findViewById(R.id.textViewLocation) as TextView)
            }
    }

    override fun getItemCount(): Int {
        return jobList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(jobList[position])
    }

    fun updateData(list: List<JobPosition>){
        jobList.clear()
        jobList.addAll(list)
        notifyDataSetChanged()
    }


    inner class ViewHolder(
        view: View,
        private val textViewTitle: TextView,
        private val textViewCompany: TextView,
        private val textViewLocation: TextView): RecyclerView.ViewHolder(view){

        fun bind(jobPosition: JobPosition){
            textViewTitle.text = jobPosition.title
            textViewCompany.text = jobPosition.company
            textViewLocation.text = jobPosition.location
        }
    }
}