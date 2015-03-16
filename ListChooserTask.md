# listChooser #

Set property to an input comboBox choice value

### Parameters ###

|Attributes|Description|Required|
|:---------|:----------|:-------|
|title|Title of the window|no|
|message|Message at the top of window|no|
|property|Property to be created with the input value|yes|
|listing|Comma separated list of possible values|yes|
|current|Default selected value|no|

### Example ###
```
<listChooser title="ListChooser" property="result.choice"
                 message="Choose in the list:"
                 listing="aaa,bbb,ccc"
                 current="bbb"/>
```