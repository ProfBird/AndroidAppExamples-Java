A simple app that has two activities and two fragments. It has different behaviors depending on the orientation.
* In portrait orientation:
  * Each fragment is loaded into a separate activity
* In landscape orientation:
  *  Both fragments are loaded in one activity
* Handling state change during rotation
  * In the first fragment, state is saved in the first fragment using savedInstanceState   
  * In the second fragment, automatic configuration changes are disabled (in the manifest) and calls to onConfigurationChanged are enabled.
* When a button on the first fragment is clicked, a message is sent to the second fragment. This works in both orientations.


Note: This app is just one app in the class demo repository. To get the source code for this app you will need to clone or download the whole repository, https://github.com/UO-CIS/CIS399AndroidDemos.

This demo was written for CIS399, Android App Development, at the University of Oregon.

Read more about android programming on my blog, BirdsBits: https://birdsbits.wordpress.com
