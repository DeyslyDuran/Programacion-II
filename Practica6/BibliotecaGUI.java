package Practica6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class BibliotecaGUI extends JFrame {
    private Biblioteca biblioteca;
    private JTabbedPane tabbedPane;

    // Paneles
    private JPanel panelLibros;
    private JPanel panelAutores;
    private JPanel panelEstudiantes;
    private JPanel panelPrestamos;
    private JPanel panelEstadisticas;

    // Listas de estudiantes (simularíamos una gestión completa)
    private java.util.ArrayList<Estudiante> estudiantes;

    public BibliotecaGUI() {
        estudiantes = new java.util.ArrayList<>();
        cargarDatos();
        inicializarComponentes();
        cargarDatosIniciales();
    }

    private void cargarDatos() {
        try {
            biblioteca = PersistenceManager.cargarBiblioteca();
        } catch (IOException | ClassNotFoundException e) {
            biblioteca = new Biblioteca("Biblioteca Central UMSA");
            JOptionPane.showMessageDialog(this,
                    "No se pudieron cargar datos previos. Iniciando nueva biblioteca.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void guardarDatos() {
        try {
            PersistenceManager.guardarBiblioteca(biblioteca);
            JOptionPane.showMessageDialog(this,
                    "Datos guardados exitosamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inicializarComponentes() {
        setTitle("Sistema de Gestión - " + biblioteca.getNombre());
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear menú
        crearMenuBar();

        // Crear pestañas
        tabbedPane = new JTabbedPane();

        panelLibros = crearPanelLibros();
        panelAutores = crearPanelAutores();
        panelEstudiantes = crearPanelEstudiantes();
        panelPrestamos = crearPanelPrestamos();
        panelEstadisticas = crearPanelEstadisticas();

        tabbedPane.addTab("📚 Libros", panelLibros);
        tabbedPane.addTab("✍️ Autores", panelAutores);
        tabbedPane.addTab("👨‍🎓 Estudiantes", panelEstudiantes);
        tabbedPane.addTab("📋 Préstamos", panelPrestamos);
        tabbedPane.addTab("📊 Estadísticas", panelEstadisticas);

        add(tabbedPane);
    }

    private void crearMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemGuardar = new JMenuItem("💾 Guardar");
        JMenuItem itemSalir = new JMenuItem("🚪 Salir");

        itemGuardar.addActionListener(e -> guardarDatos());
        itemSalir.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Desea guardar antes de salir?",
                    "Confirmar Salida",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                guardarDatos();
                System.exit(0);
            } else if (respuesta == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        });

        menuArchivo.add(itemGuardar);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcercaDe = new JMenuItem("Acerca de");
        itemAcercaDe.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Sistema de Gestión de Biblioteca UMSA\n" +
                                "Versión 1.0\n" +
                                "Con Persistencia de Datos",
                        "Acerca de",
                        JOptionPane.INFORMATION_MESSAGE));
        menuAyuda.add(itemAcercaDe);

        menuBar.add(menuArchivo);
        menuBar.add(menuAyuda);

        setJMenuBar(menuBar);
    }

    private JPanel crearPanelLibros() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tabla de libros
        String[] columnas = {"Título", "ISBN", "Páginas", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = new JButton("➕ Agregar Libro");
        JButton btnLeer = new JButton("📖 Leer Libro");
        JButton btnActualizar = new JButton("🔄 Actualizar");

        btnAgregar.addActionListener(e -> agregarLibro(modelo));
        btnLeer.addActionListener(e -> leerLibro(tabla));
        btnActualizar.addActionListener(e -> actualizarTablaLibros(modelo));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnLeer);
        panelBotones.add(btnActualizar);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        actualizarTablaLibros(modelo);

        return panel;
    }

    private void actualizarTablaLibros(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (Libro libro : biblioteca.getLibros()) {
            modelo.addRow(new Object[]{
                    libro.getTitulo(),
                    libro.getIsbn(),
                    libro.getPaginas().size(),
                    libro.isPrestado() ? "PRESTADO" : "DISPONIBLE"
            });
        }
    }

    private void agregarLibro(DefaultTableModel modelo) {
        JTextField txtTitulo = new JTextField(20);
        JTextField txtISBN = new JTextField(20);
        JTextField txtPaginas = new JTextField(5);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Título:"));
        panel.add(txtTitulo);
        panel.add(new JLabel("ISBN:"));
        panel.add(txtISBN);
        panel.add(new JLabel("Número de páginas:"));
        panel.add(txtPaginas);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Agregar Nuevo Libro", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int numPaginas = Integer.parseInt(txtPaginas.getText());
                String[] contenidos = new String[numPaginas];
                for (int i = 0; i < numPaginas; i++) {
                    contenidos[i] = "Contenido de la página " + (i + 1);
                }

                Libro libro = new Libro(txtTitulo.getText(), txtISBN.getText(), contenidos);
                biblioteca.agregarLibro(libro);
                actualizarTablaLibros(modelo);
                JOptionPane.showMessageDialog(this, "Libro agregado exitosamente");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Número de páginas inválido",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void leerLibro(JTable tabla) {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un libro de la tabla",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Libro libro = biblioteca.getLibros().get(fila);
        StringBuilder contenido = new StringBuilder();
        contenido.append("=== ").append(libro.getTitulo()).append(" ===\n\n");

        for (Libro.Pagina pagina : libro.getPaginas()) {
            contenido.append("Página ").append(pagina.getNumero())
                    .append(": ").append(pagina.getContenido()).append("\n");
        }

        JTextArea textArea = new JTextArea(contenido.toString());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(this, scrollPane,
                "Lectura: " + libro.getTitulo(),
                JOptionPane.PLAIN_MESSAGE);
    }

    private JPanel crearPanelAutores() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnas = {"Nombre", "Nacionalidad"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = new JButton("➕ Agregar Autor");
        JButton btnActualizar = new JButton("🔄 Actualizar");

        btnAgregar.addActionListener(e -> agregarAutor(modelo));
        btnActualizar.addActionListener(e -> actualizarTablaAutores(modelo));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        actualizarTablaAutores(modelo);

        return panel;
    }

    private void actualizarTablaAutores(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (Autor autor : biblioteca.getAutores()) {
            modelo.addRow(new Object[]{
                    autor.getNombre(),
                    autor.getNacionalidad()
            });
        }
    }

    private void agregarAutor(DefaultTableModel modelo) {
        JTextField txtNombre = new JTextField(20);
        JTextField txtNacionalidad = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Nacionalidad:"));
        panel.add(txtNacionalidad);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Agregar Nuevo Autor", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Autor autor = new Autor(txtNombre.getText(), txtNacionalidad.getText());
            biblioteca.agregarAutor(autor);
            actualizarTablaAutores(modelo);
            JOptionPane.showMessageDialog(this, "Autor agregado exitosamente");
        }
    }

    private JPanel crearPanelEstudiantes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnas = {"Código", "Nombre"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = new JButton("➕ Agregar Estudiante");
        JButton btnActualizar = new JButton("🔄 Actualizar");

        btnAgregar.addActionListener(e -> agregarEstudiante(modelo));
        btnActualizar.addActionListener(e -> actualizarTablaEstudiantes(modelo));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        actualizarTablaEstudiantes(modelo);

        return panel;
    }

    private void actualizarTablaEstudiantes(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (Estudiante est : estudiantes) {
            modelo.addRow(new Object[]{
                    est.getCodigo(),
                    est.getNombre()
            });
        }
    }

    private void agregarEstudiante(DefaultTableModel modelo) {
        JTextField txtCodigo = new JTextField(15);
        JTextField txtNombre = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Código:"));
        panel.add(txtCodigo);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Agregar Nuevo Estudiante", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Estudiante est = new Estudiante(txtCodigo.getText(), txtNombre.getText());
            estudiantes.add(est);
            actualizarTablaEstudiantes(modelo);
            JOptionPane.showMessageDialog(this, "Estudiante agregado exitosamente");
        }
    }

    private JPanel crearPanelPrestamos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnas = {"Estudiante", "Libro", "Fecha Préstamo", "Fecha Devolución", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNuevo = new JButton("➕ Nuevo Préstamo");
        JButton btnDevolver = new JButton("↩️ Devolver Libro");
        JButton btnActualizar = new JButton("🔄 Actualizar");

        btnNuevo.addActionListener(e -> nuevoPrestamo(modelo));
        btnDevolver.addActionListener(e -> devolverLibro(tabla, modelo));
        btnActualizar.addActionListener(e -> actualizarTablaPrestamos(modelo));

        panelBotones.add(btnNuevo);
        panelBotones.add(btnDevolver);
        panelBotones.add(btnActualizar);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        actualizarTablaPrestamos(modelo);

        return panel;
    }

    private void actualizarTablaPrestamos(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (Prestamo p : biblioteca.getPrestamos()) {
            modelo.addRow(new Object[]{
                    p.getEstudiante().getNombre(),
                    p.getLibro().getTitulo(),
                    p.getFechaPrestamo(),
                    p.getFechaDevolucion(),
                    p.isActivo() ? "ACTIVO" : "DEVUELTO"
            });
        }
    }

    private void nuevoPrestamo(DefaultTableModel modelo) {
        if (estudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe registrar estudiantes primero",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.util.ArrayList<Libro> librosDisponibles = new java.util.ArrayList<>();
        for (Libro l : biblioteca.getLibros()) {
            if (!l.isPrestado()) {
                librosDisponibles.add(l);
            }
        }

        if (librosDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay libros disponibles",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JComboBox<Estudiante> comboEstudiantes = new JComboBox<>(
                estudiantes.toArray(new Estudiante[0]));
        JComboBox<Libro> comboLibros = new JComboBox<>(
                librosDisponibles.toArray(new Libro[0]));

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Estudiante:"));
        panel.add(comboEstudiantes);
        panel.add(new JLabel("Libro:"));
        panel.add(comboLibros);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Registrar Préstamo", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Estudiante est = (Estudiante) comboEstudiantes.getSelectedItem();
            Libro libro = (Libro) comboLibros.getSelectedItem();

            if (biblioteca.prestarLibro(est, libro)) {
                actualizarTablaPrestamos(modelo);
                JOptionPane.showMessageDialog(this, "Préstamo registrado exitosamente");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error al registrar préstamo",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void devolverLibro(JTable tabla, DefaultTableModel modelo) {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un préstamo de la tabla",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Prestamo prestamo = biblioteca.getPrestamos().get(fila);
        if (!prestamo.isActivo()) {
            JOptionPane.showMessageDialog(this,
                    "Este préstamo ya fue devuelto",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        biblioteca.devolverLibro(prestamo);
        actualizarTablaPrestamos(modelo);
        JOptionPane.showMessageDialog(this, "Libro devuelto exitosamente");
    }

    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnActualizar = new JButton("🔄 Actualizar Estadísticas");
        btnActualizar.addActionListener(e -> actualizarEstadisticas(textArea));

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnActualizar, BorderLayout.SOUTH);

        actualizarEstadisticas(textArea);

        return panel;
    }

    private void actualizarEstadisticas(JTextArea textArea) {
        StringBuilder stats = new StringBuilder();
        stats.append("═══════════════════════════════════════════════════\n");
        stats.append("   ESTADÍSTICAS - ").append(biblioteca.getNombre()).append("\n");
        stats.append("═══════════════════════════════════════════════════\n\n");

        stats.append("📚 Total de Libros: ").append(biblioteca.getLibros().size()).append("\n");
        stats.append("✍️  Total de Autores: ").append(biblioteca.getAutores().size()).append("\n");
        stats.append("👨‍🎓 Total de Estudiantes: ").append(estudiantes.size()).append("\n\n");

        int librosDisponibles = 0;
        int librosPrestados = 0;
        for (Libro l : biblioteca.getLibros()) {
            if (l.isPrestado()) {
                librosPrestados++;
            } else {
                librosDisponibles++;
            }
        }

        stats.append("📖 Libros Disponibles: ").append(librosDisponibles).append("\n");
        stats.append("📕 Libros Prestados: ").append(librosPrestados).append("\n\n");

        stats.append("📋 Total de Préstamos: ").append(biblioteca.getPrestamos().size()).append("\n");
        stats.append("✅ Préstamos Activos: ").append(biblioteca.getPrestamosActivos().size()).append("\n");
        stats.append("✔️  Préstamos Completados: ")
                .append(biblioteca.getPrestamos().size() - biblioteca.getPrestamosActivos().size())
                .append("\n\n");

        stats.append("🕐 Horario: ").append(biblioteca.getHorario()).append("\n");

        textArea.setText(stats.toString());
    }

    private void cargarDatosIniciales() {
        if (biblioteca.getAutores().isEmpty()) {
            biblioteca.agregarAutor(new Autor("Miguel de Cervantes", "Española"));
            biblioteca.agregarAutor(new Autor("Gabriel García Márquez", "Colombiana"));
        }

        if (biblioteca.getLibros().isEmpty()) {
            String[] paginasDonQuijote = {"En un lugar de la Mancha...", "Segunda parte..."};
            String[] paginasCienAnios = {"Muchos años después...", "Macondo y su historia..."};
            biblioteca.agregarLibro(new Libro("Don Quijote de la Mancha", "ISBN12345", paginasDonQuijote));
            biblioteca.agregarLibro(new Libro("Cien Años de Soledad", "ISBN67890", paginasCienAnios));
        }

        if (estudiantes.isEmpty()) {
            estudiantes.add(new Estudiante("INF001", "Ana Flores"));
            estudiantes.add(new Estudiante("INF002", "Juan Pérez"));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            BibliotecaGUI gui = new BibliotecaGUI();
            gui.setVisible(true);
        });
    }
}