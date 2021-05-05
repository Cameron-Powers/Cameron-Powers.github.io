import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class Map extends StatefulWidget {
  @override
  _MapState createState() => _MapState();
}

class _MapState extends State<Map> {
  // GoogleMapController _mapController;
  static final CameraPosition _ottawa =
      CameraPosition(target: LatLng(45.4215, -75.6972), zoom: 14);

  @override
  Widget build(BuildContext context) {
    return GoogleMap(
      mapType: MapType.normal,
      initialCameraPosition: _ottawa,
      // onMapCreated: _onMapCreated,
    );
  }

  // void _onMapCreated(GoogleMapController controller) async {
  //   _mapController = controller;
  // }

  // void _backToOttawa() {
  //   _mapController.animateCamera(CameraUpdate.newCameraPosition(_ottawa));
  // }
}

//     floatingActionButton: FloatingActionButton.extended(
//       onPressed: _backToOttawa,
//       label: Text('Ottawa'),
//       icon: Icon(Icons.home),
//       backgroundColor: HexColor('#8547FF'),
//     ),
