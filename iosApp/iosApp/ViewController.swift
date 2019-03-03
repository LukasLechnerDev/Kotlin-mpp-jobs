import UIKit
import main

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        // label.text = Proxy().proxyHello() + "123"
        label.text = "my first ios shit"
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    @IBOutlet weak var label: UILabel!
}
