import UIKit
import main

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, JobsListView {
    
    @IBOutlet var jobsTableView: UITableView!
    
    private var jobs: [JobPosition] = []
    private lazy var presenter = JobsListPresenter(view: self)
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return jobs.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let jobCell = tableView.dequeueReusableCell(withIdentifier: "jobcell", for: indexPath) as! JobTableViewCell
        let index: Int = indexPath.row
        
        jobCell.jobTitle?.text = jobs[index].title
        jobCell.jobCompany?.text = jobs[index].company
        jobCell.jobLocation?.text = jobs[index].location
        
        return jobCell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 65
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.getJobsList()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func render(uiState: UiState) {
        if(uiState is UiState.Success){
            let state = uiState as! UiState.Success
            displayJobList(jobs: state.data as! [JobPosition])
        }
        if(uiState is UiState.Loading){
            displayProgress()
        }
        if(uiState is UiState.Error){
            let state = uiState as! UiState.Error
            displayError(error: state.throwable as! KotlinThrowable)
        }
    }
    
    private func displayJobList(jobs: [JobPosition]) {
        print("getJobsListSuccess with \(jobs.count) jobs called")
        self.jobs = jobs
        jobsTableView.reloadData()
    }
    
    private func displayProgress() {
       print("showProgressIndicator called")
    }
    
    func displayError(error: KotlinThrowable) {
        print("showError called: \(error)")
    }
}
