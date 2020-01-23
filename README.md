# Bark-o-meter
## Introduction
Don't you hate wondering whether your dog is peacefully sleeping or throwing a fit when you're out of the house?
This repository contains the code used to build a bark-o-meter to monitor and register barking when out of the house.

The project uses a raspberry pi for collecting, storing and presenting data. Data is captured using a VMA309 sound sensor.

## Roadmap
The project has two parts. The first part deals with setting up hardware, capturing sound data and storing it. The second part presents the data to the user. The working order is presented below.

1. Capturing data
   1. Setup hardware and verify working signal receieved by software.
   2. Select analog or digital sound input. Tweak settings to register at a reasonable level.
   3. Setup start and stop functions and write data to file.
2. Presenting data
   1. Import and format data.
   2. Create a dashboard that presents the registered data.
   3. Publish dashboard to make it accessible online.

## Task status
- [ ] Connect sensor to Raspberry pi and verify that it works.
- [ ] Setup interupt routine that registers when a sound occurrs.
- [ ] Record time of occurrence.
- [ ] Design and implement structure for storing and exporting data.
- [ ] Import data.
- [ ] Build dashboard for presenting data.
- [ ] Publish dashboard on local network for testing.
- [ ] Publish dashboard for online access.

## Future Work
* Use a proper database to store data points
