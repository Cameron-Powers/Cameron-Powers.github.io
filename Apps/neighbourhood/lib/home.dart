import 'package:flutter/material.dart';
import 'store.dart';
import 'shoppingList.dart';
import 'account.dart';
import 'map.dart';

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  int _currentIndex = 0;
  var tabs = [
    Map(),
    Store(),
    ShoppingList(),
    Account(),
  ];

  void onTabTapped(int index) {
    setState(() {
      _currentIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Expanded(
          child: tabs.elementAt(_currentIndex),
        ),
        BottomNavigationBar(
          type: BottomNavigationBarType.fixed,
          currentIndex: _currentIndex,
          onTap: (int index) {
            setState(() {
              _currentIndex = index;
            });
          },
          items: [
            BottomNavigationBarItem(
              icon: Icon(Icons.map),
              label: 'Map',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.store),
              label: 'Store',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.list),
              label: 'Shop List',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.account_circle),
              label: 'Account',
            ),
          ],
        ),
      ],
    ));
  }
  // FOR TRACING EVENTS
  // _perfTrace() async {
  //   Trace trace = FirebasePerformance.instance.newTrace('testTrace');
  //   trace.start();
  //   await Future.delayed(Duration(seconds: 5));
  //   trace.stop();
  // }

  // FOR THROWING CRASH ERRORS
  // _crash() {
  //   throw FlutterError('Test Error');
  // }
}
