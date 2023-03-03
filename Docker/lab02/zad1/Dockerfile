FROM node:12

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 8080

# ENV HOST=0.0.0.0

CMD [ "node", "index.js" ]