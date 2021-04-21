package lab4;



import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class gui {

    private JFrame frame;
    private Service s;

    public gui(Service s) {
        this.s = s;
        initialize();

    }

    private void initialize() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(100, 100, 640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        JLayeredPane layeredPane = new JLayeredPane();
        frame.getContentPane().add(layeredPane, BorderLayout.CENTER);

        JButton btnNewButton = new JButton("1. Adaugati carte");
        btnNewButton.setBounds(10, 10, 269, 21);
        layeredPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("2. Cautati carte dupa cota/id");
        btnNewButton_1.setBounds(10, 41, 269, 21);
        layeredPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("3. Afisati toate cartile");
        btnNewButton_2.setBounds(10, 72, 269, 21);
        layeredPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("4. Modificati o carte");
        btnNewButton_3.setBounds(10, 103, 269, 21);
        layeredPane.add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("5. Stergeti o carte");
        btnNewButton_4.setBounds(10, 134, 269, 21);
        layeredPane.add(btnNewButton_4);

        JButton btnNewButton_5 = new JButton("6. Afisati toate cartile si valoarea lor totala");
        btnNewButton_5.setVerticalAlignment(SwingConstants.TOP);
        btnNewButton_5.setBounds(10, 165, 269, 21);
        layeredPane.add(btnNewButton_5);

        JButton btnNewButton_6 = new JButton("<html>7. Afisati toate cartile scrise de un autor dat</html>");
        btnNewButton_6.setVerticalAlignment(SwingConstants.TOP);
        btnNewButton_6.setBounds(10, 196, 269, 40);
        layeredPane.add(btnNewButton_6);

        JButton btnNewButton_7 = new JButton("<html>8. Afisati toate cartile aparute intre doi ani dati</html>");
        btnNewButton_7.setVerticalAlignment(SwingConstants.TOP);
        btnNewButton_7.setBounds(10, 246, 269, 40);
        layeredPane.add(btnNewButton_7);

        JButton btnNewButton_8 = new JButton("<html>9. Afisati toate cartile aparute la o anumita editura</html>");
        btnNewButton_8.setVerticalAlignment(SwingConstants.TOP);
        btnNewButton_8.setBounds(10, 296, 269, 40);
        layeredPane.add(btnNewButton_8);

        JButton btnNewButton_9 = new JButton("<html>10. Afisati toate cartile care au pretul mai mic decat o valoare data</html>");
        btnNewButton_9.setVerticalAlignment(SwingConstants.TOP);
        btnNewButton_9.setBounds(10, 346, 269, 40);
        layeredPane.add(btnNewButton_9);

        JButton btnNewButton_10 = new JButton("11. Iesire");
        btnNewButton_10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    ends();
                } catch (Throwable e1) {
                    JOptionPane.showMessageDialog(frame, "O eroare s-a produs la salvarea datelor!");
                }
                System.exit(0);
            }
        });
        btnNewButton_10.setBounds(10, 413, 85, 21);
        layeredPane.add(btnNewButton_10);

        Component[] comps = {
            btnNewButton,
            btnNewButton_1,
            btnNewButton_2,
            btnNewButton_3,
            btnNewButton_4,
            btnNewButton_5,
            btnNewButton_6,
            btnNewButton_7,
            btnNewButton_8,
            btnNewButton_9,
            btnNewButton_10
        };

        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component[] cc = layeredPane.getComponents();
                for (Component c: cc) {
                    Boolean ok = false;
                    for (Component d: comps)
                        if (d.equals(c))
                            ok = true;
                    if (ok == false)
                        layeredPane.remove(c);
                } 
                layeredPane.revalidate();
                layeredPane.repaint(); 
                JLabel question = new JLabel("Doriti sa introduceti o carte deja existenta?"); 
                question.setBounds(289, 14, 250, 13);
                layeredPane.add(question);
                JRadioButton rdbtnNewRadioButton = new JRadioButton("Da"); 
                rdbtnNewRadioButton.setBounds(540, 10, 40, 21);
                layeredPane.add(rdbtnNewRadioButton);
                JRadioButton rdbtnNewRadioButton1 = new JRadioButton("Nu");
                rdbtnNewRadioButton1.setBounds(580, 10, 50, 21);
                layeredPane.add(rdbtnNewRadioButton1);

                rdbtnNewRadioButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        layeredPane.remove(question);
                        layeredPane.remove(rdbtnNewRadioButton);
                        layeredPane.remove(rdbtnNewRadioButton1);
                        layeredPane.revalidate();
                        layeredPane.repaint();
                        JLabel lblNewLabel = new JLabel("Introduceti id");
                        lblNewLabel.setBounds(289, 14, 130, 13);
                        layeredPane.add(lblNewLabel);
                        JTextField textField = new JTextField(); 
                        textField.setBounds(417, 14, 100, 15);
                        layeredPane.add(textField);
                        JButton submit = new JButton("Gata!");
                        submit.setBounds(289, 30, 100, 21);
                        layeredPane.add(submit);
                        submit.addActionListener(new ActionListener() { 
                            public void actionPerformed(ActionEvent ae) {
                                String id = textField.getText(); 
                                try {
                                    int idd = Integer.valueOf(id);
                                    if (s.getBookByID(idd) == null)
                                        throw new Exception("Cartea cu acel id nu exista!");
                                    Book b = s.getBookByID(idd);
                                    s.addBook(b.getID(), b.getTitle(), b.getAuthor(), b.getReleaseYear(), b.getPrice(), b.getPublisher());
                                    JOptionPane.showMessageDialog(frame, "Carte introdusa cu succes");

                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(frame, e.getMessage());
                                } finally {
                                    layeredPane.remove(lblNewLabel);
                                    layeredPane.remove(textField);
                                    layeredPane.remove(submit);
                                    layeredPane.revalidate();
                                    layeredPane.repaint();
                                }
                            }
                        });
                    }
                });

                rdbtnNewRadioButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        layeredPane.remove(question);
                        layeredPane.remove(rdbtnNewRadioButton);
                        layeredPane.remove(rdbtnNewRadioButton1);
                        layeredPane.revalidate();
                        layeredPane.repaint();
                        JLabel lblNewLabel = new JLabel("Introduceti id");
                        lblNewLabel.setBounds(289, 14, 130, 13);
                        layeredPane.add(lblNewLabel);
                        JLabel lblNewLabel1 = new JLabel("Introduceti titlu");
                        lblNewLabel1.setBounds(289, 30, 130, 13);
                        layeredPane.add(lblNewLabel1);
                        JLabel lblNewLabel2 = new JLabel("Introduceti autor");
                        lblNewLabel2.setBounds(289, 46, 130, 13);
                        layeredPane.add(lblNewLabel2);
                        JLabel lblNewLabel3 = new JLabel("Introduceti an lansare");
                        lblNewLabel3.setBounds(289, 62, 130, 13);
                        layeredPane.add(lblNewLabel3);
                        JLabel lblNewLabel4 = new JLabel("Introduceti pret");
                        lblNewLabel4.setBounds(289, 78, 130, 13);
                        layeredPane.add(lblNewLabel4);
                        JLabel lblNewLabel5 = new JLabel("Introduceti editura");
                        lblNewLabel5.setBounds(289, 94, 130, 13);
                        layeredPane.add(lblNewLabel5);
                        JTextField textField = new JTextField();
                        textField.setBounds(417, 14, 100, 15);
                        layeredPane.add(textField);
                        JTextField textField1 = new JTextField();
                        textField1.setBounds(417, 30, 100, 15);
                        layeredPane.add(textField1);
                        JTextField textField2 = new JTextField();
                        textField2.setBounds(417, 46, 100, 15);
                        layeredPane.add(textField2);
                        JTextField textField3 = new JTextField();
                        textField3.setBounds(417, 62, 100, 15);
                        layeredPane.add(textField3);
                        JTextField textField4 = new JTextField();
                        textField4.setBounds(417, 78, 100, 15);
                        layeredPane.add(textField4);
                        JTextField textField5 = new JTextField();
                        textField5.setBounds(417, 94, 100, 15);
                        layeredPane.add(textField5);
                        JButton submit = new JButton("Gata!");
                        submit.setBounds(289, 110, 100, 21);
                        layeredPane.add(submit);
                        submit.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent ae) {
                                String id = textField.getText();
                                String title = textField1.getText();
                                String author = textField2.getText();
                                String publYear = textField3.getText();
                                String price = textField4.getText();
                                String publ = textField5.getText();
                                try {
                                    int idd = Integer.valueOf(id);
                                    int publlYear = Integer.valueOf(publYear);
                                    float pricee = Float.valueOf(price);
                                    if (s.getBookByID(idd) != null)
                                        throw new Exception("Cartea cu acel id exista deja!");
                                    s.addBook(idd, title, author, publlYear, pricee, publ);
                                    JOptionPane.showMessageDialog(frame, "Carte introdusa cu succes");

                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(frame, "Ati introdus date necorespunzatoare in datele cartii");
                                } finally {
                                    layeredPane.remove(lblNewLabel);
                                    layeredPane.remove(textField);
                                    layeredPane.remove(lblNewLabel1);
                                    layeredPane.remove(textField1);
                                    layeredPane.remove(lblNewLabel2);
                                    layeredPane.remove(textField2);
                                    layeredPane.remove(lblNewLabel3);
                                    layeredPane.remove(textField3);
                                    layeredPane.remove(lblNewLabel4);
                                    layeredPane.remove(textField4);
                                    layeredPane.remove(lblNewLabel5);
                                    layeredPane.remove(textField5);
                                    layeredPane.remove(submit);
                                    layeredPane.revalidate();
                                    layeredPane.repaint();
                                }
                            }
                        });
                    }
                });
            }
        });


        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component[] cc = layeredPane.getComponents();
                for (Component c: cc) {
                    Boolean ok = false;
                    for (Component d: comps)
                        if (d.equals(c))
                            ok = true;
                    if (ok == false)
                        layeredPane.remove(c);
                }
                layeredPane.revalidate();
                layeredPane.repaint();
                JLabel newLbl = new JLabel("Introduceti cota/id :");
                newLbl.setBounds(289, 14, 110, 13);
                layeredPane.add(newLbl);
                JTextField tf = new JTextField();
                tf.setBounds(400, 14, 100, 15);
                layeredPane.add(tf);
                JButton btn = new JButton("Gata!");
                btn.setBounds(289, 30, 80, 21);
                layeredPane.add(btn);
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String res = tf.getText();
                        String[] rez = res.split("\\.");
                       try { 
                    	    if (rez.length == 1) {
                            int id = Integer.parseInt(rez[0]);
                            Book b = s.getBookByID(id);
                            if (b == null)
                                JOptionPane.showMessageDialog(frame, "Cartea cu id-ul specificat nu a fost gasita! \n");
                            else {
                                layeredPane.remove(newLbl);
                                layeredPane.remove(tf);
                                layeredPane.remove(btn);
                                layeredPane.revalidate();
                                layeredPane.repaint();
                                JLabel idLabel = new JLabel();
                                idLabel.setText("ID Carte : " + b.getID());
                                idLabel.setBounds(289, 14, 200, 13);
                                layeredPane.add(idLabel);
                                JLabel titleLabel = new JLabel();
                                titleLabel.setText("Titlu Carte : " + b.getTitle());
                                titleLabel.setBounds(289, 30, 200, 13);
                                layeredPane.add(titleLabel);
                                JLabel authLabel = new JLabel();
                                authLabel.setText("Autor Carte : " + b.getAuthor());
                                authLabel.setBounds(289, 46, 200, 13);
                                layeredPane.add(authLabel);
                                JLabel publYLabel = new JLabel();
                                publYLabel.setText("An lansare Carte : " + b.getReleaseYear());
                                publYLabel.setBounds(289, 62, 200, 13);
                                layeredPane.add(publYLabel);
                                JLabel priceLabel = new JLabel();
                                priceLabel.setText("Pret Carte : " + b.getPrice());
                                priceLabel.setBounds(289, 78, 200, 13);
                                layeredPane.add(priceLabel);

                            }
                        } else if (rez.length == 2) {
                            Book b = s.getBookByQuota(res);
                            if (b == null)
                                JOptionPane.showMessageDialog(frame, "Cartea cu cota specificata nu a fost gasita! \n");
                            else {
                                layeredPane.remove(newLbl);
                                layeredPane.remove(tf);
                                layeredPane.remove(btn);
                                layeredPane.revalidate();
                                layeredPane.repaint();
                                JLabel idLabel = new JLabel();
                                idLabel.setText("ID Carte : " + b.getID());
                                idLabel.setBounds(289, 14, 200, 13);
                                layeredPane.add(idLabel);
                                JLabel titleLabel = new JLabel();
                                titleLabel.setText("Titlu Carte : " + b.getTitle());
                                titleLabel.setBounds(289, 30, 200, 13);
                                layeredPane.add(titleLabel);
                                JLabel authLabel = new JLabel();
                                authLabel.setText("Autor Carte : " + b.getAuthor());
                                authLabel.setBounds(289, 46, 200, 13);
                                layeredPane.add(authLabel);
                                JLabel publYLabel = new JLabel();
                                publYLabel.setText("An lansare Carte : " + b.getReleaseYear());
                                publYLabel.setBounds(289, 62, 200, 13);
                                layeredPane.add(publYLabel);
                                JLabel priceLabel = new JLabel();
                                priceLabel.setText("Pret Carte : " + b.getPrice());
                                priceLabel.setBounds(289, 78, 200, 13);
                                layeredPane.add(priceLabel);
                                JLabel quotaLabel = new JLabel();
                                quotaLabel.setText("Cota Carte : " + b.getQuota());
                                quotaLabel.setBounds(289, 94, 200, 13);
                                layeredPane.add(quotaLabel);

                            }
                        } else {
                            layeredPane.remove(newLbl);
                            layeredPane.remove(tf);
                            layeredPane.remove(btn);
                            layeredPane.revalidate();
                            layeredPane.repaint();
                            JOptionPane.showMessageDialog(frame, "Format incorect pentru cota/id!");

                        }
                       }
                       catch(Exception e) {
                    	   JOptionPane.showMessageDialog(frame, "Format incorect pentru cota/id!");
                       }
                    }
                });
            }
        });



        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component[] cc = layeredPane.getComponents();
                for (Component c: cc) {
                    Boolean ok = false;
                    for (Component d: comps)
                        if (d.equals(c))
                            ok = true;
                    if (ok == false)
                        layeredPane.remove(c);
                }
                layeredPane.revalidate();
                layeredPane.repaint();
                String[] headers = {
                    "ID",
                    "Titlu",
                    "Autor",
                    "An lansare",
                    "Pret",
                    "Editura",
                    "Cota"
                };
                Vector < Book > elems = s.getElems();
                Object[][] data = new Object[elems.size()][7];
                for (int i = 0; i < elems.size(); i++) {
                    data[i][0] = elems.get(i).getID();
                    data[i][1] = elems.get(i).getTitle();
                    data[i][2] = elems.get(i).getAuthor();
                    data[i][3] = elems.get(i).getReleaseYear();
                    Float aux = elems.get(i).getPrice();
                    DecimalFormat twoDForm = new DecimalFormat("#.00");
                    String totalPrice = twoDForm.format(aux);
                    data[i][4] = totalPrice;
                    data[i][5] = elems.get(i).getPublisher();
                    data[i][6] = elems.get(i).getQuota();
                }
                JTable table = new JTable(data, headers);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(289, 10, 320, 420);
                layeredPane.add(scrollPane);
            }
        });


        btnNewButton_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component[] cc = layeredPane.getComponents();
                for (Component c: cc) {
                    Boolean ok = false;
                    for (Component d: comps)
                        if (d.equals(c))
                            ok = true;
                    if (ok == false)
                        layeredPane.remove(c);
                }
                layeredPane.revalidate();
                layeredPane.repaint();
                JLabel first = new JLabel("Introduceti vechea cota : ");
                first.setBounds(289, 14, 150, 13);
                layeredPane.add(first);
                JTextField tf = new JTextField();
                tf.setBounds(440, 14, 60, 15);
                layeredPane.add(tf);
                JButton submit = new JButton("Gata!");
                submit.setBounds(289, 30, 80, 21);
                layeredPane.add(submit);
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String quota = tf.getText();
                        layeredPane.remove(tf);
                        layeredPane.remove(submit);
                        layeredPane.remove(first);
                        layeredPane.revalidate();
                        layeredPane.repaint();
                        Book b = s.getBookByQuota(quota);
                        if (b == null) {
                            JOptionPane.showMessageDialog(frame, "Cartea cu cota data nu exista!");
                            return;
                        }
                        JLabel lblNewLabel = new JLabel("Introduceti id");
                        lblNewLabel.setBounds(289, 14, 130, 13);
                        layeredPane.add(lblNewLabel);
                        JLabel lblNewLabel1 = new JLabel("Introduceti titlu");
                        lblNewLabel1.setBounds(289, 30, 130, 13);
                        layeredPane.add(lblNewLabel1);
                        JLabel lblNewLabel2 = new JLabel("Introduceti autor");
                        lblNewLabel2.setBounds(289, 46, 130, 13);
                        layeredPane.add(lblNewLabel2);
                        JLabel lblNewLabel3 = new JLabel("Introduceti an lansare");
                        lblNewLabel3.setBounds(289, 62, 130, 13);
                        layeredPane.add(lblNewLabel3);
                        JLabel lblNewLabel4 = new JLabel("Introduceti pret");
                        lblNewLabel4.setBounds(289, 78, 130, 13);
                        layeredPane.add(lblNewLabel4);
                        JLabel lblNewLabel5 = new JLabel("Introduceti editura");
                        lblNewLabel5.setBounds(289, 94, 130, 13);
                        layeredPane.add(lblNewLabel5);
                        JTextField textField = new JTextField();
                        textField.setBounds(417, 14, 100, 15);
                        layeredPane.add(textField);
                        JTextField textField1 = new JTextField();
                        textField1.setBounds(417, 30, 100, 15);
                        layeredPane.add(textField1);
                        JTextField textField2 = new JTextField();
                        textField2.setBounds(417, 46, 100, 15);
                        layeredPane.add(textField2);
                        JTextField textField3 = new JTextField();
                        textField3.setBounds(417, 62, 100, 15);
                        layeredPane.add(textField3);
                        JTextField textField4 = new JTextField();
                        textField4.setBounds(417, 78, 100, 15);
                        layeredPane.add(textField4);
                        JTextField textField5 = new JTextField();
                        textField5.setBounds(417, 94, 100, 15);
                        layeredPane.add(textField5);
                        JButton submit = new JButton("Gata!");
                        submit.setBounds(289, 110, 100, 21);
                        layeredPane.add(submit);
                        submit.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent ae) {
                                String id = textField.getText();
                                String title = textField1.getText();
                                String author = textField2.getText();
                                String publYear = textField3.getText();
                                String price = textField4.getText();
                                String publ = textField5.getText();
                                try {
                                    int idd = Integer.valueOf(id);
                                    int publlYear = Integer.valueOf(publYear);
                                    float pricee = Float.valueOf(price);
                                    Book b = s.getBookByID(idd);
                                    if (s.getBookByID(idd) != null && (!b.getTitle().equals(title) || !b.getAuthor().equals(author) ||
                                            b.getReleaseYear() != publlYear || Math.abs(b.getPrice() - pricee) > 0.001f || !b.getPublisher().equals(publ))) {
                                        JOptionPane.showMessageDialog(frame, "Ati introdus detalii gresite pentru o carte deja existenta!");
                                        return;
                                    }
                                    s.updateBook(quota, idd, title, author, publlYear, pricee, publ);
                                    JOptionPane.showMessageDialog(frame, "Carte modificata cu succes!");

                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(frame, "Ati introdus valori necorespunzatoare in datele cartii");
                                } finally {
                                    layeredPane.remove(lblNewLabel);
                                    layeredPane.remove(textField);
                                    layeredPane.remove(lblNewLabel1);
                                    layeredPane.remove(textField1);
                                    layeredPane.remove(lblNewLabel2);
                                    layeredPane.remove(textField2);
                                    layeredPane.remove(lblNewLabel3);
                                    layeredPane.remove(textField3);
                                    layeredPane.remove(lblNewLabel4);
                                    layeredPane.remove(textField4);
                                    layeredPane.remove(lblNewLabel5);
                                    layeredPane.remove(textField5);
                                    layeredPane.remove(submit);
                                    layeredPane.revalidate();
                                    layeredPane.repaint();
                                }
                            }
                        });

                    }
                });
            }
        });


        btnNewButton_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component[] cc = layeredPane.getComponents();
                for (Component c: cc) {
                    Boolean ok = false;
                    for (Component d: comps)
                        if (d.equals(c))
                            ok = true;
                    if (ok == false)
                        layeredPane.remove(c);
                }
                layeredPane.revalidate();
                layeredPane.repaint();
                JLabel lbl = new JLabel("Introduceti cota : ");
                lbl.setBounds(289, 14, 120, 13);
                layeredPane.add(lbl);
                JTextField tf = new JTextField();
                tf.setBounds(410, 10, 60, 15);
                layeredPane.add(tf);
                JButton submit = new JButton("Gata!");
                submit.setBounds(289, 30, 80, 21);
                layeredPane.add(submit);
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String quota = tf.getText();
                        layeredPane.remove(lbl);
                        layeredPane.remove(tf);
                        layeredPane.remove(submit);
                        layeredPane.revalidate();
                        layeredPane.repaint();
                        if (s.getBookByQuota(quota) == null) {
                            JOptionPane.showMessageDialog(frame, "Cota introdusa nu exista!");
                            return;
                        }
                        s.deleteBook(quota);
                        JOptionPane.showMessageDialog(frame, "Carte stearsa cu succes!");
                    }
                });
            }
        });



        btnNewButton_5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component[] cc = layeredPane.getComponents();
                for (Component c: cc) {
                    Boolean ok = false;
                    for (Component d: comps)
                        if (d.equals(c))
                            ok = true;
                    if (ok == false)
                        layeredPane.remove(c);
                }
                layeredPane.revalidate();
                layeredPane.repaint();
                String[] headers = {
                    "ID",
                    "Titlu",
                    "Autor",
                    "An lansare",
                    "Pret",
                    "Editura"
                };
                Vector < Pair < Book, Float >> elems = s.getBooksAndTotalPrice();
                Object[][] data = new Object[elems.size()][6];
                for (int i = 0; i < elems.size(); i++) {
                    Book b = elems.get(i).first();
                    Float price = elems.get(i).second();
                    data[i][0] = b.getID();
                    data[i][1] = b.getTitle();
                    data[i][2] = b.getAuthor();
                    data[i][3] = b.getReleaseYear();
                    data[i][4] = price;
                    data[i][5] = b.getPublisher();
                }
                JTable table = new JTable(data, headers);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(289, 10, 320, 420);
                layeredPane.add(scrollPane);
            }
        });


        btnNewButton_6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component[] cc = layeredPane.getComponents();
                for (Component c: cc) {
                    Boolean ok = false;
                    for (Component d: comps)
                        if (d.equals(c))
                            ok = true;
                    if (ok == false)
                        layeredPane.remove(c);
                }
                layeredPane.revalidate();
                layeredPane.repaint();
                JLabel lbl = new JLabel("Introduceti autorul : ");
                lbl.setBounds(289, 14, 120, 13);
                layeredPane.add(lbl);
                JTextField tf = new JTextField();
                tf.setBounds(410, 10, 80, 15);
                layeredPane.add(tf);
                JButton submit = new JButton("Gata!");
                submit.setBounds(289, 30, 80, 21);
                layeredPane.add(submit);
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String auth = tf.getText();
                        layeredPane.remove(lbl);
                        layeredPane.remove(tf);
                        layeredPane.remove(submit);
                        layeredPane.revalidate();
                        layeredPane.repaint();
                        Vector < Book > res = s.getBooksByAuthor(auth);
                        if (res == null) {
                            JOptionPane.showMessageDialog(frame, "Nu exista carti scrise de acest autor");
                            return;
                        }
                        String[] headers = {
                            "ID",
                            "Titlu",
                            "Autor",
                            "An lansare",
                            "Pret",
                            "Editura",
                            "Cota"
                        };
                        Object[][] data = new Object[res.size()][7];
                        for (int i = 0; i < res.size(); i++) {
                            data[i][0] = res.get(i).getID();
                            data[i][1] = res.get(i).getTitle();
                            data[i][2] = res.get(i).getAuthor();
                            data[i][3] = res.get(i).getReleaseYear();
                            Float aux = res.get(i).getPrice();
                            DecimalFormat twoDForm = new DecimalFormat("#.00");
                            String totalPrice = twoDForm.format(aux);
                            data[i][4] = totalPrice;
                            data[i][5] = res.get(i).getPublisher();
                            data[i][6] = res.get(i).getQuota();
                        }
                        JTable table = new JTable(data, headers);
                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setBounds(289, 10, 320, 420);
                        layeredPane.add(scrollPane);

                    }
                });
            }
        });


        btnNewButton_7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component[] cc = layeredPane.getComponents();
                for (Component c: cc) {
                    Boolean ok = false;
                    for (Component d: comps)
                        if (d.equals(c))
                            ok = true;
                    if (ok == false)
                        layeredPane.remove(c);
                }
                layeredPane.revalidate();
                layeredPane.repaint();
                JLabel lbl = new JLabel("Introduceti primul an : ");
                lbl.setBounds(289, 14, 150, 13);
                layeredPane.add(lbl);
                JLabel lbl1 = new JLabel("Introduceti al doilea an : ");
                lbl1.setBounds(289, 30, 150, 13);
                layeredPane.add(lbl1);
                JTextField tf = new JTextField();
                tf.setBounds(440, 12, 80, 15);
                layeredPane.add(tf);
                JTextField tf1 = new JTextField();
                tf1.setBounds(440, 30, 80, 15);
                layeredPane.add(tf1);
                JButton submit = new JButton("Gata!");
                submit.setBounds(289, 46, 80, 21);
                layeredPane.add(submit);
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String first = tf.getText();
                        String second = tf1.getText();
                        layeredPane.remove(lbl);
                        layeredPane.remove(lbl1);
                        layeredPane.remove(tf);
                        layeredPane.remove(tf1);
                        layeredPane.remove(submit);
                        layeredPane.revalidate();
                        layeredPane.repaint();

                        try {
                            int fir = Integer.valueOf(first);
                            int sec = Integer.valueOf(second);
                            if (fir > sec) {
                                int aux = fir;
                                fir = sec;
                                sec = aux;
                            }
                            Vector < Book > res = s.getBooksBetweenTwoYears(fir, sec);
                            String[] headers = {
                                "ID",
                                "Titlu",
                                "Autor",
                                "An lansare",
                                "Pret",
                                "Editura",
                                "Cota"
                            };
                            if(res == null) {
                            	JOptionPane.showMessageDialog(frame,  "Nu exista carti scrise intre acesti ani!");
                            	return;
                            }
                            Object[][] data = new Object[res.size()][7];
                            for (int i = 0; i < res.size(); i++) {
                                data[i][0] = res.get(i).getID();
                                data[i][1] = res.get(i).getTitle();
                                data[i][2] = res.get(i).getAuthor();
                                data[i][3] = res.get(i).getReleaseYear();
                                Float aux = res.get(i).getPrice();
                                DecimalFormat twoDForm = new DecimalFormat("#.00");
                                String totalPrice = twoDForm.format(aux);
                                data[i][4] = totalPrice;
                                data[i][5] = res.get(i).getPublisher();
                                data[i][6] = res.get(i).getQuota();
                            }
                            JTable table = new JTable(data, headers);
                            JScrollPane scrollPane = new JScrollPane(table);
                            scrollPane.setBounds(289, 10, 320, 420);
                            layeredPane.add(scrollPane);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, e.getMessage());
                        }

                    }
                });
            }
        });


        btnNewButton_8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component[] cc = layeredPane.getComponents();
                for (Component c: cc) {
                    Boolean ok = false;
                    for (Component d: comps)
                        if (d.equals(c))
                            ok = true;
                    if (ok == false)
                        layeredPane.remove(c);
                }
                layeredPane.revalidate();
                layeredPane.repaint();
                JLabel lbl = new JLabel("Introduceti editura : ");
                lbl.setBounds(289, 14, 120, 13);
                layeredPane.add(lbl);
                JTextField tf = new JTextField();
                tf.setBounds(410, 12, 80, 15);
                layeredPane.add(tf);
                JButton submit = new JButton("Gata!");
                submit.setBounds(289, 30, 80, 21);
                layeredPane.add(submit);
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String publ = tf.getText();
                        layeredPane.remove(lbl);
                        layeredPane.remove(tf);
                        layeredPane.remove(submit);
                        layeredPane.revalidate();
                        layeredPane.repaint();
                        Vector < Book > res = s.getBooksByPublisher(publ);
                        if (res == null) {
                            JOptionPane.showMessageDialog(frame, "Nu exista carti publicate de aceasta editura!");
                            return;
                        }
                        String[] headers = {
                            "ID",
                            "Titlu",
                            "Autor",
                            "An lansare",
                            "Pret",
                            "Editura",
                            "Cota"
                        };
                        Object[][] data = new Object[res.size()][7];
                        for (int i = 0; i < res.size(); i++) {
                            data[i][0] = res.get(i).getID();
                            data[i][1] = res.get(i).getTitle();
                            data[i][2] = res.get(i).getAuthor();
                            data[i][3] = res.get(i).getReleaseYear();
                            Float aux = res.get(i).getPrice();
                            DecimalFormat twoDForm = new DecimalFormat("#.00");
                            String totalPrice = twoDForm.format(aux);
                            data[i][4] = totalPrice;
                            data[i][5] = res.get(i).getPublisher();
                            data[i][6] = res.get(i).getQuota();
                        }
                        JTable table = new JTable(data, headers);
                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setBounds(289, 10, 320, 420);
                        layeredPane.add(scrollPane);
                    }
                });
            }
        });


        btnNewButton_9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component[] cc = layeredPane.getComponents();
                for (Component c: cc) {
                    Boolean ok = false;
                    for (Component d: comps)
                        if (d.equals(c))
                            ok = true;
                    if (ok == false)
                        layeredPane.remove(c);
                }
                layeredPane.revalidate();
                layeredPane.repaint();
                JLabel lbl = new JLabel("Introduceti pretul : ");
                lbl.setBounds(289, 14, 110, 13);
                layeredPane.add(lbl);
                JTextField tf = new JTextField();
                tf.setBounds(400, 12, 80, 15);
                layeredPane.add(tf);
                JButton submit = new JButton("Gata!");
                submit.setBounds(289, 30, 80, 21);
                layeredPane.add(submit);
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String price = tf.getText();
                        layeredPane.remove(lbl);
                        layeredPane.remove(tf);
                        layeredPane.remove(submit);
                        layeredPane.revalidate();
                        layeredPane.repaint();
                        try {
                            Float pricee = Float.valueOf(price);
                            Vector < Book > res = s.getBooksLowerThanAPrice(pricee);
                            if (res == null) {
                                JOptionPane.showMessageDialog(frame, "Nu exista carti mai ieftine decat pretul dat!");
                                return;
                            }
                            String[] headers = {
                                "ID",
                                "Titlu",
                                "Autor",
                                "An lansare",
                                "Pret",
                                "Editura",
                                "Cota"
                            };
                            Object[][] data = new Object[res.size()][7];
                            for (int i = 0; i < res.size(); i++) {
                                data[i][0] = res.get(i).getID();
                                data[i][1] = res.get(i).getTitle();
                                data[i][2] = res.get(i).getAuthor();
                                data[i][3] = res.get(i).getReleaseYear();
                                Float aux = res.get(i).getPrice();
                                DecimalFormat twoDForm = new DecimalFormat("#.00");
                                String totalPrice = twoDForm.format(aux);
                                data[i][4] = totalPrice;
                                data[i][5] = res.get(i).getPublisher();
                                data[i][6] = res.get(i).getQuota();
                            }
                            JTable table = new JTable(data, headers);
                            JScrollPane scrollPane = new JScrollPane(table);
                            scrollPane.setBounds(289, 10, 320, 420);
                            layeredPane.add(scrollPane);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, e.getMessage());

                        }
                    }
                });
            }
        });


    }


    private void ends() {
        this.s.finalize();
    }
}