import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:cocoa_a_v3/net/flutterfire.dart';
import 'package:multi_select_flutter/multi_select_flutter.dart';
import 'home_view.dart';

class RegisterBusiness extends StatefulWidget {
  @override
  _RegisterBusinessState createState() => _RegisterBusinessState();
}

class BusinessAttributes {
  final int id;
  final String name;

  BusinessAttributes({
    this.id,
    this.name,
  });
}

class BusinessCategories {
  final int id;
  final String name;

  BusinessCategories({
    this.id,
    this.name,
  });
}

class _RegisterBusinessState extends State<RegisterBusiness> {
  TextEditingController _emailField = TextEditingController();
  TextEditingController _phoneField = TextEditingController();
  TextEditingController _businessNameField = TextEditingController();
  TextEditingController _ownerField = TextEditingController();
  TextEditingController _addressField = TextEditingController();

  static List<BusinessAttributes> _businessAttributes = [
    BusinessAttributes(id: 1, name: "Vegan"),
    BusinessAttributes(id: 2, name: "Zero Waste"),
    BusinessAttributes(id: 3, name: "Ethical Sourcing"),
    BusinessAttributes(id: 4, name: "Waste Reduction"),
    BusinessAttributes(id: 5, name: "Cruelty Free"),
    BusinessAttributes(id: 6, name: "Fair Employment"),
    BusinessAttributes(id: 7, name: "Organic"),
    BusinessAttributes(id: 8, name: "Farm to Table"),
    BusinessAttributes(id: 9, name: "Locally Sourced"),
  ];

  static List<BusinessCategories> _businessCategories = [
    BusinessCategories(id: 1, name: "Grocery"),
    BusinessCategories(id: 2, name: "Cafe"),
    BusinessCategories(id: 3, name: "Local Product Dristributor"),
    BusinessCategories(id: 4, name: "Resturant"),
    BusinessCategories(id: 5, name: "Consumer Discretionary"),
    BusinessCategories(id: 6, name: "Farm"),
    BusinessCategories(id: 7, name: "Brewery"),
    BusinessCategories(id: 8, name: "Tourism"),
    BusinessCategories(id: 9, name: "Specialty"),
    BusinessCategories(id: 10, name: "Bookstore"),
    BusinessCategories(id: 11, name: "Finance"),
    BusinessCategories(id: 12, name: "Real Estate"),
    BusinessCategories(id: 13, name: "Service"),
    BusinessCategories(id: 14, name: "Beauty"),
    BusinessCategories(id: 15, name: "Entertainment"),
    BusinessCategories(id: 16, name: "Non-Profit"),
    BusinessCategories(id: 17, name: "Retail"),
    BusinessCategories(id: 18, name: "Food and Drink"),
    BusinessCategories(id: 19, name: "Health and Wellness"),
    BusinessCategories(id: 20, name: "Jewellery"),
    BusinessCategories(id: 21, name: "Recreation"),
    BusinessCategories(id: 22, name: "Consulting"),
    BusinessCategories(id: 23, name: "Information Technology"),
    BusinessCategories(id: 24, name: "Marketing"),
    BusinessCategories(id: 25, name: "Photography & Videography"),
    BusinessCategories(id: 26, name: "Dining Service"),
    BusinessCategories(id: 27, name: "E-Commerce"),
  ];

  final _items = _businessAttributes
      .map((attribute) =>
          MultiSelectItem<BusinessAttributes>(attribute, attribute.name))
      .toList();
  List<BusinessAttributes> _selectedAttributes = [];

  final _categories = _businessCategories
      .map((category) =>
          MultiSelectItem<BusinessCategories>(category, category.name))
      .toList();
  List<BusinessCategories> _selectedCategories = [];

