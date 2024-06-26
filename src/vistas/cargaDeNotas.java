/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import accesoDatos.AlumnoData;
import accesoDatos.InscripcionData;
import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Josias
 */
public class CargaDeNotas extends javax.swing.JInternalFrame {

    private AlumnoData aluData = new AlumnoData();
    private List<Alumno> alumnos;
    private DefaultTableModel modelo;
    private InscripcionData inscData = new InscripcionData();
    
    /**
     * Creates new form cargaDeNotas
     */
    public CargaDeNotas() {
        initComponents();
    modelo = (DefaultTableModel) JtbleListaAlum.getModel();
    try {
        alumnos = aluData.listarAlumnos();
        cargarAlumnos();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar nota");
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JCBSelecAlumnos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtbleListaAlum = new javax.swing.JTable();
        jBSalir = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Carga de notas");

        jLabel2.setText("Seleccione un alumno:");

        JCBSelecAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBSelecAlumnosActionPerformed(evt);
            }
        });

        JtbleListaAlum.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(JtbleListaAlum);

        jBSalir.setText("Salir");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jBGuardar.setText("Guardar");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBGuardar)
                                .addGap(90, 90, 90)
                                .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JCBSelecAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel1)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JCBSelecAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBGuardar)
                    .addComponent(jBSalir))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JCBSelecAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBSelecAlumnosActionPerformed
               Alumno alumnoSeleccionado = (Alumno) JCBSelecAlumnos.getSelectedItem();
        if (alumnoSeleccionado != null) {
            cargarInscripciones(alumnoSeleccionado.getIdAlumno());
        }
    }//GEN-LAST:event_JCBSelecAlumnosActionPerformed
    private void cargarInscripciones(int idAlumno) {
        borrarFilaTabla();
        List<Inscripcion> inscripciones = inscData.obtenerInscripcionPorAlumno(idAlumno);
        for (Inscripcion inscripcion : inscripciones) {
            Materia materia = inscripcion.getMateria();
            modelo.addRow(new Object[]{materia.getIdMateria(), materia.getNombre(), inscripcion.getNota()});
        }
    }
   private void borrarFilaTabla() {
    if (modelo != null) {
        int rowCount = modelo.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }
}

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
    int filaSeleccionada = JtbleListaAlum.getSelectedRow();
        
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para modificar la nota.");
        return;
    }
    int idAlumno = ((Alumno) JCBSelecAlumnos.getSelectedItem()).getIdAlumno();
    int idInscripcion = (int) JtbleListaAlum.getValueAt(filaSeleccionada, 0);
    double nuevaNota = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la nueva nota:"));
    inscData.actualizarNota(idAlumno, idInscripcion, nuevaNota);
    cargarInscripciones(idAlumno);
    JOptionPane.showMessageDialog(null, "La nota ha sido modificada con éxito.");
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
       dispose();
    }//GEN-LAST:event_jBSalirActionPerformed

     private void cargarAlumnos() {
        for (Alumno item : alumnos) {
            JCBSelecAlumnos.addItem(item);
        }
    }

    
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Alumno> JCBSelecAlumnos;
    private javax.swing.JTable JtbleListaAlum;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
