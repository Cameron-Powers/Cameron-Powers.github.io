import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'services/authentication.dart';

class Account extends StatefulWidget {
  @override
  _AccountState createState() => _AccountState();
}

class _AccountState extends State<Account> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Center(
      child: Column(
        children: [
          Text('Account'),
          ElevatedButton(
            onPressed: () {
              context.read<AuthenticationService>().signOut();
            },
            child: Text('Sign Out'),
          ),
        ],
      ),
    ));
  }
}
