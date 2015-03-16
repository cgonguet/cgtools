# grep #

Grep into a file

### Parameters ###

|Attributes|Description|Required|
|:---------|:----------|:-------|
|file|File to grep in|yes|
|regexp|Regular expression to search for|yes|
|property|Property to be created with result|yes|

### Example ###
If the file `/tmp/my_file.txt` contains:
```
#Header
#Comment
#
Version: 1.5
#
Another-Version: 2.6
```

#### Example 1 ####
```
<grep file="/tmp/my_file.txt" regexp=".*Version:.*" property="grep.result"/>
<echo>${grep.result}</echo>
```
Returns:
```
Version: 1.5
Another-Version: 2.6
```

#### Example 2 ####
```
<grep file="/tmp/my_file.txt" regexp="Version: (.*)" property="grep.result"/>
<echo>
GREP   : ${grep.result}
VALUE 1: ${grep.result.1}
</echo>
```
Returns:
```
GREP   : Version: 1.5
VALUE 1: 1.5
```

#### Example 3 ####
```
<grep file="/tmp/my_file.txt" regexp="(.+Version): (.*)" property="grep.result"/>
<echo>
GREP   : ${grep.result}
VALUE 1: ${grep.result.1}
VALUE 2: ${grep.result.2}
</echo>
```
Returns:
```
GREP   : Another-Version: 2.6
VALUE 1: Another-Version
VALUE 2: 2.6
```