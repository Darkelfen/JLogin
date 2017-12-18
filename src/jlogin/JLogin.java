package jlogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.EmptyBorder;

public class JLogin extends JFrame implements ActionListener
{
    private JButton iniciarSesion;
    private JButton cerrarSesion;
    private JButton eliminarUsuario;
    private JButton editarUsuario;
    private JButton agregarUsuario;
    
    private JTextField textfieldNombre;
    private JPasswordField textfieldContrasena;
    private JLabel errorMsgU;
    private JLabel errorMsgP;
    private JFrame window;
    private JMenuBar barraMenu;
    private JMenu menuArchivo,menuAyuda;
    private JMenuItem infoUsuario,cambiarPass,salir,equipoDesarrollo;
    private JTable jTable1;

    public JLogin()
    {
        super("Formulario de Inicio de Sesión");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setSize(900, 600);
        super.setMinimumSize(new Dimension(900, 400));
        
        ImageIcon image = new ImageIcon("banner.png");
        JLabel banner = new JLabel(image);
        JPanel panelBanner = new JPanel();
        panelBanner.add(banner);
        panelBanner.setBackground( new Color(190, 11, 103) );
        
        JPanel primeraFila = new JPanel(new BorderLayout());
        JLabel labelNombre = new JLabel("Nombre de usuario");
        labelNombre.setPreferredSize(new Dimension(100, 24));
        this.textfieldNombre = new JTextField();
        textfieldNombre.setPreferredSize(new Dimension(500, 24));
        primeraFila.add(labelNombre, BorderLayout.LINE_START);
        primeraFila.add(textfieldNombre, BorderLayout.CENTER);
        
        JPanel userErr = new JPanel(new BorderLayout());
        errorMsgU = new JLabel ("Ingrese usuario");
        JLabel emptyUser = new JLabel("");
        emptyUser.setPreferredSize(new Dimension (100,24));
        errorMsgU.setForeground(Color.red);
        errorMsgU.setVisible(false);
        userErr.add(emptyUser, BorderLayout.LINE_START);
        userErr.add(errorMsgU, BorderLayout.CENTER);

        JPanel segundaFila = new JPanel(new BorderLayout());
        JLabel labelContrasena = new JLabel("Contraseña");
        labelContrasena.setPreferredSize(new Dimension(100, 24));
        this.textfieldContrasena = new JPasswordField();
        textfieldContrasena.setPreferredSize(new Dimension(300, 24));
        segundaFila.add(labelContrasena, BorderLayout.LINE_START);
        segundaFila.add(textfieldContrasena, BorderLayout.CENTER);
        
        JPanel passErr = new JPanel(new BorderLayout());
        errorMsgP = new JLabel ("Ingrese contraseña");
        JLabel emptyPass = new JLabel("");
        emptyPass.setPreferredSize(new Dimension (100,24));
        errorMsgP.setForeground(Color.red);
        errorMsgP.setVisible(false);
        passErr.add(emptyPass, BorderLayout.LINE_START);
        passErr.add(errorMsgP, BorderLayout.CENTER);
        
        this.iniciarSesion = new JButton("Iniciar sesión");
        iniciarSesion.addActionListener(this);
        iniciarSesion.setBackground(Color.BLUE);
        iniciarSesion.setForeground(Color.WHITE);
        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(5, 1, 10, 10));
        formulario.add(primeraFila);
        formulario.add(userErr);
        formulario.add(segundaFila);
        formulario.add(passErr);
        formulario.add(iniciarSesion);
        
        JPanel centro = new JPanel();
        centro.setBorder(new EmptyBorder(20, 20, 20, 20));
        centro.add(formulario);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(panelBanner, BorderLayout.PAGE_START);
        panel.add(centro, BorderLayout.CENTER);
        
