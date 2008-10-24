package org.cgtools.anttasks.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

import javax.swing.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrepTask extends Task {
  private File file;
  private String regexp;
  private String property;
  String[] availables;
  JComboBox jComboBox;

  protected void precheck() {
    if (property == null || property.length() == 0) {
      throw new BuildException("'property' attribute is required.");
    }

    if (file == null) {
      throw new BuildException("'file' attribute is required");
    }
    if (!file.exists()) {
      throw new BuildException("File does not exist");
    }
  }


  public void execute() throws BuildException {
    precheck();
    try {
      String out = "";

      Pattern p = Pattern.compile(regexp);

      DataInputStream in = new DataInputStream(new FileInputStream(file));
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String strLine;
      try {
        while ((strLine = br.readLine()) != null) {
          Matcher matcher = p.matcher(strLine);
          if (matcher.matches()) {
            out += strLine;
            if (matcher.groupCount() != 0) {
              for (int i = 1; i <= matcher.groupCount(); i++) {
                String groupProp = property + "." + i;
                String groupVal = matcher.group(i);
                getProject().setProperty(groupProp, groupVal);
                log("Set '" + groupProp + "' to '" + groupVal + "'", Project.MSG_VERBOSE);
              }
              return;
            }
            out+= '\n';
          }
        }
      } finally {
        in.close();

        getProject().setProperty(property, out);
        log("Set '" + property + "' to '" + out + "'", Project.MSG_VERBOSE);
      }
    } catch (FileNotFoundException e) {
      throw new BuildException(e);
    } catch (IOException e) {
      throw new BuildException(e);
    }
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public void setRegexp(String regexp) {
    this.regexp = regexp;
  }

  public void setFile(File file) {
    this.file = file;
  }
}
