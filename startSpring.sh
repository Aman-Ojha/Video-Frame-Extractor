#!/bin/zsh

# Delete H2 database files
rm -rf /Users/amanojhaa/my-h2-databases/*

# Check for process on TCP port 8080
pids_to_kill=$(lsof -ti tcp:8080 | while read pid; do
  if ! ps -p $pid -o comm= | grep -iq "Postman"; then
    echo $pid
  fi
done)

if [ -n "$pids_to_kill" ]; then
  echo $pids_to_kill | xargs kill -9
  echo "Killed processes on port 8080 except Postman"
else
  echo "No non-Postman processes found on port 8080"
fi

# Run the Spring Boot application
./mvnw spring-boot:run