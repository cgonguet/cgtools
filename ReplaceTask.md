# replace #

Replace into a property value

### Parameters ###

|Attributes|Description|Required|
|:---------|:----------|:-------|
|value|File to grep in|yes|
|regexp|Regular expression to replace|yes|
|replacement|Replacement value|yes|
|property|Property to be created with result|yes|

### Example ###
```
<property name="replace.input" value="org.cgtools.anttasks.ant.ReplaceTask"/>
<cgtools:replace value="${replace.input}" 
                 regexp="\." replacement="/"
                 property="replace.result"/>
<echo>
INPUT : ${replace.input}
RESULT: ${replace.result}
</echo>
```
Returns:
```
INPUT : org.cgtools.anttasks.ant.ReplaceTask
RESULT: org/cgtools/anttasks/ant/ReplaceTask
```