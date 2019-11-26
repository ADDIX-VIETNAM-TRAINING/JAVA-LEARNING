/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogdemo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import jdk.nashorn.internal.scripts.JO;

/**
 *
 * @author Thuan.Truong
 */
public class DialogDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Frame");
        JTabbedPane panel = new JTabbedPane();
        frame.add(panel);
        panel.addTab("Border Layout", createBorderLayout());
        panel.addTab("Box Layout", createBoxLayout());
        panel.addTab("Card Layout", createCardLayout());
        panel.addTab("Flow Layout", createFlowLayout());
        panel.addTab("Grid Layout", new JPanel());
        panel.addTab("GridBag Layout", new JPanel());
        panel.addTab("Group Layout", createGroupLayout());
        panel.addTab("Spring Layout", createSpringLayout());
        panel.addTab("Dialog Demo", createDialogDemoLayout());
        frame.setSize(400, 400);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Create the panel contain the BorderLayout
     * @return JPanel
     */
    public static JPanel createBorderLayout() {
       JPanel panel = new JPanel(new BorderLayout());
       panel.add(new JButton("North"), BorderLayout.NORTH);
       panel.add(new JButton("South"), BorderLayout.SOUTH);
       panel.add(new JButton("Center"), BorderLayout.CENTER);
       panel.add(new JButton("West"), BorderLayout.WEST);
       panel.add(new JButton("East"), BorderLayout.EAST);
       return panel;
    }

    /**
     * Create the panel contain the BoxLayout
     * @return JPanel
     */
    public static JPanel createBoxLayout() {
       JPanel panel = new JPanel();
       panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       panel.add(new JButton("North"));
       panel.add(new JButton("South"));
       panel.add(new JButton("Center"));
       panel.add(new JButton("West"));
       panel.add(new JButton("East"));
       return panel;
    }

    /**
     * Create the panel contain the CardLayout
     * @return JPanel
     */
    public static JPanel createCardLayout() {
       JPanel panel = new JPanel(new BorderLayout());
       JPanel cards = new JPanel(new CardLayout());
       JPanel card1 = new JPanel();
       JPanel card2 = new JPanel();
       JPanel comboBoxPane = new JPanel();
       String comboBoxItems[] = { "Card 1", "Card 2" };
       JComboBox comboBox = new JComboBox(comboBoxItems);
       
       card1.add(new JLabel("Card 1: Content"));
       card2.add(new JLabel("Card 2: Content"));
       comboBox.setEditable(false);
       comboBox.addItemListener(e -> {
           ((CardLayout) cards.getLayout()).show(cards, (String) e.getItem());
       });
       comboBoxPane.add(comboBox);
       cards.add(card1, "Card 1");
       cards.add(card2, "Card 2");
       panel.add(comboBoxPane, BorderLayout.PAGE_START);
       panel.add(cards, BorderLayout.CENTER);
       return panel;
    }

    /**
     * Create the panel contain the FlowLayout
     * @return JPanel
     */
    public static JPanel createFlowLayout() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel flowPanle = new JPanel(new FlowLayout());
        flowPanle.add(new JButton("North"));
        flowPanle.add(new JButton("South"));
        flowPanle.add(new JButton("Center"));
        flowPanle.add(new JButton("West"));
        flowPanle.add(new JButton("East"));
        
        flowPanle.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);
        JButton changeSetting = new JButton("Left to Right");
        changeSetting.addActionListener(e -> {
            if ("Left to Right".equals(changeSetting.getText())) {
                changeSetting.setText("Right to Left");
                flowPanle.setComponentOrientation(
                ComponentOrientation.RIGHT_TO_LEFT);
            } else {
                changeSetting.setText("Left to Right");
                flowPanle.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);
            }
        });
        panel.add(changeSetting, BorderLayout.LINE_START);
        panel.add(flowPanle, BorderLayout.CENTER);
        return panel;
    }
    
    /**
     * Create the panel contain the GroupLayout
     * @return JPanel
     */
    public static JPanel createGroupLayout() {
        JPanel panel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(panel);
        
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                .addComponent(button1)
                .addComponent(button2)
                .addGroup(
                    groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(button3)      
                    .addComponent(button4)
                )
        );
        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                .addGroup(
                    groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(button1)
                    .addComponent(button2)
                    .addComponent(button3)
                )
                .addComponent(button4)
        );
        panel.setLayout(groupLayout);
        return panel;
    }

    /**
     * Create the panel contain the SpringLayout
     * @return JPanel
     */
    public static JPanel createSpringLayout() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Label: ");
        SpringLayout layout = new SpringLayout();
        JTextField textField = new JTextField("My Text Field", 15);  
        panel.add(label);  
        panel.add(textField);
        panel.setLayout(layout);
        
        layout.putConstraint(SpringLayout.WEST, label,6,SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, label,6,SpringLayout.NORTH, panel);  
        layout.putConstraint(SpringLayout.WEST, textField,6,SpringLayout.EAST, label);  
        layout.putConstraint(SpringLayout.NORTH, textField,6,SpringLayout.NORTH, panel);  
        layout.putConstraint(SpringLayout.EAST, panel,6,SpringLayout.EAST, textField);  
        layout.putConstraint(SpringLayout.SOUTH, panel,6,SpringLayout.SOUTH, textField);
        
        return panel;
    }
    
    /**
     * Create the panel contain the SpringLayout
     * @return JPanel
     */
    public static JPanel createDialogDemoLayout() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        JButton btnDialog1 = new JButton("Dialog 1");
        JButton btnDialog2 = new JButton("Dialog 2");
        JButton btnDialog3 = new JButton("Dialog 3");
        panel.add(btnDialog1);
        panel.add(btnDialog2);
        panel.add(btnDialog3);
        
        
        btnDialog1.addActionListener(e -> {
            createDialog1(panel);
        });
        
        btnDialog2.addActionListener(e -> {
            createDialog2(panel);
        });
        
        btnDialog3.addActionListener(e -> {
            createDialog3(panel);
        });
        return panel;
    }
    
    /**
     * Create Dialog 1 (Login dialog)
     * @param panel 
     */
    public static void createDialog1 (JPanel panel) {
        JDialog dialog = new JDialog();
            JPanel dialogPanel = new JPanel();
            GroupLayout diGroupLayout = new GroupLayout(dialogPanel);
            diGroupLayout.setAutoCreateGaps(true);
            diGroupLayout.setAutoCreateContainerGaps(true);
            
            /* Dialog componences*/
            JLabel label1 = new JLabel("User:");
            JLabel label2 = new JLabel("Password:");
            JTextField textField1 = new JTextField();
            JPasswordField textField2 = new JPasswordField();
            JButton btnOK = new JButton("OK");
            JButton btnCancel = new JButton("Cancel");
            
            btnOK.addActionListener(e1 -> {
                JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(panel),
                    "User: " + textField1.getText() + "\n Password: " + textField2.getText());
            });
            btnCancel.addActionListener(e1 -> {
                dialog.dispose();
            });
            
            diGroupLayout.setHorizontalGroup(
                    diGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(
                        diGroupLayout.createSequentialGroup()
                        .addComponent(label1)
                        .addComponent(textField1)
                    )
                    .addGroup(
                        diGroupLayout.createSequentialGroup()
                        .addComponent(label2)
                        .addComponent(textField2)
                    )
                    .addGroup(
                        diGroupLayout.createSequentialGroup()
                        .addComponent(btnOK)
                        .addComponent(btnCancel)
                    )
            );
            diGroupLayout.setVerticalGroup(
                    diGroupLayout.createSequentialGroup()
                    .addGroup(
                        diGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(textField1)
                    )
                    .addGroup(
                        diGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(textField2)
                    )
                    .addGroup(
                        diGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOK)
                        .addComponent(btnCancel)
                    )
            );
            dialogPanel.setLayout(diGroupLayout);
            dialog.add(dialogPanel);
            dialog.setSize(300, 150);
            dialog.setTitle("Dialog 1");
            dialog.setResizable(false);
            dialog.setVisible(true);
    }

    /**
     * Create Dialog 2 (Slider Dialog)
     * @param panel 
     */
    public static void createDialog2 (JPanel panel) {
        JDialog dialog = new JDialog();
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        JButton btnOK = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        btnOK.addActionListener(e1 -> {
            JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(panel),
                "Value is " + slider.getValue());
        });
        btnCancel.addActionListener(e1 -> {
            dialog.dispose();
        });

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridwidth = 4;
        dialogPanel.add(slider, constraint);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.gridwidth = 2;
        dialogPanel.add(btnOK, constraint);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 3;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        dialogPanel.add(btnCancel, constraint);

        dialog.add(dialogPanel);
        dialog.setSize(300, 150);
        dialog.setTitle("Dialog 2");
        dialog.setResizable(false);
        dialog.setVisible(true);
    }
     /**
     * Create Dialog 3 (Dynamic create textField Dialog)
     * @param panel 
     */
    public static void createDialog3 (JPanel panel) {
        String s = (String)JOptionPane.showInputDialog(
                (JFrame) SwingUtilities.getWindowAncestor(panel),
                "Input value: ",
                "Dialog 3.1",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                "3");
        if ((s != null) && (s.length() > 0)) {
            JDialog dialog = new JDialog();
            JPanel dialogPanel = new JPanel();
            dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
            SpringLayout layout = new SpringLayout();
            JButton btnOK = new JButton("OK");
            JButton btnCancel = new JButton("Cancel");

            int number = Integer.parseInt(s);
            int size =  (number + 1) * 35;
            for (int i = 0; i < number; i++) {
                dialogPanel.add(new TextField("" + (i + 1)));
            }
            btnOK.addActionListener(e1 -> {

                int sum = 0;
                for (int i = 0; i < number; i++) {
                    TextField textField = (TextField) dialogPanel.getComponent(i);
                    sum += Integer.parseInt(textField.getText());
                }
            JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(panel),
                "Value is " + sum);
            });
            btnCancel.addActionListener(e1 -> {
                dialog.dispose();
            });
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(layout);
            layout.putConstraint(SpringLayout.WEST, btnOK, 100, SpringLayout.WEST, dialogPanel);
            layout.putConstraint(SpringLayout.WEST, btnCancel, 10, SpringLayout.EAST, btnOK);

            bottomPanel.add(btnOK);
            bottomPanel.add(btnCancel);
            dialogPanel.add(bottomPanel);
            dialog.add(dialogPanel);
            System.out.println("Debugg: " + btnOK.getY() + btnOK.getWidth());
            dialog.setSize(300, size);
            dialog.setTitle("Dialog 3.2");
            dialog.setResizable(false);
            dialog.setVisible(true);
        }
    }
}
