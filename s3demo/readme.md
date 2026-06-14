# Spring Boot AWS S3 File Upload

## Features
- Upload files to AWS S3
- Download files from AWS S3
- Delete files from AWS S3

## Tech Stack
- Java 17
- Spring Boot
- AWS SDK v2
- Maven

## Setup
1. Create an S3 bucket
2. Configure AWS credentials
3. Update application.properties
4. Run the application

## APIs

POST /upload
GET /download/{fileName}
DELETE /delete/{fileName}