#!/bin/bash
sleep 20

echo SETUP.sh + "Initializing Mongo DB Cluster..."
mongo --host mongo1:27017 <<EOF
  var config_json = {
    "_id": "replicaset",
    "version": 1,
    "members": [
      {
        "_id": 0,
        "host": "mongo1:27017",
        "priority": 2
      },
      {
        "_id": 1,
        "host": "mongo2:27017",
        "priority": 0
      },
      {
        "_id": 2,
        "host": "mongo3:27017",
        "priority": 0
      }
    ]
  };
  rs.initiate(config_json, { force: true });
  rs.reconfig(config_json, { force: true });
  db.getMongo().setReadPref('nearest');
EOF