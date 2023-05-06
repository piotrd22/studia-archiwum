FROM python:3.11-slim-bullseye

COPY . .

RUN pip install -r requirements.txt

EXPOSE 8080

CMD ["python", "app.py"]