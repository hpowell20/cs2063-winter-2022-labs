# Introduction to Android Studio

This lab will introduce the Android Studio IDE and Android project structure through the creation of a simple 'Hello World!' application that will run on emulated or physical devices.

#### Objectives

1. Familiarize IDE navigation
2. Introduce running apps on physical and emulated devices
3. Introduce layout editor
4. Project structure breakdown and highlights
5. Console log for debugging

#### Procedure

###### Create a new project

* Open Android Studio
* → Select the _Create New Project_ option
* → Select Empty Activity and click **Next**
* → For the project details add the following:
	* Name: Lab 1 Hello World
	* Package name: ca.unb.mobiledev.lab1
	* Language: Java
	* Minimum API Level: API 23: Android 6.0 (Marshmallow)
* → Click **Finish**

NOTES:

* The minimum API level specifies which version of the Software Development Kit (SDK) will be used to compile the app against
* Android Studio will use this information to provide accurate auto-complete information in addition to providing warnings when your code is not appropriately targeting the set minimum API level
* While you should target the most current version of the SDK setting a minimum SDK allows you to get backwards compatibility with devices running older versions
	* This allows for your application to run on a larger percentage of devices.

###### Start the Project

With the project created start it for the first time using the auto generated code.  We will make a few changes later on; the intention here is to ensure you can run the code.  Let's try this now using either a device and/or and emulator.

_Using a Device_

In order to connect to the device __Developer Mode__ needs to be enabled which allows for USB connection and debugging.

1. Navigate to your device Settings menu
2. Find About Device
3. Locate the Build number and click it 7 times
	* NOTE: The location of the build number and number of clicks may vary between devices; you may need to look up the device instructions
4. You will receive notice that developer mode has been unlocked
5. Navigate back one menu to About Device to access the new Developer options menu
6. Turn on USB debugging
7. With Android Studio open attach via USB cable an Android device to your computer
8. On your device press **OK** to allow USB debugging
9. Run the application by clicking Run → Run 'app'
	* Ensure your device is selected and click **OK**

_Using an Emulator_

Android Studio comes equipped with built-in device emulators and options for emulating real world devices for testing the applications you develop, to ensure functionality is as you expect it across a range of device types.  

1. Locate the Run menu along the File menu bar along the top of Android Studio and click Run ‘app’
	* You might need to click Stop first
	* You can also run the emulator by clicking the familiar green Play button along the icon toolbar
2. Click on the __Create Virtual Device__ button
3. Create the type of virtual device you would like to use
 	* You may also need to download the appropriate API runtime level
4. Ensure your emulated device is selected and click **OK**

NOTE:
* These can be managed using the Android Virtual Machine (AVD) Manager at any time

**Deliverable 1**

Take a screenshot of your app running (on any device or emulator).

###### Layout Editor

1. If you’ve navigated away from the layout editor, click the Project tab along the far left side of Android Studio, navigate to and double click res/layout/_activity_main.xml_ in the project structure.
2. Click the ‘Hello world!’ text in the application window preview. The text field, which is an Android object called TextView that we’ll learn more about later, is highlighted.
	* Take note of the _Attributes_ window that populates to the right
	* This toolbar shows the Android View objects that can be customized.
