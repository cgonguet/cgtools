# editor #

A simple file editor

### Parameters ###

|Attributes|Description|Required|
|:---------|:----------|:-------|
|title|Title of the window|no|
|message|Message at the top of window|no|
|file|File path to edit|yes|
|font|Editor font|no|
|fontSize|Editor font size|no|

### Example ###
```
<editor title="Editor" message="You can modify your file" 
        file="/tmp/my_file.txt" 
        font="Arial" fontSize="28"/>
```