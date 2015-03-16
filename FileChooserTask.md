# fileChooser #

A basic swing file chooser

### Parameters ###

|Attributes|Description|Required|
|:---------|:----------|:-------|
|title|Title of the window|no|
|path|Default path|no|
|property|Property to be created with the choose file path|yes|
|suffix|Suffix to create a filter|no|

### Example ###
```
<fileChooser title="Choose your file" 
             path="/tmp" 
             suffix="log" 
             property="result.file"/>
```