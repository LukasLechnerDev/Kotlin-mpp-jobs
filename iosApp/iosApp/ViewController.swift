import UIKit
import main

class ViewController: UIViewController, JobsListView {
    
    @IBOutlet weak var label: UILabel!
    
    private lazy var presenter = JobsListPresenter(view: self)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.getJobsList()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func showProgressIndicator(show: Bool) {
        label.text = "loading"
    }
    
    func showError(error: KotlinThrowable) {
        label.text = "error"
    }
    
    func getJobsListSuccess(jobs: [JobPositionDto]) {
        label.text = "Success"
        print(jobs[0].company)
    }
}
