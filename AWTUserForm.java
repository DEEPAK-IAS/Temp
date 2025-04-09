import java.awt.*;
import java.awt.event.*;

public class AWTUserForm {
    public AWTUserForm() {
        Frame frame = new Frame("User Form");

        frame.setSize(400, 350);
        frame.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        Label heading = new Label("User Registration Form", Label.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        heading.setBounds(70, 40, 260, 30);
        frame.add(heading);

        Label idLabel = new Label("ID:");
        idLabel.setBounds(50, 90, 80, 20);
        frame.add(idLabel);

        Label nameLabel = new Label("Name:");
        nameLabel.setBounds(50, 120, 80, 20);
        frame.add(nameLabel);

        Label emailLabel = new Label("Email:");
        emailLabel.setBounds(50, 150, 80, 20);
        frame.add(emailLabel);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setBounds(50, 180, 80, 20);
        frame.add(passwordLabel);

        TextField idField = new TextField();
        idField.setBounds(140, 90, 200, 20);
        frame.add(idField);

        TextField nameField = new TextField();
        nameField.setBounds(140, 120, 200, 20);
        frame.add(nameField);

        TextField emailField = new TextField();
        emailField.setBounds(140, 150, 200, 20);
        frame.add(emailField);

        TextField passwordField = new TextField();
        passwordField.setEchoChar('*');
        passwordField.setBounds(140, 180, 200, 20);
        frame.add(passwordField);

        Button saveBtn = new Button("Save");
        saveBtn.setBounds(50, 230, 70, 30);
        frame.add(saveBtn);

        Button updateBtn = new Button("Update");
        updateBtn.setBounds(130, 230, 70, 30);
        frame.add(updateBtn);

        Button deleteBtn = new Button("Delete");
        deleteBtn.setBounds(210, 230, 70, 30);
        frame.add(deleteBtn);

        Button showBtn = new Button("Show");
        showBtn.setBounds(290, 230, 70, 30);
        frame.add(showBtn);

        saveBtn.addActionListener(e -> System.out.println("Saved: " + nameField.getText()));
        updateBtn.addActionListener(e -> System.out.println("Updated: " + idField.getText()));
        deleteBtn.addActionListener(e -> System.out.println("Deleted: " + idField.getText()));
        showBtn.addActionListener(e -> {
            System.out.println("ID: " + idField.getText());
            System.out.println("Name: " + nameField.getText());
            System.out.println("Email: " + emailField.getText());
            System.out.println("Password: " + passwordField.getText());
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
