package test;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class TestApp extends JFrame {
	private Container c;
	private JLabel labelTel, labelMessage;
	private JTextField tfTel, tfMessage;
	private JButton btnSend,btnReset;
	
	static void sendMessage(String to, String text) {
	    String api_key = "NCSAMCROFE2TJ77P";
	    String api_secret = "EH0SCOLZO8PGIDEQLTXOROKWXVONJAYN";
	    Message coolsms = new Message(api_key, api_secret);

	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", to);
	    params.put("from", "01040682883");
	    params.put("type", "SMS");
	    params.put("text", text);
	    params.put("app_version", "test app 1.2");

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println("메시지가 전송되었습니다.");
	      System.out.println(obj.toString());
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
	}
	public void initObject() {
		c = getContentPane();
		labelTel = new JLabel("전화번호");
		labelMessage = new JLabel("메시지");
		tfTel = new JTextField();
		tfMessage = new JTextField();
		btnSend = new JButton("전송");
		btnReset = new JButton("초기화");
	}
	public void initSetting() {
		setSize(500, 200);
		c.setLayout(new GridLayout(3,2));
		
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String to = tfTel.getText();
				String msg = tfMessage.getText();
				sendMessage(to, msg);
			}
		});
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfTel.setText("");
				tfMessage.setText("");
			}
		});
	}
	public void initBatch() {
		c.add(labelTel);
		c.add(tfTel);
		c.add(labelMessage);
		c.add(tfMessage);
		c.add(btnSend);
		c.add(btnReset);
	}
	public TestApp() {
		initObject();
		initSetting();
		initBatch();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TestApp();
	}

}
