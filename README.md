# Receipt Processing Service

A Spring Boot application that processes receipts and calculates reward points based on certain criteria.

## Running the code
1. Clone the repository(Using SSH):

   ```
   git clone git@github.com:Ashwin-Rajarajan/receipt-processor-challenge.git
   ```
2. Go to the directory:

   ```
   cd receipt-processor-challenge
   ```
3. Build the Docker image:

   ```
   docker build -t receipt-processor .
   ```  
4. Run the Docker container:

   ```
   docker run --rm -p 8080:8080 receipt-processor
   ```
5. The API will be available at:

   ```
   http://localhost:8080
   ```

## APIs
1. Process Receipt:
   1. Processes a receipt and stores the points awarded for it.
   2. Endpoint: `http://127.0.0.1:8080/receipts/process`
   3. HTTP Request Method: `POST`
   4. Sample CURL: 
    
       ```curl 
          curl --location 'http://127.0.0.1:8080/receipts/process' \
            --header 'Content-Type: application/json' \
            --data '{
            "retailer": "Target",
            "purchaseDate": "2022-01-01",
            "purchaseTime": "13:01",
            "items": [
            {
            "shortDescription": "Mountain Dew 12PK",
            "price": "6.49"
            },{
            "shortDescription": "Emils Cheese Pizza",
            "price": "12.25"
            },{
            "shortDescription": "Knorr Creamy Chicken",
            "price": "1.26"
            },{
            "shortDescription": "Doritos Nacho Cheese",
            "price": "3.35"
            },{
            "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
            "price": "12.00"
            }
            ],
            "total": "35.35"
            }'
       ```
   5. Returns: The ID of the Receipt object(HTTP 200) if the receipt is valid. An error message(HTTP 400) if the request body is not valid according to the yml file.
   6. Success Response Example:
      ```json
      {
        "id": "b28ded36-3f46-4afe-9eb1-1f763d880587"
      }
      ```
   7. Error Response Example:      
      ```json
      {
        "description": "The receipt is invalid."
      }
      ```

2. Fetch Points for a Receipt
    1. Fetches the points awarded for an already processed receipt.
    2. Endpoint: `http://127.0.0.1:8080/receipts/{id}/points`
    3. HTTP Request Method: `GET`
    4. Sample CURL:

        ```curl 
           curl --location 'localhost:8080/receipts/b28ded36-3f46-4afe-9eb1-1f763d880587/points'
        ```
    5. Returns: The Points awarded to the Receipt corresponding to the ID(HTTP 200) if the receipt ID is valid. An error message(HTTP 404) if no such ID exists.
    6. Success Response Example:
       ```json
       {
         "points": 28
       }
       ```
    7. Error Response Example:
       ```json
       {
         "description": "No receipt found for that ID."
       }
       ```
## Running Test Cases

To run test cases before running the service, please uncomment line number 10 on the Dockerfile.
Note that this will cause the build to fail in case any test case fails.

(Unless the code is modified, the test cases will not fail as they have been validated before uploading
the code to the repository).

## Package Structure

The code is structured to follow Best Practices in Spring Boot, to ensure clean separation and to enhance maintainability.
The `src` is split into two modules: `main` and `test`. `main` has the logic and classes for the Application code while `test` has test cases to validate the code.

The `main` module is further split into these packages:
1. `api` - Contains the Controllers (classes responsible for handling HTTP requests and defining API endpoints).
2. `dto` - Contains the Data Transfer Objects for transferring data.
   1. `request` - DTOs related to HTTP requests.
   2. `response` - DTOs related to HTTP responses.
3. `model` - Holds the Database Models or the Data Access Objects(DAO).
4. `repository` - These classes interact with the Databases (or in-memory data stores).
5. `service` - The classes that actually handle business logic.
6. `utils` - Utility classes and Helper functions.

Some other important files in the source code are:
1. `Dockerfile` - The script to build a Docker Image for the app.
2. `build.gradle` - The build configuration file for Gradle, which specifies the dependencies and build tasks.

## Cleanup

Once the code is run and the API is validated, the docker container and the image can be removed. This can be done by running the following commands:

1. Stop the code running in the container by pressing `Control + C`. This will terminate the SpringBoot application and 
remove the docker container since we ran the `docker run` command with the `--rm` flag. 
2. Remove the Docker Image by running this command:
   ```bash
   docker rmi receipt-processor
   ```