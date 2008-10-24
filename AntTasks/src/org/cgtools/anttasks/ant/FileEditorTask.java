package org.cgtools.anttasks.ant;

import org.apache.tools.ant.BuildException;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.*;

import org.cgtools.anttasks.ant.ValidableInputTask;

public class FileEditorTask extends ValidableInputTask {
  private JTextPane textPane;
  private File file;
  private boolean create = true;
  private int width = 1000;
  private int heigth = 600;
  private String font = "courier";
  private int fontSize = 14;

  protected void precheck() {
    if (file == null) {
      throw new BuildException("'file' attribute is required");
    }
    if (!file.exists() && !create) {
      throw new BuildException("File does not exist and creation is not authorized");
    }
  }

  protected JComponent getUserPane() throws IOException {
    textPane = new JTextPane();
    textPane.setFont(new Font(font, Font.PLAIN, fontSize));
    textPane.setForeground(Color.black);
    textPane.setEditable(true);

    if (file.exists()) {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      StringBuffer text = new StringBuffer();
      String line;
      while ((line = reader.readLine()) != null) {
        text.append(line).append('\n');
      }
      textPane.setText(text.toString());
    }
    return new JScrollPane(textPane);
  }

  protected void proceed() throws IOException, BadLocationException {
    final FileWriter writer = new FileWriter(file);
    writer.write(textPane.getDocument().getText(0, textPane.getDocument().getLength()));
    writer.flush();
    writer.close();
  }

  protected void setSize(JFrame mainFrame) {
    mainFrame.setSize(width, heigth);
  };

  public void setFile(final File file) {
    this.file = file;
  }

  public void setCreate(final boolean create) {
    this.create = create;
  }

  public void setWidth(final int width) {
    this.width = width;
  }

  public void setHeigth(final int heigth) {
    this.heigth = heigth;
  }

  public void setFont(final String font) {
    this.font = font;
  }

  public void setFontSize(final int fontSize) {
    this.fontSize = fontSize;
  }


}