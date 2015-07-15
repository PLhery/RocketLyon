# RocketLyon
getmdl.io based web public transport app (for Lyon, France - TCL ), for PhoneGap/Cordova on Android/iPh and Project Westminster on Windows 10

![screenshot](http://i.imgur.com/qT5VCBW.jpg)

Live demo : http://paul-louis.me/RocketLyon

## What works
  - Web app
   - [X] Search nearest stations
   - [X] See stops in street view

## Todo

- an Apache Cordova branch - Android (&iPhone) app https://cordova.apache.org/
  - [ ] Native Google Maps integration
- Project Westminster branch - Universal Windows 10 app  https://dev.windows.com/en-us/uwp-bridges/project-westminster
  - [ ] Integrate with Cortana voice commands

- Adapt it to your own city (in new forks ?)

- Web app things
  - [ ] Find street view bugs
  - [ ] show timetables ("horaires" button)
  - [ ] manage favorites
  - [ ] generate a "map.json" data with line trajectories
  - [ ] make a huge google map page to see stations/stops/lines
  - [ ] add options to select which lines to show 
  - [ ] adapt it to the cards's "map" button
  - [ ] add a velov map
  
## Installation

Just install it on you web server, either apache, nginx..

Generate the .json data, for TCL, download the .sqlite from http://app.tcl.fr/flux/mobile_referential/android/3/referential_android.zip and launch genData.php.

## Motivation

The current google play TCL ("Transports en Communs Lyonnais") app https://play.google.com/store/apps/details?id=com.micropole.android.tcl_mobile is old and do not respect google's guidelines anymore.. Moreover, there is no app for windows 10, and the windows phone app is really basic..


## Contributors

You can do anything unchecked on the todo list. If I'm doing one of those thing, I check it on the todo list. (and put it on "what work" when finished) - so fork the project, and when done send me a pull request

## Libs

doT is licensed under the MIT License https://github.com/olado/doT -  template engine
jQuery is licensed under the MIT License https://github.com/jquery/jquery -  could be replaced by zepto later ?
getmdl.io © Google, 2015. Licensed under an Apache-2 license. https://github.com/google/material-design-lite - Google's library of material design components

LOGO by Anshul Dhiman https://www.iconfinder.com/icons/438790/boost_rocket_space_startup_icon 
License: Creative Commons (Attribution 3.0 Unported) 

We need to create one for the project (following those guidelines https://www.google.com/design/spec/style/icons.html )


## License

Code released under the MIT license.
