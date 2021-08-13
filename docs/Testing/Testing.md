## Practical 2
### Functional Tests
- Test if datasets are being sent out and received [PASSED]
- Test if datasets from multiple sensors are being received and distinction of sensor data possible [PASSED]
- Test if sending and receiving datasets is possible within docker network and with docker-compose [PASSED]

### Performance Tests
- Test case where 10.000 datasets of sensor data is sent out was created and time was measured. [2,45sec / PASSED | Condition: < 5000ms]

## Practical 3
### Functional Tests

### Performance Tests

#### Runtime measurements UDP vs. MQTT
- 1 Round = 10.000 datasets
- Average time of 20 test runs

UDP            |  MQTT
:-------------------------:|:-------------------------:
![](./udp_avg_run.png)  |  ![](mqtt_avg_run.png)