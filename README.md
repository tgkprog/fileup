# file upload
Uses apache file upload 2 streaming method with spring boot. Which means the file is NOT uploaded to a temporary location and then the input stream to that file givben to our spring code. Instead the Apache library parses the Multi part form message and gives us each field's input stream. A field can be text, date, password ... or file type.
