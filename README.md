FileHandlerS3

Assumptions:
1. To effectively download and upload files to a downstream system accounting for downtimes and scale I have used Amazon S3 as intermediary.
2. Spring boot microservice will expose rest-apis to connect to upload/list/download these files from a fixed S3 bucket.
3. I have tested this msvc api to upload files upto 3gb.
4. S3 bucket name,access keys are configurable and one can use buckets of their own choosing. Reusable to the teeth.
5. Microservice apis work like an adapter and can be configured to use anyother file upload download service. Simply implement FileHandler interface 
    and implementations as jars in the same project. Your custom adapters will be picked up by apis and used to upload/download/list.
    

How to run:
1. Clone from the repository and do a plain mvn clean install to construct the jar.
2. Navigate to the target folder and run java -jar <artifact_name>.
3. configure application.yml with following parameters before doing maven install.
bucket:
  name: file-upload-ashwani
  accessKey: ****
  secretKey: ****
  
How to extend:
1. Make a separate spring boot project and implement the following interface. Don't forget to annotate the implementation with @Component/@Service 
annotation. Please keep the packaging name hierarchy into org.ashwani.spring.rest.* 
public interface FileHandler {

    public void upload(FileInfo fileInfo);

    public boolean supports(FILE_SERVICE file_service);

    public List<FileInfo> getAllFiles();

    FileInfo findFileByName(String fileName);
}
2. To implement the above interface you would need to configure the clients and other configuration beens for the broker. Please follow the \
   same package hierarchy.
3. Add this spring boot project in pom.xml as a dependency and rebuild the artifact. 

How to Run:
1. GetByFileName: curl --location --request GET 'http://localhost:8080/api/files/20190630_181404.jpg'
2. Get All Files: curl --location --request GET 'http://localhost:8080/api/files/'
3. Upload one file: curl --location --request POST 'http://localhost:8080/api/files/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "filePath":"D:\\Movies&TV\\Point Break 2015 1080p BluRay x264 DTS-JYK\\Point Break 2015 1080p BluRay x264 DTS-JYK.mkv",
    "fileName":"Point Break 2015 1080p BluRay x264 DTS-JYK.mkv",
    "fileService": "S3",
    "destination":"file-upload-ashwani"
}'
