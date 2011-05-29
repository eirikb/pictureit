Pictureit
===
This is a simple proof of concept project. <br>
index.html contains a simple piece of JavaScript code that will extract characters from image.png (RGB-values) and make the browser render a full page based on the text hidden in the image.

This is not stenography, since the text within the image is not hidden og cryptated in any particular way.

It is not opimized in any way, no encoding/encrypting, alpha values in PNG is not used, images within the image are stored as Base64. I have no ambitions for implementing optimizations, since this is a simple, working proof of concept.

Demo
---
http://eirikb.github.com/pictureit/

Building the image
---
This will create image.png from the textfile

    java -jar pictureit.jar testfile.txt  

