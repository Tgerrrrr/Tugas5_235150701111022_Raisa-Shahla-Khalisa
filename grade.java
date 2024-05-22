import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class grade extends JFrame {
    JLabel lbJudul, lbTugas, lbKuis, lbUTS, lbUAS, lbHasil;
    JTextField txtTugas, txtKuis, txtUTS, txtUAS, txtHasil;
    JButton btnHasil, btnShowAll;
    JRadioButton rbPemlan, rbASD, rbMatkomlan, rbProbstat;
    JTextArea resultArea;
    ButtonGroup subjectGroup;
    Subject selectedSubject;
    double pemlanGrade, asdGrade, matkomlanGrade, probstatGrade;

    public grade() {
        setLayout(null);

        lbJudul = new JLabel("Hitung Nilai Akhir");
        lbJudul.setFont(new Font("Arial", Font.BOLD, 14));
        add(lbJudul);
        lbJudul.setBounds(100, 10, 170, 20);

        rbPemlan = new JRadioButton("Pemlan");
        rbASD = new JRadioButton("ASD");
        rbMatkomlan = new JRadioButton("Matkomlan");
        rbProbstat = new JRadioButton("Probstat");

        subjectGroup = new ButtonGroup();
        subjectGroup.add(rbPemlan);
        subjectGroup.add(rbASD);
        subjectGroup.add(rbMatkomlan);
        subjectGroup.add(rbProbstat);

        rbPemlan.setBounds(110, 40, 100, 20);
        rbASD.setBounds(10, 40, 100, 20);
        rbMatkomlan.setBounds(210, 40, 100, 20);
        rbProbstat.setBounds(310, 40, 100, 20);

        add(rbPemlan);
        add(rbASD);
        add(rbMatkomlan);
        add(rbProbstat);

        lbTugas = new JLabel("Tugas : ");
        add(lbTugas);
        lbTugas.setBounds(50, 70, 62, 20);

        txtTugas = new JTextField("0");
        add(txtTugas);
        txtTugas.setBounds(150, 70, 60, 20);

        lbKuis = new JLabel("Kuis : ");
        add(lbKuis);
        lbKuis.setBounds(50, 100, 70, 20);

        txtKuis = new JTextField("0");
        add(txtKuis);
        txtKuis.setBounds(150, 100, 60, 20);

        lbUTS = new JLabel("UTS : ");
        add(lbUTS);
        lbUTS.setBounds(50, 130, 70, 20);

        txtUTS = new JTextField("0");
        add(txtUTS);
        txtUTS.setBounds(150, 130, 60, 20);

        lbUAS = new JLabel("UAS : ");
        add(lbUAS);
        lbUAS.setBounds(50, 160, 70, 20);

        txtUAS = new JTextField("0");
        add(txtUAS);
        txtUAS.setBounds(150, 160, 60, 20);

        lbHasil = new JLabel("Hasil : ");
        add(lbHasil);
        lbHasil.setBounds(50, 190, 70, 20);

        txtHasil = new JTextField("0");
        add(txtHasil);
        txtHasil.setBounds(150, 190, 60, 20);
        txtHasil.setEditable(false);

        btnHasil = new JButton("Hitung");
        add(btnHasil);
        btnHasil.setBounds(50, 220, 100, 20);

        btnShowAll = new JButton("Tampilkan nilai semua matkul");
        add(btnShowAll);
        btnShowAll.setBounds(100, 370, 200, 20);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(50, 250, 300, 100);
        add(scrollPane);

        btnHasil.addActionListener(new MainAction());
        btnShowAll.addActionListener(new ShowAllAction());
        rbPemlan.addActionListener(new SubjectAction());
        rbASD.addActionListener(new SubjectAction());
        rbMatkomlan.addActionListener(new SubjectAction());
        rbProbstat.addActionListener(new SubjectAction());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setTitle("Hitung Nilai Akhir");
        setVisible(true);
    }

    class SubjectAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            txtTugas.setText("0");
            txtKuis.setText("0");
            txtUTS.setText("0");
            txtUAS.setText("0");
            txtHasil.setText("0");

            if (event.getSource() == rbPemlan) {
                selectedSubject = new Pemlan();
            } else if (event.getSource() == rbASD) {
                selectedSubject = new ASD();
            } else if (event.getSource() == rbMatkomlan) {
                selectedSubject = new Matkomlan();
            } else if (event.getSource() == rbProbstat) {
                selectedSubject = new Probstat();
            }
        }
    }

    class MainAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                int tugas = Integer.parseInt(txtTugas.getText().trim());
                int kuis = Integer.parseInt(txtKuis.getText().trim());
                int UTS = Integer.parseInt(txtUTS.getText().trim());
                int UAS = Integer.parseInt(txtUAS.getText().trim());

                if (selectedSubject != null) {
                    double hasil = selectedSubject.calculateGrade(tugas, kuis, UTS, UAS);
                    txtHasil.setText(String.format("%.2f", hasil));

                    if (selectedSubject instanceof ASD) {
                        asdGrade = hasil;
                    } else if (selectedSubject instanceof Pemlan) {
                        pemlanGrade = hasil;
                    } else if (selectedSubject instanceof Matkomlan) {
                        matkomlanGrade = hasil;
                    } else if (selectedSubject instanceof Probstat) {
                        probstatGrade = hasil;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih mata kuliah terlebih dahulu!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                txtHasil.setText("Error");
            }
        }
    }

    class ShowAllAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            resultArea.setText("");
            resultArea.append("HASIL NILAI SEMUA MATA KULIAH\n");
            resultArea.append("ASD: " + String.format("%.2f", asdGrade) + "\n");
            resultArea.append("Pemlan: " + String.format("%.2f", pemlanGrade) + "\n");
            resultArea.append("Matkomlan: " + String.format("%.2f", matkomlanGrade) + "\n");
            resultArea.append("Probstat: " + String.format("%.2f", probstatGrade) + "\n");
        }
    }

    public static void main(String[] args) {
        new grade();
    }
}

abstract class Subject {
    abstract double calculateGrade(int tugas, int kuis, int UTS, int UAS);

    abstract String getName();
}

class Pemlan extends Subject {
    double calculateGrade(int tugas, int kuis, int UTS, int UAS) {
        return (tugas * 0.3) + (kuis * 0.2) + (UTS * 0.25) + (UAS * 0.25);
    }

    String getName() {
        return "Pemlan";
    }
}

class ASD extends Subject {
    double calculateGrade(int tugas, int kuis, int UTS, int UAS) {
        return (tugas * 0.25) + (kuis * 0.25) + (UTS * 0.25) + (UAS * 0.25);
    }

    String getName() {
        return "ASD";
    }
}

class Matkomlan extends Subject {
    double calculateGrade(int tugas, int kuis, int UTS, int UAS) {
        return (tugas * 0.2) + (kuis * 0.3) + (UTS * 0.2) + (UAS * 0.3);
    }

    String getName() {
        return "Matkomlan";
    }
}

class Probstat extends Subject {
    double calculateGrade(int tugas, int kuis, int UTS, int UAS) {
        return (tugas * 0.25) + (kuis * 0.25) + (UTS * 0.2) + (UAS * 0.3);
    }

    String getName() {
        return "Probstat";
    }
}
