package org.cgtools.anttasks.ant;

import org.apache.tools.ant.BuildException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class ValidableFrame extends JFrame {
  protected boolean cancel = false;
  protected boolean closeGuiBeforeProceed = false;
  protected ValidationChoice choice;

  public void view() {
    setSize(600, 600);

    getContentPane().add(getUserPane(), BorderLayout.CENTER);

    choice = new ValidationChoice(cancel);
    getContentPane().add(choice, BorderLayout.SOUTH);

    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public abstract JPanel getUserPane();

  public void waitClose() {
    int result = 0;
    try {
      result = choice.waitForChoice();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    switch (result) {
      case ValidationChoice.OK:
        if (closeGuiBeforeProceed) {
          closeFrame();
        }
        proceed();
        if (!closeGuiBeforeProceed) {
          closeFrame();
        }
        break;
      case ValidationChoice.CANCEL:
        closeFrame();
        throw new BuildException("Cancel");
      default:
        throw new BuildException("Internal Error");
    }
  }

  public abstract void proceed();

  private void closeFrame() {
    setVisible(false);
    dispose();
  }


  public static class ValidationChoice extends JPanel {
    public static final int OK = 0;
    public static final int CANCEL = 1;

    Object sync = new Object();
    int result;

    public ValidationChoice(boolean cancel) {
      addButton(new Action(OK, "OK"), "West");
      if (cancel) {
        addButton(new Action(CANCEL, "Cancel"), "East");
      }
    }

    public int waitForChoice() throws InterruptedException {
      synchronized (sync) {
        sync.wait();
      }
      return result;
    }

    private void addButton(final Action action, final String pos) {
      add(new JButton(new AbstractAction(action.name) {
        public void actionPerformed(ActionEvent e) {
          result = action.val;
          synchronized (sync) {
            sync.notifyAll();
          }
        }
      }), pos);
    }

    public static class Action {
      int val;
      String name;

      public Action(int val, String name) {
        this.val = val;
        this.name = name;
      }
    }
  }


}
