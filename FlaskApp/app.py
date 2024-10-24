import os
import datetime  # Add datetime for timestamp
from flask import Flask, request, make_response, jsonify
from dotenv import load_dotenv
from pymongo import MongoClient
from flask_cors import CORS  # Import CORS

load_dotenv()

app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

# MongoDB connection string
mongo_db_url = os.getenv('MONGO_DB_CONN_STRING')
client = MongoClient(mongo_db_url)
db = client['PmsIot']
collection = db['iot']

# Store sensor data and message
sensor_data = {
    "temperature": None,
    "humidity": None,
    "ammonia": None
}
message = "Hello, IoT!"

@app.route('/')
def hello():
    return "Hello, Secure World!"

@app.route('/update')
def update():
    global sensor_data
    # Get sensor data from query parameters
    temp = request.args.get('temp')
    humidity = request.args.get('humidity')
    ammonia = request.args.get('ammonia')

    # Store sensor data in dictionary
    sensor_data = {
        "temperature": temp,
        "humidity": humidity,
        "ammonia": ammonia,
        "timestamp": datetime.datetime.utcnow()
    }

    # Insert sensor data into MongoDB
    collection.insert_one(sensor_data)

    # Return the message to be displayed on the OLED
    return message

@app.get('/get-data')
def get_data():
    latest_data = collection.find().sort("timestamp", -1).limit(1)
    data_list = []
    for data in latest_data:
        data['_id'] = str(data['_id'])
        data_list.append(data)

    response = make_response(jsonify(data_list))
    response.headers['Content-Type'] = 'application/json'
    return response

@app.post('/set-message')
def set_message():
    global message
    data = request.get_json()
    message = data.get('message', message)
    return jsonify({"status": "success", "message": message})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
