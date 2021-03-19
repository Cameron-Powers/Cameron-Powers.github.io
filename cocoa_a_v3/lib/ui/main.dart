import 'package:cocoa_a_v3/ui/home_view.dart';
import 'package:cocoa_a_v3/ui/register_business.dart';
import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'authentication.dart';
import 'loading.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: '/',
      title: 'Cocoa',
      // theme: ThemeData(fontFamily: 'brightSunshineCap'),
      routes: {
        '/':(context) => Loading(),
        '/registerBusiness':(context) => RegisterBusiness(),
        '/authentication':(context) => Authentication(),
        '/home':(context) => HomeView(),
      }
    );
  }
}


