package org.cgtools.anttasks.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

import java.io.File;

public class ReplaceTask extends Task {
  private String value;
  private String regexp;
  private String replacement;
  private String property;

  protected void precheck() {
    if (property == null || property.length() == 0) {
      throw new BuildException("'property' attribute is required.");
    }

    if (value == null) {
      throw new BuildException("'value' attribute is required");
    }
    if (regexp == null) {
      throw new BuildException("'regexp' attribute is required");
    }
    if (replacement == null) {
      throw new BuildException("'replacement' attribute is required");
    }
  }

  public void execute() throws BuildException {
    precheck();
    String output = value.replaceAll(regexp, replacement);
    getProject().setProperty(property, output);
    log("Set '" + property + "' to '" + output + "'", Project.MSG_VERBOSE);
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public void setRegexp(String regexp) {
    this.regexp = regexp;
  }

  public void setReplacement(String replacement) {
    this.replacement = replacement;
  }

}
