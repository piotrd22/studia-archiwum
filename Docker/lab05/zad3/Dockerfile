ARG PYTHON_VERSION=3.11

FROM python:${PYTHON_VERSION}-slim-buster

COPY ./myapp /myapp

WORKDIR /myapp

RUN pip install -r requirements.txt

CMD ["python", "app.py"]