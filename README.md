# CIS399AndroidDemos
Demos of Android apps for CIS 399 at the University of Oregon. Apps were written in Java using Android Studio.

Note: All these apps are in one repository, so you will
need to download or clone the whole repository even if you
only want code for one app.

## Directory

* RockPaperScissors-SingleActivity
  * A game with one activity and:
    * a ConstraintLayout
    * putting logic code in a separate class
    * setting an onClick handler using an XML widget attribute

* RockPaperScissors-SingleActivity-Menu+Settings
  * A game with one activity and:
    * a menu in the app bar
    * a settings activity and xml layout with a CheckBoxPreference

* RockPaperScissors-SingleActivity-MultiLayout
  * A game with a single activity and:
    * layouts for both screen orientations:
      * portrait
      * landscape
    * Activity state is saved in a Bundle using the onSaveInstanceState callback method.

* Multi-ActivityDemo
  * A very simple app that demonstrates starting a second activity

* RockPaperScissors-MultiActivity
  * An RPS game with two activities
    * Uses radio buttons for player's hand choice
    * The second activity has an app bar with an up button
    * The player's hand choice is sent to the second activity in an intent

* SimpleFragmentDemo
  * A simple app that has two activities and two fragments.
    * In portrait orientation each fragment is loaded into a separate activity.
    * In landscape orientation both fragments are loaded in one activity.
  * The state of the first activity is saved during rotation, the second activity has configuration change disabled.
  * When a button on the first fragment is clicked, a message is sent to the second fragment.

* RockPaperScssors-Fragments
  * A game with two activities and two fragments which are loaded differently
    depending on screen size and orientation. Activities have a Toolbar and the
    second activity has an up button on the Toolbar.

* AsyncDemo
  * A simple demo of running a task on a non-UI thread using AsyncTask.

* DatePickerDemo
  * A simple app with just a DatePicker widget and a TextView that shows the date selected. The Calendar class is used to get the current date and the SimpleDateFormat class is used to format the date obtained from the picker.

* WeatherForecast-SAX+ListView
  * Parses an XML file of weather data using SAX and displays weather icons in the ListView along with the weather data. The data is from an Open Web Map API

* WeatherForecast-SAX+SQLite+ListView
  * Uses a database to store multiple weather forecasts in an SQLite database. It also uses a CursorAdapter with the ListView.

* WeatherForecast-SAX+SQLite+REST+ListView
  * Pulls forecast data from the Weather Underground REST service
  * Parse the XML forecast data using SAX
  * Stores the forecasts in a SQLite database 
  * Uses a CursorAdapter to display a forecast in a ListView.

* WeatherForecast-kSOAP+SQLite+ListView
  * Similar to WeatherForecast-SAX+SQLite+ListView, but it gets it's weather forecast data from a SOAP web service instead of from XML files. It uses kSOAP as the web service client. 7/12/16: Unfortunately, the cdyne weather web service used by this app is defunct!

* kSoapWaterTempDemo
  * A very simple app that demonstrates using the kSoap2-Android library to read from a web service. It reads the ocean temperature from a NOAA SOAP web service.
* GeoDistanceCalcuator
  * Uses the Fused Location Provider (Google Play Services) to get the current latitude and longitude.
  * Use an Android Geocoder object to convert latitude and longitude to an address and vice-versa.
  * Uses Android Location objects to calculate the distance between the two locations.

---------------------------

I wrote these apps for CIS 399, Android Mobile Application Development, a course I teach at the University of Oregon.

Read about Android programming on my blog:
https://birdsbits.wordpress.com/tag/android/
