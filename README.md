# Bark-o-meter
## Introduction
Don't you hate wondering whether your dog is peacefully sleeping or throwing a fit when you're out of the house?
This repository contains the code used to build a bark-o-meter to monitor and register barking when out of the house.

The project uses a raspberry pi for collecting, storing and presenting data. Data is captured using a Vellman VMA309 sound sensor.

## Roadmap
The project has two parts. The first part deals with setting up hardware, capturing sound data and storing it. The second part presents the data to the user. The working order is presented below.

1. Capturing data
   1. Setup hardware and verify working signal receieved by software.
   2. Select analog or digital sound input. Tweak settings to register at a reasonable level.
   3. Setup start and stop functions and write data to database.
2. Presenting data
   1. Import and format data.
   2. Create a dashboard that presents the registered data.
   3. Publish dashboard to make it accessible online.

## Task status
- [x] Connect sensor to Raspberry pi and verify that it works.
- [ ] Setup interupt routine that registers when a sound occurrs.
- [ ] Record time of occurrence.
- [x] Design and implement structure for storing and exporting data.
- [x] Import data.
- [x] Build simple dashboard for presenting data.
- [x] Publish dashboard on local network for testing.
- [ ] Publish dashboard for online access.

## Software
The project is written using C for recording sound from the sensor and storing it, Java for constructing a simple back-end to deliver data, and finally a simple web stack for presenting the data as a webpage. The final goal is to have a simple VPS run the dashboard and database and let the raspberry pi upload data to the database. The following libraries and/or frameworks are used:
* pigpio (link) - for reading data from the sensor using the RPi.
* Java Spark (insert link) - simple web server for delivering data to the dashboard.
* Plotly (insert link)- a fairly simple but powerful library for presenting data using JavaScript.
* PSQL database - for storing on/off times and any detected sound.

## Tweaking the sound sensor
Capturing the correct noises is tricky. The sensor has a tendency to capture e.g. the washing machine amongst other things. This can be solved in two ways, one based using hardware and one using software. Option one is to tweak the sensitivity of the sensor using the potentiometer. The second option is to discard sound readings below some threshold. The level of both depends on the location of the sensor as well as the proximity to external sound sources.

## Potential Future Work
* Expand data presentation with more options.
* Make the dashboard update automatically without reloading the page.
* Implement a better start-up process - a simple start-up script on the raspberry pi will likely go a long way.
