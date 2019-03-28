# springBootFileExercise
A spring boot exercise that exposes an API that will allow of the following:

Uploading Files to an in-memory DB<br/>
Downloading Files from the in-memory DB<br/>
Listing all of the Files that are currently stored in DB<br/>

<br/>
To start up the application, run the following:
`mvn spring-boot:run`

<br/>
After which, you should be able to store items by issuing requests to the following URI in Postman or curl: `http://localhost:8080/files`
<br/>

To retrieve a file using its file ID, you can set a GET request against to its URI:
`http://localhost:8080/files/1`

To retrieve a file using its file name, you can set a GET request against to its URI:
`http://localhost:8080/files/ple.jpg`

To return file metadata for a given filename:
`http://localhost:8080/fileMetadata/ple.jpg`

To return file metadata for all the files in the DB:
`http://localhost:8080/fileMetadata`