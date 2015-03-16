# minput #

Set several properties to input values in the same window

### Parameters ###
|Attributes|Description|Required|
|:---------|:----------|:-------|
|title|Title of the window|no|
|message|Message at the top of window|no|
|length|Length of the input fields|no|


### Parameters specified as nested elements ###

## inputline ##

|Attributes|Description|Required|
|:---------|:----------|:-------|
|label|Label|no|
|property|The name of a property to be created from input value|yes|
|text|Default value|no|

### Example ###
```
<minput message="Enter the following values:" length="25">
   <inputline label="Label A: " text="Value for A" property="result.for.a"/>
   <inputline label="Label B: " property="result.for.b"/>
</minput>
```