import UIKit
import Flutter
import GoogleMaps
// #import “GoogleMaps/GoogleMaps.h”
// #import <Flutter/Flutter.h>

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
  ) -> Bool {
    GMSServices.provideAPIKey("AIzaSyBFh6cecNIvyCM2qLns_0iMVgdAYqm2W1M")
    GeneratedPluginRegistrant.register(with: self)
    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }
}