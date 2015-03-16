# Ant Tasks #

## Overview ##

  * The GUI tasks:
    * [multiple input](MultipleInputTask.md): allow to set several properties to input values in the same window
    * [file editor](FileEditorTask.md): very simple file editor
    * [file chooser](FileChooserTask.md): a basic swing file chooser
    * [list chooser](ListChooserTask.md): as input but using a comboBox

  * The regexp tasks:
    * [grep](GrepTask.md): search in a file
    * [replace](ReplaceTask.md): replace a substring in a property

## Install ##

  1. Download the CGAntTasks.jar [here](http://cgtools.googlecode.com/files/CGAntTasks.jar)
  1. Add it to your ANT additional classpath
  1. Add the XML namespace in your ANT project
```
   <project name="MY_PROJECT" 
            xmlns:cgtools="antlib:org.cgtools.anttasks.ant">
```
  1. Call the tasks as follow:
```
   <cgtools:minput message="Enter the following values:" length="25">
      <inputline label="Label A: " text="my_value_for_a" property="result.for.a"/>
      <inputline label="Label B: " property="result.for.b"/>
    </cgtools:minput>
    <echo>${result.for.a} and ${result.for.b}</echo>
```

## Usage ##

  * [muinput](MultipleInputTask.md)
  * [editor](FileEditorTask.md)
  * [fileChooser](FileChooserTask.md)
  * [listChooser](ListChooserTask.md)
  * [grep](GrepTask.md)
  * [replace](ReplaceTask.md)
