//
//  HomeView.swift
//  Firebase Login
//
//  Created by Cameron Powers on 2020-04-15.
//  Copyright © 2020 Cameron Powers. All rights reserved.
//

import SwiftUI

struct HomeView: View {
    @EnvironmentObject var userInfo: UserInfo
    var body: some View {
        NavigationView{
            Text("Logged in as User")
                .navigationBarTitle("Firebase Login")
                .navigationBarItems(trailing: Button("Log Out"){
                    self.userInfo.isUserAuthenticated = .signedOut
                })
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
