package jlogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import static javax.swing.UIManager.get;
import static javax.swing.UIManager.get;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class JLogin extends JFrame implements ActionListener {

    private JButton iniciarSesion;
    private JButton cerrarSesion;
    private JButton eliminarUsuario;
    private JButton editarUsuario;
    private JButton editarUsuario2;
    private JButton agregarUsuario;
    private JButton agregarUsuario2;
    private JButton cambiarContrasena;
    private JButton volver;
    
    //Logeo
    private JTextField textfieldNombreUsuario; //Login
    private JTextField textfieldUser;
    private JTextField textfieldNombreReal;
    private JTextField textfieldEmail;
    private JPasswordField textfieldPass;
    private JPasswordField textfieldPass1; //Cambiar Contraseña
    private JPasswordField textfieldPass2; //Cambiar Contraseña
    private JPasswordField textfieldContrasena; //Login

    private String usuarioL;
    private String passL;

    private JLabel errorMsgU;
    private JLabel errorMsgP;
    private JFrame mainWindow, addUserWindow, editUserWindow, passEditWindow, infoWindow;

    private JLogin window;

    private JMenuBar barraMenu;
    private JMenu menuArchivo, menuAyuda;
    private JMenuItem infoUsuario, cambiarPass, salir, equipoDesarrollo;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Usuario> usuarios;

    private int currentRow;

    public JLogin() {

        super("Formulario de Inicio de Sesión");

        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(600, 600);
        super.setMinimumSize(new Dimension(400, 400));
        setIconImage(new ImageIcon("icon.png").getImage());
        ImageIcon image = new ImageIcon("banner.png");
        JLabel banner = new JLabel(image);
        JPanel panelBanner = new JPanel();
        panelBanner.add(banner);
        panelBanner.setBackground(new Color(190, 11, 103));

        JPanel primeraFila = new JPanel(new BorderLayout());
        JLabel labelNombre = new JLabel("Nombre de usuario");
        JLabel labelNombre2 = new JLabel("     ");
        labelNombre.setPreferredSize(new Dimension(120, 24));
        this.textfieldNombreUsuario = new JTextField();
        textfieldNombreUsuario.setPreferredSize(new Dimension(200, 24));

        JPanel panelTextFieldNombre = new JPanel();
        panelTextFieldNombre.add(textfieldNombreUsuario);

        primeraFila.add(labelNombre, BorderLayout.LINE_START);
        primeraFila.add(panelTextFieldNombre, BorderLayout.CENTER);

        JPanel userErr = new JPanel(new BorderLayout());
        errorMsgU = new JLabel("Ingrese usuario");
        JLabel emptyUser = new JLabel("");
        emptyUser.setPreferredSize(new Dimension(100, 24));
        errorMsgU.setForeground(Color.red);
        errorMsgU.setVisible(false);
        userErr.add(emptyUser, BorderLayout.LINE_START);
        userErr.add(errorMsgU, BorderLayout.CENTER);

        JPanel segundaFila = new JPanel(new BorderLayout());
        JLabel labelContrasena = new JLabel("Contraseña");
        labelContrasena.setPreferredSize(new Dimension(120, 24));
        this.textfieldContrasena = new JPasswordField();
        textfieldContrasena.setPreferredSize(new Dimension(200, 24));

        JPanel panelTextFieldPass = new JPanel();
        panelTextFieldPass.add(textfieldContrasena);

        segundaFila.add(labelContrasena, BorderLayout.LINE_START);
        segundaFila.add(panelTextFieldPass, BorderLayout.CENTER);

        JPanel passErr = new JPanel(new BorderLayout());
        errorMsgP = new JLabel("Ingrese contraseña");
        JLabel emptyPass = new JLabel("");
        emptyPass.setPreferredSize(new Dimension(100, 24));
        errorMsgP.setForeground(Color.red);
        errorMsgP.setVisible(false);
        passErr.add(emptyPass, BorderLayout.LINE_START);
        passErr.add(errorMsgP, BorderLayout.CENTER);

        JPanel leyenda = new JPanel(new BorderLayout());

        JLabel legend = new JLabel("Ingenieria En Desarrollo de Videojuegos y Realidad Virtual");
        //JLabel legend2 = new JLabel("                                           ");
        legend.setPreferredSize(new Dimension(100, 24));
        legend.setForeground(Color.GRAY);
        //leyenda.add(legend2,BorderLayout.LINE_START);
        leyenda.add(legend, BorderLayout.CENTER);

        this.iniciarSesion = new JButton("Iniciar sesión");
        iniciarSesion.addActionListener(this);
        iniciarSesion.setBackground(Color.BLUE);
        iniciarSesion.setForeground(Color.WHITE);
        iniciarSesion.setPreferredSize(new Dimension(200, 24));
        JPanel panelIniciarSesion = new JPanel();
        panelIniciarSesion.add(iniciarSesion);

        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(6, 1, 5, 5));
        formulario.add(primeraFila);
        formulario.add(userErr);
        formulario.add(segundaFila);
        formulario.add(passErr);
        formulario.add(panelIniciarSesion);
        formulario.add(leyenda);

        JPanel centro = new JPanel();
        centro.setBorder(new EmptyBorder(20, 20, 20, 20));
        centro.add(formulario);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(panelBanner, BorderLayout.PAGE_START);
        panel.add(centro, BorderLayout.CENTER);

        super.getContentPane().add(panel);

        loadUsuarios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.iniciarSesion) {
            boolean usuarioExiste = false;
            usuarioL = this.textfieldNombreUsuario.getText();
            passL = this.textfieldContrasena.getText();
            //passL = this.textfieldPass.getText();
            if (usuarioL.equals("") && passL.equals("")) {
                errorMsgU.setVisible(true);
                errorMsgP.setVisible(true);
                textfieldNombreUsuario.requestFocus();
            } else if (usuarioL.equals("")) {
                errorMsgU.setVisible(true);
                errorMsgU.setText("Ingrese Usuario");
                textfieldNombreUsuario.requestFocus();
            } else if (passL.equals("")) {
                errorMsgP.setVisible(true);
                errorMsgP.setText("Ingrese Contraseña");
                textfieldContrasena.requestFocus();
            }

            for (int i = 0; i < this.usuarios.size(); i++) {
                if (this.usuarios.get(i).getUsuario().equals(usuarioL) && this.usuarios.get(i).getPass().equals(passL)) {
                    mainWindow = openWindow();
                    this.dispose();
                    usuarioExiste = true;
                    break;
                }
            }

            if (!usuarioExiste) {
                JOptionPane.showMessageDialog(this, "Usuario no registrado", "JLogin", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == this.cerrarSesion) {
            mainWindow.dispose();
            window = new JLogin();
            window.setVisible(true);
        }

        if (e.getSource() == this.volver) {
            infoWindow.dispose();
            mainWindow = openWindow();
            mainWindow.setVisible(true);
        }
        if (e.getSource() == this.editarUsuario2) {
            try {
                String user = textfieldUser.getText();
                String nombre = textfieldNombreReal.getText();
                String email = textfieldEmail.getText();
                String pass = textfieldPass.getText();
                model.setValueAt(user, currentRow, 0);
                model.setValueAt(nombre, currentRow, 1);
                model.setValueAt(email, currentRow, 2);
                model.setValueAt(pass, currentRow, 3);
                System.out.println("Participante editado correctamente.");
            } catch (Exception er) {
                System.out.println("No se ha podido editar correctamente.");
            }
            saveUsuarios();
            editUserWindow.dispose();
            mainWindow = openWindow();
            mainWindow.setVisible(true);

        }

        if (e.getSource() == this.agregarUsuario2) {
            //Añadir usuario acá
            //String user = textfieldUser.getText();
            //String nombre = textfieldNombreReal.getText();
            //String pass = textfieldPass.getText();
            //String email = textfieldEmail.getText();

            try {
                boolean userExist = false;
                String user = textfieldUser.getText();
                String nombre = textfieldNombreReal.getText();
                String pass = textfieldPass.getText();
                String email = textfieldEmail.getText();
                for (int i = 0; i < this.model.getRowCount(); i++) {
                    String usuario = (String) this.model.getValueAt(i, 0);
                    if (usuario.equals(user)) {
                        userExist = true;
                        break;
                    }
                }
                if (userExist) {
                    JOptionPane.showMessageDialog(this, "Nombre de usuario en uso", "Agregar Usuario", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Nombre de usuario en uso.");

                } else {
                    model.addRow(new String[]{user, nombre, email, pass});
                    System.out.println("Participante ingresad@ correctamente.");
                    saveUsuarios();
                    addUserWindow.dispose();
                    mainWindow = openWindow();
                    mainWindow.setVisible(true);
                }
            } catch (Exception er) {
                System.out.println("No se ha podido ingresar correctamente.");
            }
        }

        if (e.getSource() == this.eliminarUsuario) {
            DefaultTableModel model = (DefaultTableModel) this.table.getModel();
            int row = table.getSelectedRow();
            model.removeRow(row);
            saveUsuarios();
        }
        if (e.getSource() == this.cambiarContrasena) {
            try {
                String pass = textfieldPass2.getText();
                model.setValueAt(pass, getUserRow(), 3);
                System.out.println("Contraseña editada correctamente.");
            } catch (Exception er) {
                System.out.println("No se ha podido editar la contraseña correctamente.");
            }
            saveUsuarios();
            passEditWindow.dispose();
            mainWindow = openWindow();
            mainWindow.setVisible(true);
        }

    }

    JFrame openWindow() {
        this.barraMenu = new JMenuBar();
        this.menuArchivo = new JMenu("Archivo");
        this.menuAyuda = new JMenu("Ayuda");
        this.infoUsuario = new JMenuItem("Información usuario");
        this.cambiarPass = new JMenuItem("Cambiar contraseña");
        this.salir = new JMenuItem("Salir");
        this.equipoDesarrollo = new JMenuItem("Acerca del Equipo de Desarrollo");
        this.cerrarSesion = new JButton("Cerrar");
        this.eliminarUsuario = new JButton("Eliminar");
        this.editarUsuario = new JButton("Editar");
        this.agregarUsuario = new JButton("Agregar");

        cerrarSesion.addActionListener(this);
        cerrarSesion.setBackground(Color.RED);
        cerrarSesion.setForeground(Color.WHITE);

        eliminarUsuario.addActionListener(this);
        eliminarUsuario.setBackground(Color.GRAY);
        eliminarUsuario.setForeground(Color.WHITE);
        eliminarUsuario.setEnabled(false);

        editarUsuario.addActionListener(this);
        editarUsuario.setBackground(Color.GRAY);
        editarUsuario.setForeground(Color.WHITE);
        editarUsuario.setEnabled(false);

        agregarUsuario.addActionListener(this);
        agregarUsuario.setBackground(Color.BLUE);
        agregarUsuario.setForeground(Color.WHITE);

        this.barraMenu.add(this.menuArchivo);
        this.menuArchivo.add(this.infoUsuario);
        this.menuArchivo.add(this.cambiarPass);
        this.menuArchivo.add(this.salir);

        this.barraMenu.add(this.menuAyuda);
        this.menuAyuda.add(this.equipoDesarrollo);

        this.programaEventos();

        ImageIcon image = new ImageIcon("banner2.png");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);

        JLabel banner = new JLabel(image);
        JPanel upperPanel = new JPanel();
        JPanel upperPanel2 = new JPanel();
        JPanel upperPanel3 = new JPanel();
        upperPanel.setLayout(new GridLayout(1, 1, 0, 0));

        upperPanel3.add(banner);

        upperPanel.add(upperPanel3);

        frame.setJMenuBar(barraMenu);
        frame.add(upperPanel, BorderLayout.NORTH);
        upperPanel3.setBackground(new Color(190, 11, 103));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 0, 0));

        cerrarSesion.setPreferredSize(new Dimension(100, 24));
        JPanel cerrarSesionPanel = new JPanel();
        cerrarSesionPanel.add(cerrarSesion);

        eliminarUsuario.setPreferredSize(new Dimension(100, 24));
        JPanel eliminarUsuarioPanel = new JPanel();
        eliminarUsuarioPanel.add(eliminarUsuario);

        editarUsuario.setPreferredSize(new Dimension(100, 24));
        JPanel editarUsuarioPanel = new JPanel();

        editarUsuarioPanel.add(eliminarUsuario);

        agregarUsuario.setPreferredSize(new Dimension(100, 24));
        JPanel agregarUsuarioPanel = new JPanel();
        agregarUsuarioPanel.add(cerrarSesion);
        agregarUsuarioPanel.add(eliminarUsuario);
        agregarUsuarioPanel.add(editarUsuario);
        agregarUsuarioPanel.add(agregarUsuario);

        buttonPanel.setBorder(new EmptyBorder(0, -850, 0, -10));
        buttonPanel.add(cerrarSesionPanel);
        buttonPanel.add(eliminarUsuarioPanel);
        buttonPanel.add(editarUsuarioPanel);
        buttonPanel.add(agregarUsuarioPanel);

        // tablemodel
        model = new DefaultTableModel(new String[]{"Usuario", "Nombre", "Email", "Contraseña"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        /*t
        Object rowData[][] = { { "Admin", "Administrador", "a@a" , "Admin1234"},
        { "Row2-Column1", "Row2-Column2", "Row2-Column3","Row2-Column4" } };
        Object columnNames[] = { "Usuario", "Nombre", "Email", "Contraseña" };
         */
        table = new JTable(model);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row}
                currentRow = table.getSelectedRow();
                if (currentRow > -1) {
                    editarUsuario.setEnabled(true);
                    eliminarUsuario.setEnabled(true);
                } else {
                    editarUsuario.setEnabled(false);
                    eliminarUsuario.setEnabled(false);
                }
            }
        });

        table.setPreferredSize(new Dimension(600, 300));
        JPanel panelScrollPane = new JPanel();
        panelScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        JScrollPane scrollPane = new JScrollPane(table);
        panelScrollPane.add(scrollPane);
        frame.add(panelScrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);
        frame.setVisible(true);
        loadUsuarios();
        frame.setIconImage(new ImageIcon("icon.png").getImage());
        return (frame);
    }

    public void programaEventos() {

        ActionListener infoUsuario = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
                infoWindow = mostrarInfo();
                infoWindow.setVisible(true);
            }
        };
        this.infoUsuario.addActionListener(infoUsuario);

        ActionListener cambiarPass = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
                passEditWindow = cambiarPass();
                passEditWindow.setVisible(true);
            }
        };
        this.cambiarPass.addActionListener(cambiarPass);

        ActionListener editarUsuario = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
                editUserWindow = editUser();
                editUserWindow.setVisible(true);
            }
        };
        this.editarUsuario.addActionListener(editarUsuario);

        ActionListener salir = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
                window = new JLogin();
                window.setVisible(true);
                saveUsuarios();
            }
        };
        this.salir.addActionListener(salir);

        ActionListener agregarUsuario = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
                addUserWindow = addUser();
                addUserWindow.setVisible(true);

            }
        };
        this.agregarUsuario.addActionListener(agregarUsuario);

        ActionListener equipoDesarrollo = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
                JFrame meme = newWindow();
                meme.setVisible(true);
            }
        };
        this.equipoDesarrollo.addActionListener(equipoDesarrollo);
    }

    JFrame newWindow() {
        ImageIcon image = new ImageIcon("banner.png");
        ImageIcon image2 = new ImageIcon("meme.jpg");
        ImageIcon image3 = new ImageIcon("meme2.jpg");
        JLabel labelD = new JLabel("Desarrolladores: José Rojas y Gerardo Gonzalez");
        JLabel labelD2 = new JLabel("Estudiantes de Ing. En Desarrollo de Videouegos y Realidad Virtual");

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 800);

        JLabel banner = new JLabel(image);
        JLabel banner2 = new JLabel(image2);
        JLabel banner3 = new JLabel(image3);
        JPanel PanelBanner = new JPanel();

        PanelBanner.setBackground(new Color(190, 11, 103));
        frame.add(PanelBanner, BorderLayout.PAGE_START);
        PanelBanner.add(banner, BorderLayout.PAGE_START);

        JPanel formulario = new JPanel();

        formulario.setLayout(new GridLayout(2, 2, 10, 10));

        formulario.add(banner2);
        formulario.add(labelD);
        formulario.add(banner3);

        formulario.add(labelD2);

        frame.add(formulario, BorderLayout.CENTER);
        frame.setIconImage(new ImageIcon("icon.png").getImage());
        return (frame);
    }

    JFrame addUser() {
        ImageIcon image = new ImageIcon("banner.png");

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setMinimumSize(new Dimension(900, 400));
        JPanel PanelBanner = new JPanel();
        JLabel banner = new JLabel(image);
        PanelBanner.add(banner);
        PanelBanner.setBackground(new Color(190, 11, 103));
        frame.add(PanelBanner, BorderLayout.PAGE_START);

        JPanel User = new JPanel(new BorderLayout());
        JLabel labelUser = new JLabel("Nombre de usuario");
        labelUser.setPreferredSize(new Dimension(120, 24));
        this.textfieldUser = new JTextField();
        textfieldUser.setPreferredSize(new Dimension(100, 24));
        User.add(labelUser, BorderLayout.LINE_START);
        User.add(textfieldUser, BorderLayout.CENTER);
        textfieldUser.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                checkFields();
            }
        });

        JPanel Nombre = new JPanel(new BorderLayout());
        JLabel labelNombre = new JLabel("Nombre Real");
        labelNombre.setPreferredSize(new Dimension(120, 24));
        this.textfieldNombreReal = new JTextField();
        textfieldNombreReal.setPreferredSize(new Dimension(100, 24));
        Nombre.add(labelNombre, BorderLayout.LINE_START);
        Nombre.add(textfieldNombreReal, BorderLayout.CENTER);
        textfieldNombreReal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                checkFields();
            }
        });

        JPanel email = new JPanel(new BorderLayout());
        JLabel labelEmail = new JLabel("Email");
        labelEmail.setPreferredSize(new Dimension(120, 10));
        this.textfieldEmail = new JTextField();
        textfieldEmail.setPreferredSize(new Dimension(100, 2));
        email.add(labelEmail, BorderLayout.LINE_START);
        email.add(textfieldEmail, BorderLayout.CENTER);
        textfieldEmail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                checkFields();
            }
        });

        JPanel Pass = new JPanel(new BorderLayout());
        JLabel labelPass = new JLabel("Contraseña");
        labelPass.setPreferredSize(new Dimension(100, 2));
        this.textfieldPass = new JPasswordField();
        textfieldPass.setPreferredSize(new Dimension(400, 2));
        Pass.add(labelPass, BorderLayout.LINE_START);
        Pass.add(textfieldPass, BorderLayout.CENTER);
        textfieldPass.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                checkFields();
            }
        });

        this.agregarUsuario2 = new JButton("Agregar");
        agregarUsuario2.addActionListener(this);
        agregarUsuario2.setBackground(Color.BLUE);
        agregarUsuario2.setForeground(Color.WHITE);
        agregarUsuario2.setEnabled(false);

        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(5, 1, 10, 10));
        formulario.add(User);
        formulario.add(Nombre);
        formulario.add(email);
        formulario.add(Pass);
        formulario.add(agregarUsuario2, BorderLayout.SOUTH);

        JPanel centro = new JPanel();
        centro.setBorder(new EmptyBorder(20, 20, 20, 20));
        centro.add(formulario);

        frame.add(centro);
        frame.setIconImage(new ImageIcon("icon.png").getImage());
        return frame;
    }

    JFrame mostrarInfo() {
        ImageIcon image = new ImageIcon("banner.png");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setMinimumSize(new Dimension(900, 400));
        JPanel PanelBanner = new JPanel();
        JLabel banner = new JLabel(image);
        PanelBanner.add(banner);
        PanelBanner.setBackground(new Color(190, 11, 103));
        frame.add(PanelBanner, BorderLayout.PAGE_START);

        JPanel User = new JPanel(new BorderLayout());
        JLabel labelUser = new JLabel("Nombre de usuario : ");

        JPanel User1 = new JPanel(new BorderLayout());
        JLabel labelUser1 = new JLabel();

        labelUser.setPreferredSize(new Dimension(120, 24));
        User.add(labelUser, BorderLayout.LINE_START);
        labelUser1.setPreferredSize(new Dimension(120, 24));
        User1.add(labelUser1, BorderLayout.LINE_START);
        labelUser1.setForeground(Color.red);

        JPanel Nombre = new JPanel(new BorderLayout());
        JLabel labelNombre = new JLabel("Nombre Real : ");

        JPanel Nombre1 = new JPanel(new BorderLayout());
        JLabel labelNombre1 = new JLabel();

        labelNombre.setPreferredSize(new Dimension(120, 24));
        Nombre.add(labelNombre, BorderLayout.LINE_START);

        labelNombre1.setPreferredSize(new Dimension(120, 24));
        Nombre1.add(labelNombre1, BorderLayout.LINE_START);
        labelNombre1.setForeground(Color.red);

        JPanel email = new JPanel(new BorderLayout());
        JLabel labelEmail = new JLabel("Email : ");

        JPanel email1 = new JPanel(new BorderLayout());
        JLabel labelEmail1 = new JLabel();

        labelEmail.setPreferredSize(new Dimension(120, 10));
        email.add(labelEmail, BorderLayout.LINE_START);

        labelEmail1.setPreferredSize(new Dimension(120, 10));
        email1.add(labelEmail1, BorderLayout.LINE_START);
        labelEmail1.setForeground(Color.red);

        JPanel Pass = new JPanel(new BorderLayout());
        JLabel labelPass = new JLabel("Contraseña : ");
        labelPass.setPreferredSize(new Dimension(100, 2));
        Pass.add(labelPass, BorderLayout.LINE_START);

        JPanel Pass1 = new JPanel(new BorderLayout());
        JLabel labelPass1 = new JLabel();
        labelPass1.setPreferredSize(new Dimension(100, 2));
        Pass1.add(labelPass1, BorderLayout.LINE_START);
        labelPass1.setForeground(Color.red);

        for (Usuario U : usuarios) {
            if (usuarioL.equals(((Usuario) U).getUsuario())) {
                labelUser1.setText(((Usuario) U).getUsuario());
                labelNombre1.setText(((Usuario) U).getNombre());
                labelEmail1.setText(((Usuario) U).getMail());
                labelPass1.setText(((Usuario) U).getPass());
            }
        }

        JPanel panelVolver = new JPanel();

        this.volver = new JButton("Volver");
        volver.addActionListener(this);
        volver.setBackground(Color.BLUE);
        volver.setForeground(Color.WHITE);
        volver.setPreferredSize(new Dimension(100, 24));

        panelVolver.add(volver);
        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(4, 2, 10, 10));
        formulario.add(User);
        formulario.add(User1);
        formulario.add(Nombre);
        formulario.add(Nombre1);
        formulario.add(email);
        formulario.add(email1);
        formulario.add(Pass);
        formulario.add(Pass1);

        JPanel centro = new JPanel();
        centro.setBorder(new EmptyBorder(20, 20, 20, 20));
        centro.add(formulario);

        frame.add(centro);
        frame.add(panelVolver, BorderLayout.SOUTH);
        frame.setIconImage(new ImageIcon("icon.png").getImage());

        return frame;
    }

    JFrame cambiarPass() {
        ImageIcon image = new ImageIcon("banner.png");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setMinimumSize(new Dimension(900, 400));
        JPanel PanelBanner = new JPanel();
        JLabel banner = new JLabel(image);
        PanelBanner.add(banner);
        PanelBanner.setBackground(new Color(190, 11, 103));
        frame.add(PanelBanner, BorderLayout.PAGE_START);

        JPanel primeraFila = new JPanel(new BorderLayout());
        JLabel labelContrasena1 = new JLabel("Ingrese Contraseña Antigua");
        labelContrasena1.setPreferredSize(new Dimension(200, 24));
        this.textfieldPass1 = new JPasswordField();
        textfieldPass1.setPreferredSize(new Dimension(100, 24));
        textfieldPass1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                checkFields3();
            }
        });
        primeraFila.add(labelContrasena1, BorderLayout.LINE_START);
        primeraFila.add(textfieldPass1, BorderLayout.AFTER_LINE_ENDS);

        JPanel segundaFila = new JPanel(new BorderLayout());
        JLabel labelContrasena2 = new JLabel("Ingrese Contraseña Nueva");
        labelContrasena2.setPreferredSize(new Dimension(200, 24));
        this.textfieldPass2 = new JPasswordField();
        textfieldPass2.setPreferredSize(new Dimension(100, 24));
        textfieldPass2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                checkFields3();
            }
        });
        segundaFila.add(labelContrasena2, BorderLayout.LINE_START);
        segundaFila.add(textfieldPass2, BorderLayout.CENTER);

        this.cambiarContrasena = new JButton("Cambiar Contraseña");
        cambiarContrasena.addActionListener(this);
        cambiarContrasena.setBackground(Color.BLUE);
        cambiarContrasena.setForeground(Color.WHITE);

        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(3, 1, 10, 10));
        formulario.add(primeraFila);
        formulario.add(segundaFila);
        formulario.add(cambiarContrasena);
        JPanel centro = new JPanel();
        centro.setBorder(new EmptyBorder(20, 20, 20, 20));
        centro.add(formulario);
        frame.setIconImage(new ImageIcon("icon.png").getImage());
        frame.add(centro);

        return frame;
    }

    JFrame editUser() {
        String text = "";
        ImageIcon image = new ImageIcon("banner.png");

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setMinimumSize(new Dimension(900, 400));
        JPanel PanelBanner = new JPanel();
        JLabel banner = new JLabel(image);
        PanelBanner.add(banner);
        PanelBanner.setBackground(new Color(190, 11, 103));
        frame.add(PanelBanner, BorderLayout.PAGE_START);

        JPanel User = new JPanel(new BorderLayout());
        JLabel labelUser = new JLabel("Nombre de usuario");
        labelUser.setPreferredSize(new Dimension(120, 24));
        this.textfieldUser = new JTextField();
        textfieldUser.setPreferredSize(new Dimension(100, 24));
        User.add(labelUser, BorderLayout.LINE_START);
        User.add(textfieldUser, BorderLayout.CENTER);
        text = (String) this.model.getValueAt(currentRow, 0);
        textfieldUser.setText(text);
        textfieldUser.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                checkFields2();
            }
        });

        JPanel Nombre = new JPanel(new BorderLayout());
        JLabel labelNombre = new JLabel("Nombre Real");
        labelNombre.setPreferredSize(new Dimension(120, 24));
        this.textfieldNombreReal = new JTextField();
        textfieldNombreReal.setPreferredSize(new Dimension(100, 24));
        Nombre.add(labelNombre, BorderLayout.LINE_START);
        Nombre.add(textfieldNombreReal, BorderLayout.CENTER);
        text = (String) this.model.getValueAt(currentRow, 1);
        textfieldNombreReal.setText(text);
        textfieldNombreReal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                checkFields2();
            }
        });

        JPanel email = new JPanel(new BorderLayout());
        JLabel labelEmail = new JLabel("Email");
        labelEmail.setPreferredSize(new Dimension(120, 10));
        this.textfieldEmail = new JTextField();
        textfieldEmail.setPreferredSize(new Dimension(100, 2));
        email.add(labelEmail, BorderLayout.LINE_START);
        email.add(textfieldEmail, BorderLayout.CENTER);

        text = (String) this.model.getValueAt(currentRow, 2);
        textfieldEmail.setText(text);
        textfieldEmail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                checkFields2();
            }
        });

        JPanel Pass = new JPanel(new BorderLayout());
        JLabel labelPass = new JLabel("Contraseña");
        labelPass.setPreferredSize(new Dimension(100, 2));
        this.textfieldPass = new JPasswordField();
        textfieldPass.setPreferredSize(new Dimension(400, 2));
        Pass.add(labelPass, BorderLayout.LINE_START);
        Pass.add(textfieldPass, BorderLayout.CENTER);
        text = (String) this.model.getValueAt(currentRow, 3);
        textfieldPass.setText(text);
        textfieldPass.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                checkFields2();
            }
        });

        this.editarUsuario2 = new JButton("Editar");
        editarUsuario2.addActionListener(this);
        editarUsuario2.setBackground(Color.BLUE);
        editarUsuario2.setForeground(Color.WHITE);
        editarUsuario2.setEnabled(false);

        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(5, 1, 10, 10));
        formulario.add(User);
        formulario.add(Nombre);
        formulario.add(email);
        formulario.add(Pass);
        formulario.add(editarUsuario2, BorderLayout.SOUTH);

        JPanel centro = new JPanel();
        centro.setBorder(new EmptyBorder(20, 20, 20, 20));
        centro.add(formulario);
        frame.setIconImage(new ImageIcon("icon.png").getImage());
        frame.add(centro);

        return frame;
    }

    public void loadUsuarios() {
        try {
            boolean isAdminHere = false;
            System.out.println("Tratando de leer archivo lista");
            this.usuarios = leerArchivoLista();
            System.out.println("Archivo Lista leído");

            if (this.usuarios.size() < 1) {
                System.out.println("Lista vacia");
                this.usuarios.add(new Usuario("admin", "Administrador", "admin@utalca.cl", "admin1234"));
                isAdminHere = true;
            }
            for (int i = 0; i < this.usuarios.size(); i++) {
                model.addRow(new String[]{usuarios.get(i).getUsuario(),
                    usuarios.get(i).getNombre(),
                    usuarios.get(i).getMail(),
                    usuarios.get(i).getPass()});
                if (usuarios.get(i).getUsuario().equals("admin")) {
                    isAdminHere = true;
                }
            }
            if (!isAdminHere) {
                System.out.println("Admin nu taba al final en la lista");
                model.addRow(new String[]{"admin", "Administrador", "admin@utalca.cl", "admin1234"});
            }
        } catch (Exception er) {
            System.out.println("No se ha podido ingresar correctamente.");
        }
    }

    public void saveUsuarios() {
        this.usuarios = new ArrayList<Usuario>();

        if (this.model.getRowCount() > -1) {

            for (int i = 0; i < this.model.getRowCount(); i++) {
                String usuario = (String) this.model.getValueAt(i, 0);
                String nombre = (String) this.model.getValueAt(i, 1);
                String mail = (String) this.model.getValueAt(i, 2);
                String pass = (String) this.model.getValueAt(i, 3);
                this.usuarios.add(new Usuario(usuario, nombre, mail, pass));
                System.out.println("Usuario añadido");
            }
            //Aca debe guardar el archivo con los usuarios
            user2Text();

        } else {
            System.out.println("Error leyendo los usuarios de la tabla");
        }
    }

    public int sizeU() {
        return usuarios.size();
    }

    public Usuario getU(int index) {
        return usuarios.get(index);
    }

    public boolean addU(Usuario e) {
        return usuarios.add(e);
    }

    public Usuario removeU(int index) {
        return usuarios.remove(index);
    }

    public void checkFields() {
        String user = textfieldUser.getText();
        String nombre = textfieldNombreReal.getText();
        String pass = textfieldPass.getText();
        String email = textfieldEmail.getText();
        if (!user.equals("") && !nombre.equals("") && !pass.equals("") && !email.equals("")) {
            agregarUsuario2.setEnabled(true);
        } else {
            agregarUsuario2.setEnabled(false);
        }
    }

    public void checkFields2() {
        String user = textfieldUser.getText();
        String nombre = textfieldNombreReal.getText();
        String pass = textfieldPass.getText();
        String email = textfieldEmail.getText();
        if (!user.equals("") && !nombre.equals("") && !pass.equals("") && !email.equals("")) {
            editarUsuario2.setEnabled(true);
        } else {
            editarUsuario2.setEnabled(false);
        }
    }

    public void checkFields3() {
        String pass1 = textfieldPass1.getText();
        String pass2 = textfieldPass2.getText();
        String pass3 = (String) this.model.getValueAt(getUserRow(), 3);
        if (pass1.equals(pass3) && !pass2.equals("")) {
            cambiarContrasena.setEnabled(true);
        } else {
            cambiarContrasena.setEnabled(false);
        }
    }

    public ArrayList<Usuario> leerArchivoLista() {
        ArrayList<Usuario> lista = new ArrayList();
        Scanner file = null;
        try {
            file = new Scanner(new File("Usuario.gj"));
            System.out.println("File found");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        if (file != null) {
            System.out.println("File not null");
            //System.out.println(""+file.hasNext());
            while (file.hasNextLine()) {
                System.out.println("Reading File");
                String user = file.nextLine();
                String nombre = file.nextLine();
                String mail = file.nextLine();
                String pass = file.nextLine();

                System.out.println(user + " " + nombre + " " + mail + " " + pass);
                Usuario usuario = new Usuario(user, nombre, mail, pass);
                int checker = 0;
                for (int i = 0; i < lista.size(); i++) {
                    Usuario check = lista.get(i);
                    if (check.getUsuario() == usuario.getUsuario() || check.getMail() == usuario.getMail()) {
                        //System.out.println("Codigo duplicado: " + check.getCodigo() + " - " + check.getNombre() + " / " + objeto.getCodigo() + " - " + objeto.getNombre());
                        checker = 1;
                    }
                }
                if (checker == 0) {
                    lista.add(usuario);
                }
                //lista.add(objeto);
            }
            file.close();
        }
        return lista;
    }

    public int getUserRow() {
        int datRow = 0;
        for (int i = 0; i < this.model.getRowCount(); i++) {
            String usuario = (String) this.model.getValueAt(i, 0);
            if (usuarioL.equals(usuario)) {
                datRow = i;
                System.out.println("Usuario encontrado");
                break;
            }
        }
        return datRow;
    }

    public void user2Text() {
        try {
            PrintWriter out = new PrintWriter("Usuario.gj");
            //FileOutputStream fileOut = new FileOutputStream("Usuario.gj");
            //ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (int i = 0; i < this.usuarios.size(); i++) {
                out.print(this.usuarios.get(i).getUsuario() + "\n");
                out.print(this.usuarios.get(i).getNombre() + "\n");
                out.print(this.usuarios.get(i).getMail() + "\n");
                out.print(this.usuarios.get(i).getPass() + "\n");
            }
            out.close();
            System.out.println("Lista de usuarios ha sido guardada en 'Usuario.gj'");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
