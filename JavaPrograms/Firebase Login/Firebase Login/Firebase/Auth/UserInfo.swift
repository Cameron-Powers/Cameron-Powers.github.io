import Foundation
import FirebaseAuth

class UserInfo: ObservableObject{
    enum FBAuthState {
        case undefined, signedOut, signedIn
    }
    @Published var isUserAuthenticated: FBAuthState = .undefined
    @Published var user: FBUser = .init(uid: "", name: "", email: "")
    
    var authStateDidChangeListenerHandle: AuthStateDidChangeListenerHandle?
    
    func configureFirebaseStateDidChange(){
        authStateDidChangeListenerHandle = Auth.auth().addState
    }
}
