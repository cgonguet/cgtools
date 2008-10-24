package org.cgtools.anttasks.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import sun.awt.VerticalBagLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.cgtools.anttasks.ant.ValidableInputTask;

public class MultipleInputTask extends ValidableInputTask {
  private ArrayList lines = new ArrayList();
  private int length = 50;

  protected void proceed() {
    for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
      InputLine inputLine = (InputLine) iterator.next();
      inputLine.ok();
    }
  }

  protected void precheck() {
  };

  protected JComponent getUserPane() {
    final JPanel userPane = new JPanel(new VerticalBagLayout());
    for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
      InputLine inputLine = (InputLine) iterator.next();
      userPane.add(inputLine.getPane(length));
    }
    return userPane;
  }


  public void addInputLine(InputLine line) {
    lines.add(line);
  }

  public void setLength(final int length) {
    this.length = length;
  }

  public static class InputLine extends ProjectComponent {
    private String label;
    private String text;
    String property;
//    static private int length = 75;
    JTextField textField;

    public JPanel getPane(int length) {
      if (label == null) {
        throw new BuildException("'label' attribute is required.");
      }
      if (property == null || property.length() == 0) {
        throw new BuildException("'property' attribute is required.");
      }

      JPanel pane = new JPanel();
      pane.setLayout(new BorderLayout());
      pane.add(new JLabel(label), "West");
      textField = new JTextField(text, length);
      pane.add(textField, "East");
      return pane;
    }

    public void ok() {
      final String text = textField.getText().trim();
      getProject().setProperty(property, text);
      log("Set '" + property + "' to '" + text + "'", Project.MSG_VERBOSE);
    }

    public void setLabel(String label) {
      this.label = label;
    }

    public void setProperty(String property) {
      this.property = property;
    }

    public void setText(String text) {
      this.text = text;
    }
  }

}