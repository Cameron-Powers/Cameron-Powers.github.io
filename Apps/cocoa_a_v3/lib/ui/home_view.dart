// import 'dart:collection';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:flutter/material.dart';
import 'dart:async';
import 'package:hexcolor/hexcolor.dart';
import 'register_business.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class HomeView extends StatefulWidget {
  @override
  State<HomeView> createState() => _HomeViewState();
}

class _HomeViewState extends State<HomeView> {
  List<Marker> allMarkers = [];
  GoogleMapController _mapController;
  // List<MarkerId, Marker> allMarkers = <MarkerId, Marker>[];
  static final CameraPosition _ottawa = CameraPosition(
    target: LatLng(45.4215, -75.6972),
    zoom: 13,
  );

  @override
  void initState() {
    super.initState();
    allMarkers.add(Marker(
        markerId: MarkerId('myMarker'),
        draggable: true,
        onTap: () {
          print('Marker Tapped');
        },
        position: LatLng(40.7128, -74.0060)));
  }

  void _onMapCreated(GoogleMapController controller) async {
    _mapController = controller;
    List addressList = [];

    // Adding all addresses to addressList from FireStore
    await FirebaseFirestore.instance
        .collection("businesses")
        .get()
        .then((querySnapshot) {
      querySnapshot.docs.forEach((result) {
        addressList.add(result.data()['address']);
      });
    });
    // print(addressList[1].toString());

    setState(() {
      allMarkers.add(
        // ADD FIREBASE BUSINESSES HERE
        Marker(
          markerId: MarkerId("0"),
          position: LatLng(45.4215, -75.6972),
          infoWindow: InfoWindow(
            title: "Ottawa",
            snippet: "test",
          ),
        ),
      );
    });
  }

  Future<void> _backToOttawa() async {
    _mapController.animateCamera(CameraUpdate.newCameraPosition(_ottawa));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Image.asset('assets/logo2.png', height: 70),
        backgroundColor: HexColor('#8547FF'),
      ),

      // SIDE MENU
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            DrawerHeader(
              child: Image.asset('assets/logo1.png', height: 70),
              decoration: BoxDecoration(
                color: HexColor('#8547FF'),
              ),
            ),
            ListTile(
                title: Text('Add Business'),
                onTap: () async {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => RegisterBusiness()));
                }),
            ListTile(
              title: Text('Settings'),
              onTap: () {
                // Update the state of the app
                Navigator.pop(context);
              },
            ),
            Container(
              alignment: Alignment.bottomCenter,
              padding: EdgeInsets.fromLTRB(0, 500, 0, 32),
              child: Text("Developed by Cameron Powers"),
            )
          ],
        ),
      ),

      // MAP
      body: GoogleMap(
        mapType: MapType.normal,
        initialCameraPosition: _ottawa,
        onMapCreated: _onMapCreated,
        markers: Set.from(allMarkers),
      ),

      // RETURN TO OTTAWA BUTTON - CHANGE TO RETURN TO HOME (User can set home in settings)
      floatingActionButton: FloatingActionButton.extended(
        onPressed: _backToOttawa,
        label: Text('Ottawa'),
        icon: Icon(Icons.home),
        backgroundColor: HexColor('#8547FF'),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.startFloat,
    );
  }
}

// class _HomeViewState extends State<HomeView> {
//   double bitcoin = 0.0;
//   double ethereum = 0.0;
//   double tether = 0.0;

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       body: Container(
//           decoration: BoxDecoration(
//             color: Colors.white,
//           ),
//           width: MediaQuery.of(context).size.width,
//           height: MediaQuery.of(context).size.height,
//           child: Center(
//             child: StreamBuilder(
//                 stream: FirebaseFirestore.instance
//                     .collection('Users')
//                     .doc(FirebaseAuth.instance.currentUser.uid)
//                     .collection('Coins')
//                     .snapshots(),
//                 builder: (BuildContext context,
//                     AsyncSnapshot<QuerySnapshot> snapshot) {
//                   if (!snapshot.hasData) {
//                     return Center(
//                       child: CircularProgressIndicator(),
//                     );
//                   }
//                   return ListView(
//                     children: snapshot.data.docs.map((document) {
//                       return Container(
//                           child: Row(
//                         mainAxisAlignment: MainAxisAlignment.spaceEvenly,
//                         children: [
//                           Text('User Name: ${document.id}'),
//                           Text('Amount Owned: \$${getValue(document.id, document.data()['Amount']).toStringAsFixed(2)}'),
//                         ],
//                       ));
//                     }).toList(),
//                   );
//                 }),
//           )),
//       floatingActionButton: FloatingActionButton(
//         onPressed: () {
//           Navigator.push(
//             context,
//             MaterialPageRoute(builder: (context) => AddView()),
//           );
//         },
//         child: Icon(Icons.add, color: Colors.white),
//         backgroundColor: Colors.blue,
//       ),
//     );
//   }
// }
