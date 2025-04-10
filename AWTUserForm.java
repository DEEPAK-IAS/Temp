import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import javax.swing.JOptionPane;


public class AWTUserForm {
    public AWTUserForm() {
        Connection conn = db.getConnection();
        connectTable(conn);
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

        saveBtn.addActionListener(e -> {
            Map<String, String> user = Map.of(
                    "id", idField.getText(),
                    "name", nameField.getText(),
                    "email", emailField.getText(),
                    "password", passwordField.getText()
            );
            addUser(user, conn);
        });
        updateBtn.addActionListener(e -> System.out.println("Updated: " + idField.getText()));
        deleteBtn.addActionListener(e -> {
            deleteUser(conn);
        });
        showBtn.addActionListener(e -> {
           showUser(conn);
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    
    void connectTable(Connection conn) {
        try {

            PreparedStatement stmd = conn.prepareStatement("create table if not exists users(id int primary key, name varchar(50), email varchar(50), password varchar(50))");
            stmd.executeUpdate();
            System.out.println("Table created successfully");

        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }


    void addUser(Map<String, String> user, Connection conn) {
        try {
    
            PreparedStatement stmd = conn.prepareStatement("insert into users(id, name, email, password) values(?, ?, ?, ?)");
            stmd.setInt(1, Integer.parseInt(user.get("id")));
            stmd.setString(2, (String) user.get("name"));
            stmd.setString(3, (String) user.get("email"));
            stmd.setString(4, (String) user.get("password"));
            stmd.executeUpdate();
            System.out.println("User added successfully");

        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }


    void deleteUser(Connection conn) {
        try {
            String id = JOptionPane.showInputDialog(null, "Hello! This is a simple dialog.", "Simple Dialog", JOptionPane.INFORMATION_MESSAGE);
            PreparedStatement stmd = conn.prepareStatement("delete from users where id = ?");
            stmd.setInt(1, Integer.parseInt(id));
            stmd.executeUpdate();
            System.out.println("User deleted successfully");
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }


    void showUser(Connection conn) {
        try {
            PreparedStatement stmd = conn.prepareStatement("select * from users");
            ResultSet rs = stmd.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Password: " + rs.getString("password"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
