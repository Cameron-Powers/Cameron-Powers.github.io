import 'package:http/http.dart';
import 'dart:convert';
import 'package:intl/intl.dart';

class WorldTime {

  String location; //location name for UI
  String time; //time in a location
  String flag; //url to asset flag icon
  String url; //location url for api endpoint
  bool isDaytime; //true if day, false if night

  WorldTime({this.location, this.flag, this.url});

  Future<void> getTime() async {

    try{
      //Making the request ***API is broken?
      Response response = await get('http://worldtimeapi.org/api/timezone/$url');
      Map data = jsonDecode(response.body);

      //Getting properties from the data
      String datetime = data['datetime'];
      String offset = data['utc_offset'].substring(1,3);

      //Create datetime object
      DateTime now = DateTime.parse(datetime);
      now.add(Duration(hours: int.parse(offset)));

      //Set time property
      isDaytime = now.hour > 6 && now.hour < 20 ? true : false;
      time = DateFormat.jm().format(now);
    }
    catch(e){
      print('Caught error: $e');
      time = 'could not get time data';
    }
  }
}

//Example instance
// WorldTime instance = WorldTime(location: 'Berlin', flag: 'germany.png', url: '/Europe/Berlin');
// instance.getTime()