        super.getContentPane().add(panel);
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if( e.getSource() == this.iniciarSesion )
        {
            String user = this.textfieldNombre.getText();
            String pass = this.textfieldContrasena.getText();
            
            if ( user.equals("") && pass.equals ("") )
            {
                errorMsgU.setVisible(true);
                errorMsgP.setVisible(true);
                textfieldNombre.requestFocus();
            }
            
            else if ( user.equals("") )
            {
                errorMsgU.setVisible(true);
                errorMsgU.setText("Ingrese Usuario");
                textfieldNombre.requestFocus();
            }
            else if ( pass.equals ("") )
            {
                errorMsgP.setVisible(true);
                errorMsgP.setText("Ingrese Contraseña");
                textfieldContrasena.requestFocus();
            }
            
            if( ( user.equals("admin") && pass.equals("admin") ) || ( user.equals("a") && pass.equals("a") ))
            {
                window = openWindow();
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Error", "JLogin", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        if( e.getSource() == this.cerrarSesion )
        {
            window.dispose();
            JLogin momo = new JLogin();
            momo.setVisible(true);
        }
        
        if( e.getSource() == this.editarUsuario )
        {

        }
        
        if( e.getSource() == this.agregarUsuario )
        {

        }
        
    }

    JFrame openWindow()
    {
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
        eliminarUsuario.addActionListener(this);
        editarUsuario.addActionListener(this);
        agregarUsuario.addActionListener(this);       
        
        this.barraMenu.add(this.menuArchivo);
            this.menuArchivo.add(this.infoUsuario);
            this.menuArchivo.add(this.cambiarPass);
            this.menuArchivo.add(this.salir);
            
        this.barraMenu.add(this.menuAyuda);
            this.menuAyuda.add(this.equipoDesarrollo);
        
        this.setJMenuBar(this.barraMenu);
         
        this.programaEventos();
        
        ImageIcon image = new ImageIcon("banner2.png");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        JLabel banner = new JLabel(image);
        JPanel upperPanel = new JPanel ();
        upperPanel.setLayout(new GridLayout(2, 1, 10, 10));
        upperPanel.add(barraMenu);
        upperPanel.add(banner);

        frame.add(upperPanel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 10));
        buttonPanel.add(cerrarSesion);
        buttonPanel.add(eliminarUsuario);
        buttonPanel.add(editarUsuario);
        buttonPanel.add(agregarUsuario);

        Object rowData[][] = { { "Admin", "Administrador", "a@a" , "Admin1234"},
        { "Row2-Column1", "Row2-Column2", "Row2-Column3","Row2-Column4" } };
        Object columnNames[] = { "Usuario", "Nombre", "Email", "Contraseña" };
        JTable table = new JTable(rowData, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel,BorderLayout.SOUTH);
        frame.setVisible(true);
        return (frame);
    }
    
    public void programaEventos() {

        ActionListener infoUsuario = new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //
            }
        };
        this.infoUsuario.addActionListener(infoUsuario);
        
        ActionListener cambiarPass = new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //
            }
        };
        this.cambiarPass.addActionListener(cambiarPass);
        
        ActionListener salir = new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                window.dispose();
                JLogin momo = new JLogin();
                momo.setVisible(true); 
            }
        };
        this.salir.addActionListener(salir);
        this.cerrarSesion.addActionListener(salir);
        
        ActionListener equipoDesarrollo = new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                window.dispose();
                JFrame meme = NewWindow();
                meme.setVisible(true); 
                
            }
        };
        this.equipoDesarrollo.addActionListener(equipoDesarrollo);
    }
    
     JFrame NewWindow()
    {
        ImageIcon image = new ImageIcon("banner.png");
        ImageIcon image2 = new ImageIcon("meme.jpg");
        ImageIcon image3 = new ImageIcon("meme2.jpg");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        JLabel banner = new JLabel(image);
        JLabel banner2 = new JLabel(image2);
        JLabel banner3 = new JLabel(image3);
        JPanel PanelBanner = new JPanel();
        //this.cerrarSesion = new JButton("Cerrar sesión");
        //cerrarSesion.addActionListener(this);
        frame.add(PanelBanner);
        PanelBanner.add(banner,BorderLayout.PAGE_START);
        
        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(1, 2, 100, 100));
        
        formulario.add(banner2);
        formulario.add(banner3);
        frame.add(formulario);
        //frame.add(cerrarSesion,BorderLayout.SOUTH);
        return (frame);
    }
}
