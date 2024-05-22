import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class regist extends JFrame {
    JLabel lbnama, lbttl, lbnopen, lbnotlp, lbalamat, lbemail;
    JTextField txtnama, txtttl, txtnopen, txtnotlp, txtemail;
    JTextArea txtalamat;
    JButton btnsubmit;

    public regist() {
        setLayout(null);

        // tabel nama
        lbnama = new JLabel("Nama Lengkap");
        add(lbnama);
        lbnama.setBounds(50, 60, 100, 20);

        txtnama = new JTextField();
        add(txtnama);
        txtnama.setBounds(180, 60, 250, 20);

        // tabel tanggal lahir
        lbttl = new JLabel("Tanggal Lahir");
        add(lbttl);
        lbttl.setBounds(50, 100, 100, 20);

        txtttl = new JTextField();
        add(txtttl);
        txtttl.setBounds(180, 100, 250, 20);

        // tabel no pendaftaran
        lbnopen = new JLabel("Nomer Pendaftaran");
        add(lbnopen);
        lbnopen.setBounds(50, 140, 130, 20);

        txtnopen = new JTextField();
        add(txtnopen);
        txtnopen.setBounds(180, 140, 250, 20);

        // tabel no telp
        lbnotlp = new JLabel("No. Telp");
        add(lbnotlp);
        lbnotlp.setBounds(50, 180, 100, 20);

        txtnotlp = new JTextField();
        add(txtnotlp);
        txtnotlp.setBounds(180, 180, 250, 20);

        // tabel alamat
        lbalamat = new JLabel("Alamat");
        add(lbalamat);
        lbalamat.setBounds(50, 220, 100, 20);

        txtalamat = new JTextArea();
        add(txtalamat);
        txtalamat.setBounds(180, 220, 250, 70);
        txtalamat.setLineWrap(true);

        // tabel email
        lbemail = new JLabel("E-mail");
        add(lbemail);
        lbemail.setBounds(50, 310, 100, 20);

        txtemail = new JTextField();
        add(txtemail);
        txtemail.setBounds(180, 310, 250, 20);

        // button submit
        btnsubmit = new JButton("Submit");
        add(btnsubmit);
        btnsubmit.setBounds(180, 350, 100, 30);
        btnsubmit.addActionListener(new MainAction());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setTitle("Data Pendaftaran Mahasiswa");
        setVisible(true);
    }

    class MainAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (txtnama.getText().trim().isEmpty() || txtttl.getText().trim().isEmpty() ||
                    txtnopen.getText().trim().isEmpty() || txtnotlp.getText().trim().isEmpty() ||
                    txtalamat.getText().trim().isEmpty() || txtemail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin data yang Anda isi sudah benar?",
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                JFrame dataWindow = new JFrame("Data Mahasiswa");
                dataWindow.setLayout(new BorderLayout());
                JTextArea dataText = new JTextArea();
                dataText.setEditable(false);
                dataText.append("Nama Lengkap: " + txtnama.getText().trim() + "\n");
                dataText.append("Tanggal Lahir: " + txtttl.getText().trim() + "\n");
                dataText.append("Nomer Pendaftaran: " + txtnopen.getText().trim() + "\n");
                dataText.append("No. Telp: " + txtnotlp.getText().trim() + "\n");
                dataText.append("Alamat: " + txtalamat.getText().trim() + "\n");
                dataText.append("E-mail: " + txtemail.getText().trim() + "\n");

                dataWindow.add(new JScrollPane(dataText), BorderLayout.CENTER);
                dataWindow.setSize(400, 300);
                dataWindow.setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        new regist();
    }
}
