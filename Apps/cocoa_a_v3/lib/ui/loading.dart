import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';

class Loading extends StatefulWidget {
  @override
  _LoadingState createState() => _LoadingState();
}

class _LoadingState extends State<Loading> {
  void setupApp() async {
    await Future.delayed(const Duration(seconds: 2), (){});
    // Load Google Map?
    Navigator.pushReplacementNamed(context, '/authentication');
  }

  @override
  void initState() {
    super.initState();
    setupApp();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: HexColor("#EBD39E"),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Container(
            child: Center(
              child: Image(
                image: AssetImage('assets/logo1.png'),
              ),
            ),
          ),
          Container(
            child: Center(
              child: SpinKitThreeBounce(
                color: HexColor('#8547FF'),
                size: 50.0,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
