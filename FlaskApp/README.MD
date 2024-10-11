# Prerequisites  
>-  Create a MongoDB Atlas cluster.
  
# Steps on how to run   
1. Replace the value of the .env file to your MongoDB Atlas  
```
MONGO_DB_CONN_STRING=mongodb+srv://<DB_USER_NAME>:<DB_PASSWORD>@<MONGO_DB_ATLAS_ENDPOINT>/sensors_db
```
2. Run the Flask server
```
docker compose up -d
```
