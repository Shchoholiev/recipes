FROM node:16-alpine AS builder
WORKDIR /app
COPY ./recipesweb/package.json package.json
COPY ./recipesweb/yarn.lock yarn.lock
RUN yarn install --production
COPY ./recipesweb .
RUN yarn build
FROM nginx:alpine
WORKDIR /usr/share/nginx/html
RUN rm -rf *
COPY --from=builder /app/build .
ENTRYPOINT ["nginx", "-g", "daemon off;"]
