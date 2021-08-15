namespace java managementcenter

exception InvalidOperationException {
    1: i32 code,
    2: string description
}

struct SensorResource {
    1: string location,
    2: string timestamp,
    3: i32 humidity,
    4: i32 temp,
    5: i32 brightness,
    6: i32 volume
    7: string topic
}

service SensorResourceService {

    SensorResource getSensorData() throws (1:InvalidOperationException e),
    bool testConnection() throws (1:InvalidOperationException e)
}