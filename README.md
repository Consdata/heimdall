# Heimdall

[![Build Status](https://travis-ci.org/Consdata/heimdall.svg?branch=master)](https://travis-ci.org/Consdata/heimdall)

## Running app

### Local development
- Start services via Intellij configs.
- yarn start --proxy-config proxy.local.dev.conf.json 

### Local build
- Start services via docker compose
- yarn start --proxy-config proxy.local.compose.conf.json


## Endpoints

### Send dependency report

```bash
npx @consdata/heimdall-scanner | curl -H "Content-Type: application/json" -X POST
d @- http://localhost:8081/report
```

### Mark dependency as tracked

```bash
curl -H "Content-Type: application/json" -X POST -d '{ "group": "@angular", "artifact": "core", "scope": "Npm" }' http://localhost:8083/monitor/tracking
```
