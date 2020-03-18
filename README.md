# Bark-o-meter
Don't you hate wondering whether your dog is peacefully sleeping or throwing a fit when you're out of the house?
This repository contains the code used to build a bark-o-meter to monitor and register barking when out of the house.

The project uses a Raspberry Pi for collecting, storing and presenting data. Data is captured using a Velleman VMA309 sound sensor.

## Project status
The first working iteration is complete. It has been tested over the local network using the Raspberry pi for both hosting the database and running the bark sensor. The current version uses simplified sound readings in the form sound/no sound, with the sensitivity adjusted using the built in potentiometer in the sensor.

## Roadmap and task status
The project has two parts. The first part deals with setting up hardware, capturing sound data and storing it. The second part presents the data to the user. The working order is presented below.

### Capturing data
- [x] Connect sensor to Raspberry Pi and verify that it works.
- [x] Setup routine that registers when a sound occurrs.
- [x] Record time of occurrence.
- [x] Design and implement structure for storing and exporting data.
### Presenting data
- [x] Import data.
- [x] Build simple display for presenting data.
- [x] Publish display on local network for testing.
- [ ] Create executable of web server for deployment on VPS. 
- [ ] Publish display for online access 

## Software
The project is written using C++ for recording sound from the sensor and storing it in a Postgres database, Java for constructing a simple back-end to deliver data from the database, and finally a simple web stack for presenting the data as a webpage. The final goal is to have a simple VPS host the display and database and let the Raspberry Pi upload data to the database. The following libraries and/or frameworks are used:
* [pigpio](http://abyz.me.uk/rpi/pigpio/) - to access GPIO pins on the Raspberry Pi to reading data from the sensor.
* [Java Spark](http://sparkjava.com/) - simple web server for delivering data to the display.
* [Plotly](https://plot.ly/javascript/) - a fairly simple but powerful library for presenting data using JavaScript.
* jQuery - for ajax calls.
* PostgreSQL - for storing on/off times and any detected sound. 
* libpq - for writing to the database from C++, database functions are more C-styled as a consequence. 

## Tweaking the sound sensor
Capturing the correct noises is tricky. The sensor has a tendency to capture e.g. the washing machine amongst other things. This can be solved in two ways, one based using hardware and one using software. Option one is to tweak the sensitivity of the sensor using the potentiometer. The second option is to discard sound readings below some threshold. The level of both depends on the location of the sensor as well as the proximity to external sound sources.

## Potential future work
* Improve sound observations to be more nuanced than the current sound/no sound version.
* Expand data presentation with more options, notably selecting time period to display.
* Make the display update automatically without reloading the page.
* Visual improvements for display.
* Include static versions of JavaScript libraries instead of loading from CDN every time.
* Improve error handling in the sound module and possibly the web server.
* Change the dummy GUI approach of the web server to a CLI (likely picocli (link)).
* Implement a better start-up process - a simple start-up script on the raspberry pi will likely go a long way.
