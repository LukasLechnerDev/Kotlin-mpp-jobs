//
//  JobTableViewCell.swift
//  iosApp
//
//  Created by Lukas Lechner on 31.03.19.
//

import UIKit

class JobTableViewCell: UITableViewCell {

    @IBOutlet var jobTitle: UILabel!
    @IBOutlet var jobCompany: UILabel!
    @IBOutlet var jobLocation: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
