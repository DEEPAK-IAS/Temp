import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AWTLoginPage {

    // Constructor
    public AWTLoginPage() {
        Frame frame = new Frame("Login Page");
        frame.setSize(300, 200);
        frame.setLayout(null);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        Label emailLabel = new Label("Email:");
        emailLabel.setBounds(50, 50, 60, 20);
        TextField emailField = new TextField();
        emailField.setBounds(120, 50, 130, 20);

        Label passLabel = new Label("Password:");
        passLabel.setBounds(50, 80, 60, 20);
        TextField passField = new TextField();
        passField.setEchoChar('*');
        passField.setBounds(120, 80, 130, 20);

        Button loginButton = new Button("Login");
        loginButton.setBounds(100, 120, 80, 30);

        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginButton);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = passField.getText();
            Connection conn = db.getConnection();
            try {
                PreparedStatement stmd = conn.prepareStatement("select * from admin");

                ResultSet rs = stmd.executeQuery();


                while (rs.next()) {
                    String dbEmail = rs.getString("email");
                    String dbPassword = rs.getString("password");

                    if (email.equals(dbEmail) && password.equals(dbPassword)) {
                        frame.dispose();
                        AWTUserForm userForm = new AWTUserForm();
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AWTLoginPage();
    }
}
