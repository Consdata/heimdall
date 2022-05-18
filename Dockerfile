FROM node as build-stage
WORKDIR /consdata
COPY ./package*.json /consdata/
RUN npm install
COPY ./ /consdata/
RUN npm run build
FROM nginx:1.21.6
COPY --from=build-stage /consdata/dist/apps/heimdall2.0 /usr/share/nginx/html
