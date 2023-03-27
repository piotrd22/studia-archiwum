FROM alpine:latest

ARG PYTHON_VERSION=3.9.2
ARG SERVER_PORT=5000

ENV PYTHON_VERSION=$PYTHON_VERSION
ENV PORT=$SERVER_PORT

RUN apk add \
    wget \
    gcc \
    make \
    zlib-dev \
    libffi-dev \
    openssl-dev \
    musl-dev

RUN cd /opt \
    && wget https://www.python.org/ftp/python/${PYTHON_VERSION}/Python-${PYTHON_VERSION}.tgz \                                              
    && tar xzf Python-${PYTHON_VERSION}.tgz

RUN cd /opt/Python-${PYTHON_VERSION} \ 
    && ./configure --prefix=/usr --enable-optimizations --with-ensurepip=install \
    && make install

COPY ./myapp /myapp
WORKDIR /myapp
RUN pip3 install -r requirements.txt

EXPOSE ${PORT}

CMD ["python3", "app.py"]