  @override
  void initState() {
    _selectedCategories = _businessCategories;
    _selectedAttributes = _businessAttributes;
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: HexColor("#EBD39E"),
      body: Container(
        width: MediaQuery.of(context).size.width,
        height: MediaQuery.of(context).size.height,
        padding: EdgeInsets.fromLTRB(20, 0, 20, 0),
        child: ListView(
          children: [
            Container(
              child: Center(
                child: Image(
                  image: AssetImage('assets/logo3.png'),
                  height: 150,
                ),
              ),
            ),

            // BUSINESS NAME
            Container(
              width: MediaQuery.of(context).size.width / 1.3,
              child: TextFormField(
                style: TextStyle(color: HexColor('#000000')),
                controller: _businessNameField,
                decoration: InputDecoration(
                  enabledBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  labelText: 'Business Name',
                  labelStyle: TextStyle(color: HexColor('#000000')),
                ),
              ),
            ),

            // OWNER
            Container(
              width: MediaQuery.of(context).size.width / 1.3,
              child: TextFormField(
                style: TextStyle(color: HexColor('#000000')),
                controller: _ownerField,
                decoration: InputDecoration(
                  enabledBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  labelText: 'Owner',
                  labelStyle: TextStyle(color: HexColor('#000000')),
                ),
              ),
            ),

            // ADDRESS
            Container(
              width: MediaQuery.of(context).size.width / 1.3,
              child: TextFormField(
                style: TextStyle(color: HexColor('#000000')),
                controller: _addressField,
                decoration: InputDecoration(
                  enabledBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  labelText: 'Address',
                  labelStyle: TextStyle(color: HexColor('#000000')),
                ),
              ),
            ),

            // EMAIL
            Container(
              width: MediaQuery.of(context).size.width / 1.3,
              child: TextFormField(
                style: TextStyle(color: HexColor('#000000')),
                controller: _emailField,
                decoration: InputDecoration(
                  enabledBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  hintText: 'YourEmail@Example.com',
                  hintStyle: TextStyle(color: HexColor('#000000')),
                  labelText: 'Email',
                  labelStyle: TextStyle(color: HexColor('#000000')),
                ),
              ),
            ),

            // PHONE NUMBER
            Container(
              width: MediaQuery.of(context).size.width / 1.3,
              child: TextFormField(
                style: TextStyle(color: HexColor('#000000')),
                controller: _phoneField,
                decoration: InputDecoration(
                  enabledBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: HexColor('#8547FF')),
                  ),
                  labelText: 'Phone Number',
                  labelStyle: TextStyle(color: HexColor('#000000')),
                ),
              ),
            ),

            // BUSINESS ATTRIBUTES
            SizedBox(height: MediaQuery.of(context).size.height / 80),
            Container(
              width: MediaQuery.of(context).size.width / 1.3,
              child: MultiSelectDialogField(
                items: _items,
                title: Text("Business Attributes"),
                selectedColor: HexColor('#8547FF'),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                  border: Border.all(
                    color: HexColor('#8547FF'),
                    width: 1,
                  ),
                ),
                buttonText: Text(
                  "Business Attributes",
                  style: TextStyle(
                    color: HexColor('#000000'),
                    fontSize: 16,
                  ),
                ),
                onConfirm: (results) {
                  _selectedAttributes = results;
                },
              ),
            ),

            // BUESINESS CATEGORIES
            SizedBox(height: MediaQuery.of(context).size.height / 80),
            Container(
              width: MediaQuery.of(context).size.width / 1.3,
              child: MultiSelectDialogField(
                items: _categories,
                title: Text("Business Categories"),
                selectedColor: HexColor('#8547FF'),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                  border: Border.all(
                    color: HexColor('#8547FF'),
                    width: 1,
                  ),
                ),
                buttonText: Text(
                  "Business Categories",
                  style: TextStyle(
                    color: HexColor('#000000'),
                    fontSize: 16,
                  ),
                ),
                onConfirm: (results) {
                  _selectedCategories = results;
                },
              ),
            ),

            // REGISTER BUTTON
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
                  List categories = [];
                  List attributes = [];

                  for(var i = 0; i < _selectedCategories.length; i++){
                    categories.add(_selectedCategories[i].name);
                  }
                  for(var i = 0; i < _selectedAttributes.length; i++){
                    attributes.add(_selectedAttributes[i].name);
                  }
                  bool shouldNavigate = await registerBusiness(
                      _businessNameField.text,
                      _ownerField.text,
                      _addressField.text,
                      _emailField.text,
                      _phoneField.text,
                      attributes,
                      categories,
                      );
                  if (shouldNavigate) {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => RegisterBusiness(),
                      ),
                    );
                  }
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

            // BACK BUTTON
            SizedBox(height: MediaQuery.of(context).size.height / 35),
            Container(
              width: MediaQuery.of(context).size.width / 1.4,
              // alignment: Alignment.topLeft,
                height: 45,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(15.0),
                  color: HexColor('#8547FF'),
                ),
              child: MaterialButton(
                onPressed: () {
                      Navigator.push(context,
                        MaterialPageRoute(
                          builder: (context) => HomeView(),
                        ),
                      );
                  },
                  child: Text(
                    'Back',
                    style: TextStyle(
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
