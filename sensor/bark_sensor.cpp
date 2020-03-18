#include <iostream>
#include <cstring>
#include <thread>
#include <chrono>
#include <string>
#include <ctime>
#include "PIGPIO/pigpio.h"
#include "/usr/include/postgresql/libpq-fe.h"
 
bool runThread = true;
const char connStr[] = "host= port= dbname= user= password=";
const char query[] = "INSERT INTO barkdata (event, time, level) VALUES ($1, to_timestamp($2), $3);";
 
void write_value_to_db(uint16_t val){
    PGconn *conn = PQconnectdb(connStr);
 
    if(PQstatus(conn) != CONNECTION_OK){
        std::cout << "db connection failed." << std::endl;
        PQfinish(conn);
    } 
 
    time_t time = std::chrono::system_clock::to_time_t(std::chrono::system_clock::now());
    unsigned long t = time; 
 
    char event[8] = "event";
    char level[8];
    char msTime[24];
    sprintf(level, "%ld", val);
    sprintf(msTime, "%ld", time);
    const char *const paramValues[] = {event, msTime, level};
    const int paramLengths[] = {sizeof(event), sizeof(msTime), sizeof(level)};
    const int paramFormats[] = {0, 0, 0};
 
    PGresult *result = PQexecParams(conn, query, 3, NULL, paramValues, paramLengths, paramFormats, 0);
 
    if(PQresultStatus(result) != PGRES_COMMAND_OK){
        std::cout << "DB entry failed: " << PQresultErrorMessage(result) << std::endl;
    } 

    PQclear(result);
    PQfinish(conn);
}
     
void write_on_off_db(bool on){
    PGconn *conn = PQconnectdb(connStr);

    if(PQstatus(conn) != CONNECTION_OK){
        std::cout << "db connection failed." << std::endl;
        PQfinish(conn);
    } 
		 
    time_t time = std::chrono::system_clock::to_time_t(std::chrono::system_clock::now());
    unsigned long t = time; 
    char event[5];
    
    if(on){
        strcpy(event, "on");
    } else {
        strcpy(event, "off");
    }
    
    char level[] = "0";
    char msTime[24];
    sprintf(msTime, "%ld", time);
    const char *const paramValues[] = {event, msTime, level};
    const int paramLengths[] = {sizeof(event), sizeof(msTime), sizeof(level)};
    const int paramFormats[] = {0, 0, 0};
     
    PGresult *result = PQexecParams(conn, query, 3, NULL, paramValues, paramLengths, paramFormats, 0);
     
    if(PQresultStatus(result) != PGRES_COMMAND_OK){
        std::cout << "DB entry failed: " << PQresultErrorMessage(result) << std::endl;
    } 
    
    PQclear(result);
    PQfinish(conn);
}
        
void sensor_listen() {
    uint16_t soundLevel = 0;
    std::cout << "Sensor listener started." << std::endl;

    while(runThread){
        soundLevel = gpioRead(17); 
	// time_t is in the unix int time representation
	time_t time = std::chrono::system_clock::to_time_t(std::chrono::system_clock::now());
        if(soundLevel > 0){ 
            std::cout << "Sound registered at: " << std::ctime(&time);
            write_value_to_db(soundLevel);
            std::this_thread::sleep_for(std::chrono::seconds(2));
            soundLevel = 0; // reset soundLevel before restarting loop - superfluous?
        }
    }
}

int main() {
    std::string input = "";
    std::cout << "Bark sensor started, \"stop\" to exit." << std::endl; 
            
    if(gpioInitialise() < 0){
        std::cout << "gpio init failed - terminating." << std::endl; 
	return -1;
    } else {
        std::cout << "gpio init completed." << std::endl;
    }
        
    gpioSetMode(17, PI_INPUT);
    write_on_off_db(true);    
    std::thread soundListener(sensor_listen);

    while(input != "stop"){
        std::cin >> input;
    }
 
    runThread = false;
    soundListener.join();
    write_on_off_db(false);
    gpioTerminate();
    std::cout << "Bark sensor exited." << std::endl;
    return 0;
}
