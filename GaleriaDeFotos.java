import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GaleriaDeFotos extends JFrame {
    private JLabel etiquetaImagen;
    private JButton botonAnterior, botonSiguiente;

    // Nombres de las imágenes solamente, sin ruta
    private String[] rutasImagenes = {
            "imagen1.jpg",
            "imagen2.jpg",
            "imagen3.jpg",
            "imagen4.jpg",
            "imagen5.jpg"
    };

    private int indiceActual = 0;

    public GaleriaDeFotos() {
        super("Galería de Fotos");
        setLayout(new BorderLayout());

        etiquetaImagen = new JLabel();
        etiquetaImagen.setHorizontalAlignment(JLabel.CENTER);
        mostrarImagen(indiceActual);
        add(etiquetaImagen, BorderLayout.CENTER);

        botonAnterior = new JButton("Anterior");
        botonSiguiente = new JButton("Siguiente");

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonAnterior);
        panelBotones.add(botonSiguiente);
        add(panelBotones, BorderLayout.SOUTH);

        botonAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (indiceActual > 0) {
                    indiceActual--;
                    mostrarImagen(indiceActual);
                }
            }
        });

        botonSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (indiceActual < rutasImagenes.length - 1) {
                    indiceActual++;
                    mostrarImagen(indiceActual);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void mostrarImagen(int indice) {
        // Cargar recurso desde carpeta 'imagenes' que esté dentro de src/
        java.net.URL url = getClass().getResource("/imagenes/" + rutasImagenes[indice]);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            Image imagenEscalada = icono.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
            etiquetaImagen.setIcon(new ImageIcon(imagenEscalada));
        } else {
            etiquetaImagen.setText("No se pudo cargar la imagen: " + rutasImagenes[indice]);
            System.out.println("Error al cargar: " + rutasImagenes[indice]);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GaleriaDeFotos());
    }
}
