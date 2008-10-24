package org.cgtools.anttasks.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;


public class FileChooserTask extends Task {
  private File path;
  private String suffix;
  private String selectedFile;
  private String property;
  private String title;

  public void execute() throws BuildException {
    selectedFile = null;

    if (property == null || property.length() == 0) {
      throw new BuildException("'resultProperty' attribute is required.");
    }

    JFileChooser chooser = new JFileChooser();
    if (title != null || title.length() > 0) {
      chooser.setDialogTitle(title);
    }

    if (path != null && path.exists()) {
      chooser.setCurrentDirectory(path);
    }

    if (suffix != null && suffix.length() > 0) {
      chooser.addChoosableFileFilter(new FileFilter() {
        public boolean accept(File f) {
          if (f.isDirectory()) {
            return true;
          }
          String ext = null;
          String filename = f.getName();
          int i = filename.lastIndexOf('.');
          if (i > 0 && i < filename.length() - 1) {
            ext = filename.substring(i + 1).toLowerCase();
          }
          return ext != null && ext.equals(suffix);
        }

        public String getDescription() {
          return "." + suffix;
        }
      });
    }

    final JFrame mainFrame = new JFrame();
    int result = chooser.showOpenDialog(mainFrame);
    switch (result) {
      case JFileChooser.APPROVE_OPTION:
        selectedFile = chooser.getSelectedFile().getPath();
        break;
      case JFileChooser.CANCEL_OPTION:
        throw new BuildException("Cancel file selection");
      case JFileChooser.ERROR_OPTION:
        throw new BuildException("Error while selecting file");
    }

    log("Select file: " + selectedFile);

    getProject().setProperty(property, selectedFile);
    log("Set property: " + property + " -> " + selectedFile,
        Project.MSG_VERBOSE);
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public void setPath(final File path) {
    this.path = path;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }
}