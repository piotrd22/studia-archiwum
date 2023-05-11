FROM node:alpine

RUN apk --no-cache add curl

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY index.js .

HEALTHCHECK --interval=30s --timeout=30s --retries=3 --start-period=20s \
    CMD curl --fail http://localhost:3000/health || exit 1

EXPOSE 3000

CMD ["node", "index.js"]
