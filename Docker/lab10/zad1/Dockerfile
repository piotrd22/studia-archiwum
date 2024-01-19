FROM python:3.11-slim-bullseye

COPY main.py .
COPY requirements.txt .

RUN pip install -r requirements.txt

EXPOSE 5000

CMD ["python", "main.py"]