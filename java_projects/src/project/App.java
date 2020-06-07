package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class App {
    private JPanel panelMain;
    private JComboBox comboBox;
    private JProgressBar progressBar;
    private JTextArea textArea;
    private JLabel dateTime;
    private JButton buttonEncrypt;
    private JButton buttonDecrypt;
    private JTextArea textArea2;
    private JLabel statusProcent;
    private JRadioButton Hex;
    private JRadioButton Byte;
    private ButtonGroup radioGroup;

    public void status() {
        try {
            for (int i = 0; i <= 100; i++) {

                Thread.sleep(30);

                statusProcent.setFont(new Font(null, Font.BOLD, 14));
                statusProcent.setText(Integer.toString(i) + "%");
                progressBar.setValue(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clock() {

        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Calendar calendar = new GregorianCalendar();
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.get(Calendar.YEAR);

                        int second = calendar.get(Calendar.SECOND);
                        int minute = calendar.get(Calendar.MINUTE);
                        int hour = calendar.get(Calendar.HOUR);

                        if (hour == 0) {
                            hour = 12;
                        }

                        DecimalFormat df = new java.text.DecimalFormat("00.##");

                        dateTime.setText("Time " + df.format(hour) + ":" + df.format(minute) + ":" + df.format(second) +
                                " Date " + df.format(day) + "/" + df.format(month) + "/" + df.format(year));

                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        clock.start();
    }

    public void encryptSHA256() {
        Thread encrypt_status = new Thread() {
            public void run() {
                status();
                String text = textArea.getText();
                Crypto crypto = new Crypter();
                String encrypted = new String(crypto.encryptSHA256(text.getBytes()));
                textArea2.setText(encrypted);
            }
        };
        encrypt_status.start();
    }

    public void encryptCBC654() {
        Thread encrypt_status = new Thread() {
            public void run() {
                status();
                String text = textArea.getText();
                Crypto crypto = new Crypter();
                String encrypted = new String(crypto.encryptCBC654(text.getBytes()));
                textArea2.setText(encrypted);
            }
        };
        encrypt_status.start();
    }

    public void encryptCFB983() {
        Thread encrypt_status = new Thread() {
            public void run() {
                status();
                String text = textArea.getText();
                Crypto crypto = new Crypter();
                String encrypted = new String(crypto.encryptCFB983(text.getBytes()));
                textArea2.setText(encrypted);
            }
        };
        encrypt_status.start();
    }

    public void decryptSHA256() {
        Thread decrypt_status = new Thread() {
            public void run() {
                status();
                String text = textArea.getText();
                Crypto crypto = new Crypter();
                String decrypt = new String(crypto.decryptSHA256(text.getBytes()));
                textArea2.setText(decrypt);
            }
        };
        decrypt_status.start();
    }

    public void decryptCBC654() {
        Thread decrypt_status = new Thread() {
            public void run() {
                status();
                String text = textArea.getText();
                Crypto crypto = new Crypter();
                String decrypt = new String(crypto.decryptCBC654(text.getBytes()));
                textArea2.setText(decrypt);
            }
        };
        decrypt_status.start();
    }

    public void decryptCFB983() {
        Thread decrypt_status = new Thread() {
            public void run() {
                status();
                String text = textArea.getText();
                Crypto crypto = new Crypter();
                String decrypt = new String(crypto.decryptCFB983(text.getBytes()));
                textArea2.setText(decrypt);
            }
        };
        decrypt_status.start();
    }

    static char[] chars = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@',
            '#', '$', '%', '^', '&', '(', ')', '+',
            '-', '*', '/', '[', ']', '{', '}', '=',
            '<', '>', '?', '_', '"', '.', ',', ' '
    };

    public void hexEncrypt(int offset) {
        String text = textArea.getText();

        char[] plain = text.toCharArray();

        for (int i = 0; i < plain.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (j <= chars.length - offset) {
                    if (plain[i] == chars[j]) {
                        plain[i] = chars[j + offset];
                        break;
                    }
                } else if (plain[i] == chars[j]) {
                    plain[i] = chars[j - (chars.length - offset + 1)];
                }
            }
        }
        textArea2.setText(String.valueOf(plain));
    }

    public void hexDecrypt(int offset) {
        String text = textArea.getText();

        char[] cipher = text.toCharArray();

        for (int i = 0; i < cipher.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (j >= offset && cipher[i] == chars[j]) {
                    cipher[i] = chars[j - offset];
                    break;
                }
                if (cipher[i] == chars[j] && j < offset) {
                    cipher[i] = chars[(chars.length - offset + 1) + j];
                    break;
                }
            }
        }
        textArea2.setText(String.valueOf(cipher));
    }

    public void hexEncryptSHA256() {
        Thread encrypt_status = new Thread() {
            public void run() {
                status();
                int offset = 5;
                hexEncrypt(offset);
            }
        };
        encrypt_status.start();
    }

    public void hexEncryptCBC654() {
        Thread encrypt_status = new Thread() {
            public void run() {
                status();
                int offset = 10;
                hexEncrypt(offset);
            }
        };
        encrypt_status.start();
    }

    public void hexEncryptCFB983() {
        Thread encrypt_status = new Thread() {
            public void run() {
                status();
                int offset = 15;
                hexEncrypt(offset);
            }
        };
        encrypt_status.start();
    }

    public void hexDecryptSHA256() {
        Thread encrypt_status = new Thread() {
            public void run() {
                status();
                int offset = 5;
                hexDecrypt(offset);
            }
        };
        encrypt_status.start();
    }

    public void hexDecryptCBC654() {
        Thread encrypt_status = new Thread() {
            public void run() {
                status();
                int offset = 10;
                hexDecrypt(offset);
            }
        };
        encrypt_status.start();
    }

    public void hexDecryptCFB983() {
        Thread encrypt_status = new Thread() {
            public void run() {
                status();
                int offset = 15;
                hexDecrypt(offset);
            }
        };
        encrypt_status.start();
    }

    public App() {

        textArea.setPreferredSize(new Dimension(100, 100));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(false);

        textArea2.setPreferredSize(new Dimension(100, 100));
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(false);

        buttonEncrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String algorithm = (String) comboBox.getSelectedItem();
                Hex.setActionCommand("HEX");
                Byte.setActionCommand("BYTE");

                String radioButtonValue = radioGroup.getSelection().getActionCommand();

                if (algorithm.equals("SHA-256") && radioButtonValue.equals("BYTE")) {
                    encryptSHA256();
                } else if (algorithm.equals("CBC-654") && radioButtonValue.equals("BYTE")) {
                    encryptCBC654();
                } else if (algorithm.equals("CFB-983") && radioButtonValue.equals("BYTE")) {
                    encryptCFB983();
                } else if (algorithm.equals("SHA-256") && radioButtonValue.equals("HEX")) {
                    hexEncryptSHA256();
                } else if (algorithm.equals("CBC-654") && radioButtonValue.equals("HEX")) {
                    hexEncryptCBC654();
                } else if (algorithm.equals("CFB-983") && radioButtonValue.equals("HEX")) {
                    hexEncryptCFB983();
                }
            }
        });
        buttonDecrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String algorithm = (String) comboBox.getSelectedItem();
                Hex.setActionCommand("HEX");
                Byte.setActionCommand("BYTE");

                String radioButtonValue = radioGroup.getSelection().getActionCommand();

                if (algorithm.equals("SHA-256") && radioButtonValue.equals("BYTE")) {
                    decryptSHA256();
                } else if (algorithm.equals("CBC-654") && radioButtonValue.equals("BYTE")) {
                    decryptCBC654();
                } else if (algorithm.equals("CFB-983") && radioButtonValue.equals("BYTE")) {
                    decryptCFB983();
                } else if (algorithm.equals("SHA-256") && radioButtonValue.equals("HEX")) {
                    hexDecryptSHA256();
                } else if (algorithm.equals("CBC-654") && radioButtonValue.equals("HEX")) {
                    hexDecryptCBC654();
                } else if (algorithm.equals("CFB-983") && radioButtonValue.equals("HEX")) {
                    hexDecryptCFB983();
                }
            }
        });

        dateTime.setFont(new Font(null, Font.BOLD, 14));
        clock();

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Project App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1200, 800);
        frame.setVisible(true);
    }


}
