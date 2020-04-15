//
//  ContentView.swift
//  Firebase Login
//
//  Created by Cameron Powers on 2020-04-15.
//  Copyright © 2020 Cameron Powers. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    @EnvironmentObject var userInfo: UserInfo
    var body: some View {
        Group {
            if userInfo.isUserAuthenticated == .undefined {
                Text("Loading...")
            } else if userInfo.isUserAuthenticated == .signedOut {
                LoginView()
            } else {
                HomeView()
            }
        }
        .onAppear {
            self.userInfo.configureFirebaseStateDidChange()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
