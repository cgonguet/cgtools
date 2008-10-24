package org.cgtools.anttasks.ant;

import org.apache.tools.ant.BuildException;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.IOException;

import org.cgtools.anttasks.ant.ValidableInputTask;

public class ListChooserTask  extends ValidableInputTask {
  private String listing;
  private String current;
  private String property;
  String[] availables;
  JComboBox jComboBox;

  protected void precheck() {
    if (property == null || property.length() == 0) {
      throw new BuildException("'property' attribute is required.");
    }
    if (listing == null || listing.length() == 0) {
      throw new BuildException("'listing' attribute is required (comma separated list).");
    }
    availables = listing.split(",");
  }

  protected JComponent getUserPane() throws IOException, BadLocationException {
    jComboBox = new JComboBox(availables);
    jComboBox.setSelectedItem(current);
    return jComboBox;
  }

  protected void proceed() throws Exception {
    getProject().setProperty(property, (String)jComboBox.getSelectedItem());
  }


  public void setProperty(String property) {
    this.property = property;
  }

  public void setCurrent(String current) {
    this.current = current;
  }

  public void setListing(String listing) {
    this.listing = listing;
  }
}
