apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: ingress-heimdall
  annotations:
    # use the shared ingress-nginx
    nginx.ingress.kubernetes.io/rewrite-target: "/$1"
    kubernetes.io/ingress.class: "nginx"
spec:
  rules:
    - host: ${HOSTNAME}
      http:
        paths:
          - path: /api/monitor/v1/(.*)
            backend:
              serviceName: heimdall-monitor
              servicePort: 8080
          - path: /api/monitor-tracking/v1/(.*)
            backend:
              serviceName: heimdall-monitor-tracking
              servicePort: 8080
          - path: /api/report/v1/(.*)
            backend:
              serviceName: heimdall-report
              servicePort: 8080
          - path: /api/report-list/v1/(.*)
            backend:
              serviceName: heimdall-report-list
              servicePort: 8080
          - path: /api/dependency-list/v1/(.*)
            backend:
              serviceName: heimdall-dependency-list
              servicePort: 8080
          - path: /axon/(.*)
            backend:
              serviceName: heimdall-axonserver
              servicePort: 8024
          - path: /(.*)
            backend:
              serviceName: heimdall-frontend
              servicePort: 80
