FROM nginx:latest

RUN rm /etc/nginx/conf.d/default.conf
COPY ./default.conf /etc/nginx/conf.d
COPY certificate.pem /etc/nginx/ssl/certificate.pem
COPY key.pem /etc/nginx/ssl/key.pem

EXPOSE 80 443

CMD ["nginx", "-g", "daemon off;"]