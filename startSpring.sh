#!/bin/zsh

# Delete H2 database files
rm -rf /Users/amanojhaa/my-h2-databases/*

# Check for process on TCP port 8080
PID=$(lsof -ti tcp:8080)

if [[ -n "$PID" ]]; then
  # Kill the process if PID is not empty
  kill -9 $PID
  echo "Killed process with PID $PID on port 8080"
else
  echo "No process running on port 8080"
fi

# Run the Spring Boot application
./mvnw spring-boot:run