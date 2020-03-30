# photos-app

Before executing any functionality user must obtain a JWT token. For the simplicity this will be a single user based(no user store) application. Hence UserName and Password used for the JWT generation is hardcoded as of now. To get the JWT token user has to perform request to “/authenticate” end point with below username and Password in the body of the request payload in Jason format.

Upon success user will get JWT token and when making below requests user must send the above obtained JWT token as a “Authentication” request header in below format.

    Authorization  Bearer “JWT token”


Post a photo , Save photo as draft.

•	User has to execute a post request to “/photo” with below parameters in request body. 
photo –  Image needs to be uploaded.
caption – Caption that belong to the uploading image.
draft – Identification of save as draft. (values = “true” or “false”)
•	Image will be saved in Local disk with the path configured in “application.properties” (image.basepath) and this path has to be created beforehand.
•	Information related to image will be sorted in “Image” table.
•	To indicate save photo as draft send the “Draft” value as “true” in the request body.

Edit captions

•	Execute PUT request to “/photo/{imageId}/caption”. 
•	Image id will be the identification of image that caption belongs to. Updated captions has to send in the request body with the key “caption”.

Delete Photo

•	Execute DELETE request to "/photo/{imageId}".
•	Image id will be the image that user wants to delete.

List Photos

•	Execute GET request to "/photo/{type}" end point.
•	Type parameter can be one of three types.
o	“all” – return all photos.
o	“myphotos” – return photos belong to current user.
o	“draft” – returns all the photos saved as draft.

ASC/DESC on Published Date.

•	Execute GET request to “/photo/sort/{order}” endpoint.
•	{order} value must be either “asc” or “desc” and this will return sorted image list based on published time.

Filter Photos by User.

•	Execute GET request to "/photo/user/{userId}" end point with the ID of the user that you want to filter with.

Limit Upload File Size and Dimension.

•	This app will only accept images less that 5MB. This values is configured in “application.properties” file with the use of below property.
 
“spring.servlet.multipart.max-file-size=10MB”

•	This app has “boolean validateImage(MultipartFile imageFile)” method to validate the dimensions of the image that user trying to upload. To set max width and height please use below properties in “application.properties” file. If image exceeds max limits the App will throw an exception.  

“image.maxheight=300”
“image.maxwidth=300”
