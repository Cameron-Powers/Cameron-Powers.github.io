import 'package:cocoa_a_v3/net/flutterfire.dart';
import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'register.dart';

import 'home_view.dart';

class Authentication extends StatefulWidget {
  @override
  _AuthenticationState createState() => _AuthenticationState();
}

class _AuthenticationState extends State<Authentication> {
  TextEditingController _emailField = TextEditingController();
  TextEditingController _passwordField = TextEditingController();
  bool showRegister = false;

  void toggleView() {
    setState(() {
      showRegister = !showRegister;
    });
  }

  @override
  Widget build(BuildContext context) {
    if (showRegister) {
      return Register(toggleView: toggleView);
    } else {
      return Scaffold(
        backgroundColor: HexColor("#EBD39E"),
        body: Container(
          width: MediaQuery.of(context).size.width,
          height: MediaQuery.of(context).size.height,
          padding: EdgeInsets.fromLTRB(20, 0, 20, 80),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                child: Center(
                  child: Image(
                    image: AssetImage('assets/logo3.png'),
                    height: 150,
                  ),
                ),
              ),
              Container(
                child: Center(
                  child: Image(
                    image: AssetImage('assets/logo2.png'),
                    height: 150,
                  ),
                ),
              ),
              Container(
                width: MediaQuery.of(context).size.width / 1.3,
                child: TextFormField(
                  style: TextStyle(color: HexColor('#735240')),
                  controller: _emailField,
                  decoration: InputDecoration(
                    enabledBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                    hintText: 'YourEmail@example.com',
                    hintStyle: TextStyle(color: HexColor('#735240')),
                    labelText: 'Email',
                    labelStyle: TextStyle(color: HexColor('#735240')),
                  ),
                ),
              ),
              SizedBox(height: MediaQuery.of(context).size.height / 35),
              Container(
                width: MediaQuery.of(context).size.width / 1.3,
                child: TextFormField(
                  style: TextStyle(color: HexColor('#735240')),
                  controller: _passwordField,
                  obscureText: true,
                  decoration: InputDecoration(
                    enabledBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                    labelText: 'Password',
                    labelStyle: TextStyle(color: HexColor('#735240')),
                  ),
                ),
              ),
              SizedBox(height: MediaQuery.of(context).size.height / 35),
              Container(
                width: MediaQuery.of(context).size.width / 1.4,
                height: 45,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(15.0),
                  color: HexColor('#8547FF'),
                ),
                child: MaterialButton(
                  onPressed: () async {
                    bool shouldNavigate =
                        await signIn(_emailField.text, _passwordField.text);
                    if (shouldNavigate) {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => HomeView(),
                        ),
                      );
                    }
                  },
                  child: Text(
                    'Login',
                    style: TextStyle(
                      fontSize: 28,
                      fontFamily: 'brightSunshineCap',
                    ),
                  ),
                ),
              ),


              SizedBox(height: MediaQuery.of(context).size.height / 35),
              Container(
                width: MediaQuery.of(context).size.width / 1.4,
                height: 45,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(15.0),
                  color: HexColor('#8547FF'),
                ),
                child: MaterialButton(
                  onPressed: () async {
                    toggleView();
                  },
                  child: Text(
                    "register",
                    style: new TextStyle(
                      fontSize: 28,
                      fontFamily: 'brightSunshineCap',
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      );
    }
  }
}
