package client.view.base;

import client.controller.BaseControllerUser;
import client.controller.ControllerUser;
import client.view.MainFrame;
import client.view.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Panel used to register a new user
 * Created by Chiara Varini on 12/07/17.
 */
public class RegistrationDialog extends JDialog {

    private static final Color BACKGROUND_COLOR = new Color(67, 90, 108);

    private final int lenghtTextField = 30;
    private final JPanel infoPanel = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    public RegistrationDialog(final JFrame frame){
        super(frame, "Registration", true);
        if (frame != null) {

            Dimension frameDim = frame.getSize();
            setMinimumSize(new Dimension((int) frameDim.getWidth() / 2, (int) frameDim.getHeight() / 3));
            setLocationRelativeTo(null);

            final JTextField name = new JTextField(lenghtTextField);
            final JTextField email = new JTextField(lenghtTextField);
            final JTextField username = new JTextField(lenghtTextField);
            final JPasswordField password = new JPasswordField(lenghtTextField);
            final JPasswordField confPassword = new JPasswordField(lenghtTextField);
            final JButton registration = new JButton("Registration");
            final JLabel errLabel = new JLabel("Data error");
            errLabel.setForeground(Color.RED);
            errLabel.setFont(new Font(errLabel.getFont().getFontName(), Font.BOLD, 15));
            errLabel.setVisible(false);

            infoPanel.setBackground(BACKGROUND_COLOR);
            gbc.ipady = 5;
            gbc.ipadx = 10;
            gbc.anchor = GridBagConstraints.LINE_END;

            addField(0, "Name", name);
            addField(1, "Email", email);
            addField(2, "Username", username);
            addField(3, "Password", password);
            addField(4, "Confirm Password", confPassword);
            addField(5, "", errLabel);

            gbc.gridy = 6;
            gbc.gridx = 2;
            infoPanel.add(registration, gbc);

            registration.addActionListener(e -> {
                boolean registrationResult = BaseControllerUser.registration(name.getText(), username.getText(), email.getText(),
                        Utils.transformInString(password.getPassword()), Utils.transformInString(confPassword.getPassword()));
                if(!registrationResult){
                    errLabel.setVisible(true);
                    revalidate();
                    repaint();

                } else { //TODO mandare mail di notifica di registrazione?
                    MainFrame.getInstance().setContentPane(new HomePanel(username.getText()));
                    BaseControllerUser.login(username.getText(),Utils.transformInString(password.getPassword()));
                    dispose();
                }
            });

            getContentPane().add(infoPanel);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);

            addWindowListener(onClose());
        }
    }

    private void addField( int y, String nameField, JComponent component){
        gbc.gridx = 0;
        gbc.gridy = y;
        infoPanel.add(new JLabel(nameField), gbc);
        gbc.gridx = 1;
        infoPanel.add(component, gbc);
    }

    private WindowAdapter onClose() {
        return new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                dispose();
            }
        };
    }
}