3. Explore the options available, and customize this TextView
4. Change the font size, text colour, background colour, etc. Move the TextView around inside the app
5. Try selecting a different device for the layout editor; does the layout still look like you want it to?
	* Note that font sizes should always be specified in 'sp', a scale-independent pixel
	* This is a convenient way to size fonts in Android that will ensure it stays looking good on a variety of device screens
	* For other objects you will want 'px' or density-independent pixels 'dp'
	* For more information read the [Dimension descriptions in the Android API Guide](http://developer.android.com/guide/topics/resources/more-resources.html#Dimension)


###### Project Structure

We will now take a look at what every Android Studio project structure has in common to develop an understanding of how this folder structure is navigated and how each piece ties together.  The structure of the application is shown below.

```
→ app
	→ manifests
		→ AndroidManifest.xml
	→ java
		→ mobiledev.unb.ca.lab1
			→ MainActivity.java
		→ mobiledev.unb.ca.lab1 (androidTest)
		→ mobiledev.unb.ca.lab1 (test)
	→ res
		→ drawable
			→ ic_launcher_background.xml
			→ ic_launcher_foreground.xml
		→ layout
			→ activity_main.xml
		→ mipmap
			→ ic_launcher
			→ ic_launcher_round
		→ values
			→ colors.xml
			→ strings.xml
			→ themes
				→ themes.xml
				→ themes.xml (night)
→ Gradle Scripts
```

More information about the project structure can be found at [ProjectFiles](https://developer.android.com/studio/projects/index.html#ProjectFiles)


###### manifests Directory

This directory contains the AndroidManifest.xml file. This file contains information that pertains to how your application should run, what activities within your project it depends on, and of those which is to run when the application is first launched, as well as any device/account access permissions users must agree to before being able to download your application if placed on the Google Play Store.

Additional information on the  AndroidManifest.xml can be found [here](https://developer.android.com/guide/topics/manifest/manifest-intro.html)

**Deliverable 2**

The application theme is also described in this AndroidManifest file; provide the attribute tag that describes this.

NOTE:
* The _*android:label*_ attribute tag contains the application name we gave to our project
* If you click this an @string resource named app_name will be revealed in as being the source of this text
	* We’ll explore the strings.xml resource below

###### java Directory

This java folder contains a sub-directory for your application code ```mobiledev.unb.ca.lab1``` and sub-directories for testing. We'll focus on the non-test folder for now.

As no additional content has been added to the project the only class file is the **MainActivity** file.  This is the first activity to run when the application is launched.  Looking at the code in the file will reveal some familiar Java syntax and perhaps some unfamiliar Java syntax as well.
	* A class named MainActivity is declared and the file contains some imports and the package under which this file belongs is denoted
		* The ```@Override``` annotation indicates that the subsequent method is being overwritten against the super methods from the extended Activity class
	* There is an ```onCreate()``` method which takes a ```Bundle``` parameter which is passed up to the superclass from which we are overriding
* Look through the [Activity documentation](http://developer.android.com/reference/android/app/Activity.html) to find the method ```setContentView()``` that is used inside the ```onCreate()``` method
	* Be sure to find the method which takes the proper parameter type; in this case a layout resource ID

**Deliverable 3**

Describe what the ```setContentView()``` method call accomplishes for your application.

###### res directory

This directory contains many different types of resources that get used inside an application.

* The drawable folder can contain custom image files that can be utilized in your application to add finely customized theming or object styles, icons or any other visual assets necessary for your application.

* The layout directory contains the  _activity_main.xml_ file along with other resource files.
	* The _activity_main.xml_ is the resource that is sent as a parameter to the ```setContentView()``` method call in our MainActivity.java class file discussed above
		* Open this file to once again reveal the GUI layout editor
		* Near the bottom left corner of this layout editor window, switch from the Design tab to the Text tab
		* You’ll be presented with a hierarchy of XML tags each containing attributes relevant to defining the layout for the application you created in the GUI editor
		* Layouts can be designed in either fashion, by GUI or XML. The menu directory contains a similar file for customizing the layout for menu appearances in your application
		* More details can be found [here](http://developer.android.com/guide/topics/ui/declaring-layout.html)

**Deliverable 4**

1. What are the three common layout types?  
2. Why is it important to define IDs for view objects especially when using RelativeLayout?

* The mipmap directory contains icons used for launch deck icons for the application
	* Note how multiple files are supplied; this is to target devices with differing levels of pixel density screens.

* Under the values directory there first is a _colors.xml_ file
 	* Initially this file contains the base colors for your application
	* These are used to simply create a cohesive color scheme experience across the application by allowing the Android OS to allow existing Android View Object components to rely on these three initial colors for coloring common application components (```TaskBars```, etc.)

* Labels used throughout the application can be defined in the _strings.xml_ file
	* Open strings.xml and you’ll see an XML tags containing the application name
	* String can be referenced in multiple locations within an application and only require being altered in one location to propagate changes throughout the app
	* New strings can be defined here and then referenced using the ```@string/string_name``` syntax we saw earlier
	* You can change your application string name here and it will be reflected along your application ```Toolbar``` when the activity is run

* The _themes.xml_ is where application theming and specific Android object styling can be achieved
	* We will cover styles and theming in a future lab

###### Gradle Directory

Android uses Gradle for dependency management and build tasks.  By default two Gradle files are created.  
	* The ```build.gradle (Module: app)``` script is used to define where to find project specific dependencies and configuration options
	* The ```build.gradle (Module: app)``` script is used to establish build requirements to ensure the proper syntax are being met for your target SDK choice
		* Notice there is a _mindSdkVersion_, _targetSdkVersion_, and _versionCode_ value
			* The _minSdkVersion_ indicates what the lowest SDK that is supported by your application
			* The _targetSdkVersion_ is the optimized version you have in mind for your device
			* If the _versionCode_ value is changed from one application development to the next users will have updates triggered on their device

###### Supporting Different Languages

Now that we’re familiar with the _strings.xml_ resource file, let’s take a look at how to add locale-specific string support.  

For this section On the Project Structure tab along the left-hand side of Android Studio, at the top where it currently says Android, set the view to Project.

![](http://i.imgur.com/BfxJ6ZO.png)

1. Right click the app/src/main/res directory and select New → Android Resource Directory
	* Set the directory name to **values-es**
	* Repeat the same process and set the directory name to **values-fr**
2. Now right-click each of these folders and create a New → Values Resource File and call each **strings.xml**.

3. In the values-es/strings.xml file, add the following:
```xml
<resources>
    <string name="app_name">Mi Aplicación</string>
</resources>
```
4. In the values-fr/strings.xml file, add the following:

```xml
<resources>
    <string name="app_name">Mon Application</string>
</resources>
```

5. Run the application via the emulator once again
	* Access the settings menu from inside the emulator and set the language to French
	* Locate your application icon in the application deck
	* Open your application and you will see your French strings have been selected based on your custom locale choice
	* Set the locale to es-Spanish and do the same

6. Notice that the string "Hello World!" does not change. This is due to the fact that this string is hard-coded in the code.
	* Add a new string called _hello_world_ for "Hello World!" in the English file
		* Remember to translate the string and update each of the language specific resource files
	* Modify activity_main.xml to use this new string

**Deliverable 5**

Take screenshots of your application with French and Spanish translations and save them. Then set the language of the emulator back to your preferred language.


###### Console Log Debugging

Being able to identify what is occurring within your application at certain points in the running cycle is important for any development process. Android Studio offers the ability to have log messages printed to the console.

1. Open the _MainActivity.java_ class file
2. Immediately after the class declaration add the following string:

```java
private static final String TAG = "MainActivity";
```

3. Inside ```onCreate()``` add the following:

```java
Log.i(TAG, "This is a log display!");
```

4. You will also need to add an ```import android.util.Log;``` statement.

5. Run the application via the emulator or device once more
 	* Along the bottom portion of Android Studio, click the _Logcat_ tab
	* Set the log-level dropdown to Info and search for MainActivity
	* You'll be presented with a console log information that you built into the ```onCreate()``` function
	* This tactic can be utilized throughout the development cycle to test certain portions of your code to know how an application is behaving and where in the Activity lifecycle your application may be encountering problems

**Deliverable 6**

Take and save a screenshot of the log message window.

**Lab Completion**

* Submit a document with the following items:
	* The screenshots you took of the app
	* Your answers to questions asked throughout

* Keep a copy of your project work and answers for future reference
