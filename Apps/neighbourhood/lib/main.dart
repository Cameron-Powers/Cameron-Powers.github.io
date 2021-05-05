
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
// import 'package:firebase_analytics/firebase_analytics.dart';
// import 'package:firebase_performance/firebase_performance.dart';
import 'package:firebase_crashlytics/firebase_crashlytics.dart';
import 'package:neighbourhood/Services/authentication.dart';
import 'package:neighbourhood/SignInPage.dart';
import 'package:provider/provider.dart';
import 'dart:async';
import 'home.dart';
import 'store.dart';
import 'coupons.dart';
import 'shoppingList.dart';
import 'account.dart';
import 'map.dart';

// void main() async {
//   WidgetsFlutterBinding.ensureInitialized();
//   await Firebase.initializeApp();
//   // FirebaseAnalytics analytics = FirebaseAnalytics();
//   // FirebaseCrashlytics.instance.setCrashlyticsCollectionEnabled(true);
//   runZonedGuarded(() {
//     runApp(MaterialApp(
//       title:'Neighbourhood',
//       home: AuthenticationWrapper(),
//       initialRoute: '/home',
//       routes: {
//         '/home': (context) => Home(),
//         '/map': (context) => Map(),
//         '/store': (context) => Store(),
//         '/coupons': (context) => Coupons(),
//         '/shoppingList': (context) => ShoppingList(),
//         '/account': (context) => Account(),
//       },
//     ));
//   }, (error, stackTrace) {
//     FirebaseCrashlytics.instance.recordError(error, stackTrace);
//   });
// }

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(Neighbourhood());
}

class Neighbourhood extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        Provider<AuthenticationService>(
          create: (_) => AuthenticationService(FirebaseAuth.instance),
    ),
      StreamProvider(
        create: (context) => context.read<AuthenticationService>().authStateChanges,
      )
    ],
    child: MaterialApp(
      title: 'Neighbourhood', 
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: AuthenticationWrapper(),
    )
    );
  }
}

class AuthenticationWrapper extends StatelessWidget {
  const AuthenticationWrapper({Key key}):super(key: key);
  @override
  Widget build(BuildContext context) {
    final firebaseUser = context.watch<User>();
    if(firebaseUser != null){
      return Home();
    }
    return SignInPage();
  }
}