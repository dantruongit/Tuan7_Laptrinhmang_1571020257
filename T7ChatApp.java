package tuan7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author cr4zyb0t
 */
public class T7ChatApp extends javax.swing.JFrame {
    DatagramSocket socketReceive;
    int portSend, portReceive;
    Thread threadListen;
    
    public void initUDP(){
        try {
            portSend = Integer.parseInt(txtPortSend.getText());
            portReceive = Integer.parseInt(txtPortReceive.getText());
            InetAddress host = InetAddress.getByName(txtHost.getText());
            if(!host.isReachable(2000)){
                JOptionPane.showMessageDialog(null, "Không thể kết nối đến host !");
                return;
            }
            
            socketReceive = new DatagramSocket(portReceive);
            
            btnStart.setText("Stop chat");
            btnSend.setEnabled(true);
            threadListen = new Thread(()->{
                while(true){
                    try {
                        receiveMessage();
                    } catch (IOException e) {
                        break;
                    }
                }
            });
            threadListen.start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Không thể khởi tạo socket gửi/nhận: " + e.getMessage());
        }
    }
    public void sendMessage(String text){
        //Chuan bi du lieu gui
        //Tao doi tuong
        try (DatagramSocket socket = new DatagramSocket()) {
            //Chuan bi du lieu gui
            byte[] data = text.getBytes();
            //Xac dinh may nhan
            InetAddress address  = InetAddress.getByName(txtHost.getText());
            //Tao goi tin du lieu UDP
            DatagramPacket packet = new DatagramPacket(data, data.length, address, portSend);
            //Gui
            socket.send(packet);
            //Dong ket noi
        } catch (Exception e) {
            
        }
    }
    
    public void receiveMessage() throws IOException{
        byte[] nhan = new byte[1024];
        DatagramPacket dataReceive = new DatagramPacket(nhan, nhan.length);

        socketReceive.receive(dataReceive);
        String strReceive = new String(dataReceive.getData());
        txtChat.setText(txtChat.getText() + "Receive : " + strReceive + "\n");
    }
    
    public T7ChatApp() {
        try {
            LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
            UIManager.setLookAndFeel(lookAndFeelInfos[3].getClassName());
        } catch (Exception e) {
        }
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSend = new javax.swing.JButton();
        txtInput = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPortSend = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPortReceive = new javax.swing.JTextField();
        btnStart = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtHost = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APP CHAT");

        btnSend.setText("Send");
        btnSend.setEnabled(false);
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInputActionPerformed(evt);
            }
        });

        jLabel1.setText("Port Send");

        txtPortSend.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPortSend.setText("12345");

        jLabel2.setText("Port Receive");

        txtPortReceive.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPortReceive.setText("23456");

        btnStart.setText("Start chat");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        jLabel3.setText("Host");

        txtHost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHost.setText("127.0.0.1");

        txtChat.setEditable(false);
        txtChat.setColumns(20);
        txtChat.setRows(5);
        jScrollPane1.setViewportView(txtChat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtInput)
                        .addGap(18, 18, 18)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtPortSend, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPortReceive, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(38, 38, 38)
                        .addComponent(btnStart)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPortReceive)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPortSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        sendMessage(txtInput.getText());
        txtChat.setText(txtChat.getText() + "Me : " + txtInput.getText() + "\n");
        txtInput.setText("");
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if(btnStart.getText().contains("Start")){
            initUDP();
        }
        else{
            btnStart.setText("Start chat");
            btnSend.setEnabled(false);
            txtChat.setText("");
            threadListen.interrupt();
            socketReceive.close();
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void txtInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInputActionPerformed
        btnSendActionPerformed(evt);
    }//GEN-LAST:event_txtInputActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(T7ChatApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(T7ChatApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(T7ChatApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(T7ChatApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new T7ChatApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextField txtHost;
    private javax.swing.JTextField txtInput;
    private javax.swing.JTextField txtPortReceive;
    private javax.swing.JTextField txtPortSend;
    // End of variables declaration//GEN-END:variables
}
