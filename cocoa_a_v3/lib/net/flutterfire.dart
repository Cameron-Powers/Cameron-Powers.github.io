import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';

Future<bool> signIn(String email, String password) async {
  try {
    await FirebaseAuth.instance
        .signInWithEmailAndPassword(email: email, password: password);
    return true;
  } catch (e) {
    print(e);
    return false;
  }
}

Future<bool> register(String email, String password, String fname, String lname,
    String phoneNumber, String gender) async {
  try {
    await FirebaseAuth.instance
        .createUserWithEmailAndPassword(email: email, password: password);
    // ignore: deprecated_member_use
    await Firestore.instance.collection('users').document().setData(
        {'fname': fname, 'lname': lname, 'phoneNumber': phoneNumber, 'gender': gender, 'email':email});
    return true;
  } on FirebaseAuthException catch (e) {
    if (e.code == 'weak-passord') {
      print('The password you entered is too weak');
    } else if (e.code == 'email-already-in-use') {
      print('An account already exists for this email');
    }
    return false;
  } catch (e) {
    print(e.toString());
    return false;
  }
}

Future<bool> registerBusiness(String businessName, String owner, String address, String email, String phoneNumber, List businessAttributes, List businessCategories) async {
  try{
    FirebaseFirestore.instance.collection('businesses').add({
    'businessName': businessName, 
    'owner': owner, 
    'address': address, 
    'email': email,
    'phoneNumber': phoneNumber,
    'businessAttributes': businessAttributes,
    'businessCategories': businessCategories,
    });
  } catch (e) {
    return false;
  }
  return true;
}
