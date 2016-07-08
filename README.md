# CIS399AndroidDemos
Demos of Android apps for CIS 399 at the University of Oregon. Apps were created using Android Studio.

Note: All these apps are in one repository, so you will
need to download or clone the whole repository even if you
only want code for one app.

##Directory

* RockPaperScissors-SingleActivity
  * A game with one activity. It demonstrates constructing a relative layout,
  and adding app logic in a separate class.
* Multi-ActivityDemo
  * A very simple app that demonstrates starting a second activity
* RockPaperScissors-MultiActivity
   * A game with two activities. The second activity has a Toolbar with an up button.
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
* XMLParsePractice
  * An app that demonstrates parsing XML using SAX. The assets folder contains an XML weather forecast file that is parsed and displayed in a ListView
* WeatherForecast-SAX+ListView
  * Similar to the XMLParsePractice app, but it displays weather icons in the ListView along with the weather data.
* WeatherForecast-SAX+SQLite+ListView
  * Similar to XMLParsePractice, except it stores multiple weather forecasts in an SQLite database. It also uses a CursorAdapter with the ListView.
* WeatherForecast-kSOAP+SQLite+ListView
  * Similar to WeatherForecast-SAX+SQLite+ListView, but it gets it's weather forecast data from a SOAP web service instead of from XML files. It uses kSOAP as the web service client.

I wrote these apps for use with a class I teach at University of Oregon, CIS 399, Android Mobile Application Development.

Read my blog, BirdsBits:
https://birdsbits.wordpress.com
