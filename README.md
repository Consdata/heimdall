# Heimdall

[![Build Status](https://travis-ci.org/Consdata/heimdall.svg?branch=master)](https://travis-ci.org/Consdata/heimdall)

## Running app

- Start Axon Server
- Start backend if needed
- Start frontend if needed

### Running Axon Server

#### Axon Server via jar

- Download AxonServer standalone jar & unzip it to AxonServer directory
- $ java -jar AxonServer/axonserver.jar

#### Axon Server via Docker

- $ docker run -d --name axonserver -p 8024:8024 -p 8124:8124 axoniq/axonserver

### Running app backend

#### Local via Intellij
- Start service via Intellij config.
- Start frontend with proxy: `$ yarn start --proxy-config proxy.local.dev.conf.json`
- Open: `http://localhost:4200/`

#### Local via docker-compose
- Start app as docker: `$ docker-compose up`
- Start frontend with proxy: `$ yarn start --proxy-config proxy.local.compose.conf.json`
- Open: `http://localhost:4200/`

#### Build via docker-compose
- Start app as docker `$ docker-compose up`
- Open: `http://localhost:8200/`

## Building app

### Tag latst

```
$ ./gradlew build
$ docker-compose build
$ docker-compose push
```

### Specific tag

```
$ ./gradlew build
$ HEIMDALL_VERSION="0.1.10" docker-compose build
$ HEIMDALL_VERSION="0.1.10" docker-compose push
```

## Endpoints

### Send dependency report

```bash
npx @consdata/heimdall-scanner npm|maven | curl -H "Content-Type: application/json" -X POST -d @- http://localhost:8080/report
```
```bash
npx @consdata/heimdall-scanner npm|maven | curl -H "Content-Type: application/json" -X POST -d @- http://localhost:8200/api/report/v1/report
```

### Mark dependency as tracked

```bash
curl -H "Content-Type: application/json" -X POST -d '{ "group": "@angular", "artifact": "core", "scope": "Npm" }' http://localhost:8080/monitor/tracking
```
```bash
curl -H "Content-Type: application/json" -X POST -d '{ "group": "@angular", "artifact": "core", "scope": "Npm" }' http://localhost:8200/api/monitor/v1/monitor/tracking
```

### Search for dependencies

```bash
curl -H "Content-Type: application/json" -d '{"query": "angular core"}' http://localhost:8080/
```
```bash
curl -H "Content-Type: application/json" -d '{"query": "angular core"}' http://localhost:8200/api/dependency-list/v1/
```

## Tips

### Intellij with WSL on Windows

Disable 'safe write' to enable Angular CLI detect code changes.
