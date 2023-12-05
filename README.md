# File upload
Uses apache file upload 2 streaming method with spring boot. Which means the file is NOT uploaded to a temporary location and then the input stream to that file givben to our spring code. Instead the Apache library parses the Multi part form message and gives us each field's input stream. A field can be text, date, password ... or file type.

# *Handlers : strategy design pattern*
This code also illustrates using the strategy pattern with spring. Have a look at @Service(FileUploadConstants.LOCAL)
in [StreamHandlerBase](https://github.com/tgkprog/fileup/blob/main/src/main/java/com/bb/fileUp/service/handler/StreamHandlerBase.java)  class and usage in the [controller](https://github.com/tgkprog/fileup/blob/3fa965bbcca3044c8ba63e5beabc0142351d7344/src/main/java/com/bb/fileUp/web/FileUploadController.java#L75C76-L75C82)
