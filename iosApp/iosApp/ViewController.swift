import UIKit
import main


class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        label.text = GithubJobsApi().fetchJobsSync()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    @IBOutlet weak var label: UILabel!
}
