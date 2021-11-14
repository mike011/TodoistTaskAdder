//
//  LoginViewController.swift
//  TableViewWithREST
//
//  Created by Michael Charland on 2019-08-30.
//  Copyright © 2019 charland. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

    weak var delegate: LoginViewDelegate?

    @IBAction func pocketAuthenticate() {
        delegate?.didTapPocketAuthenticateButton()
    }
}
