FROM node as build-stage
WORKDIR /consdata
COPY ./consdata/package*.json /consdata/
RUN npm install
COPY ./consdata/ /consdata/
RUN npm run build
FROM nginx:1.15
COPY --from=build-stage /consdata/dist/apps/heimdall2.0 /usr/share/nginx/html
