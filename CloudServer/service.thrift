namespace java cloudserver

struct SensorResource {
    1: string location,
    2: string timestamp,
    3: i32 humidity,
    4: i32 temp,
    5: i32 brightness,
    6: i32 volume
}

service SensorResourceService {
    bool persistSensorData(1:SensorResource resource),
    bool testConnection()
}