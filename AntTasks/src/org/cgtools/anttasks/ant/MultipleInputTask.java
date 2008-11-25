package org.cgtools.anttasks.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import sun.awt.VerticalBagLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;

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


  public void addInputText(TextInputLine line) {
    lines.add(line);
  }

  public void addInputFile(FileChooserInputLine line) {
    lines.add(line);
  }

  public void setLength(final int length) {
    this.length = length;
  }

  public abstract static class InputLine extends ProjectComponent {
    private String label;
    String property;

    public JPanel getPane(int length) {
      if (label == null) {
        throw new BuildException("'label' attribute is required.");
      }
      if (property == null || property.length() == 0) {
        throw new BuildException("'property' attribute is required.");
      }

      JPanel pane = new JPanel();
      pane.setLayout(new BorderLayout());
      pane.add(new JLabel(label), BorderLayout.WEST);
      pane.add(getInputPane(length), BorderLayout.CENTER);
      return pane;
    }

    abstract Component getInputPane(int length);

    public void ok() {
      final String value = getInputValue();
      getProject().setProperty(property, value);
      log("Set '" + property + "' to '" + value + "'", Project.MSG_VERBOSE);
    }

    abstract String getInputValue();

    public void setLabel(String label) {
      this.label = label;
    }

    public void setProperty(String property) {
      this.property = property;
    }

  }

  public static class TextInputLine extends InputLine {
    JTextField textField;
    String text;

    Component getInputPane(int length) {
      textField = new JTextField(text, length);
      return textField;
    }

    public String getInputValue(){
      return textField.getText().trim();
    }

    public void setText(String text) {
      this.text = text;
    }

  }

  public static class FileChooserInputLine extends InputLine implements ActionListener {
    private JTextField textField;
    private JPanel userPane;
    File file;

    Component getInputPane(int length) {
      userPane = new JPanel(new BorderLayout());
      textField = new JTextField(length);
      setTextField();
      userPane.add(textField, BorderLayout.CENTER);
      JButton chooseButton = new JButton("Choose");
      chooseButton.addActionListener(this);
      userPane.add(chooseButton, BorderLayout.EAST);
      return userPane;
    }

    private void setTextField(){
      String text = file==null?"":file.getAbsolutePath();
      textField.setEditable(true);
      textField.setText(text);
      textField.setEditable(false);
    }

    String getInputValue() {
      return textField.getText().trim();
    }

    public void actionPerformed(ActionEvent e) {
      JFileChooser fileChooser = new JFileChooser(getInputValue());
      int result = fileChooser.showOpenDialog(userPane);
      if(result==JFileChooser.ERROR_OPTION){
        throw new BuildException("Error while selecting file");
      }
      if(result!=JFileChooser.APPROVE_OPTION){
        return;
      }

      file = fileChooser.getSelectedFile();
      setTextField();
    }

    public void setFile(File file) {
      this.file = file;
      log("File is set to '" + this.file + "'", Project.MSG_VERBOSE);

    }
  }

}