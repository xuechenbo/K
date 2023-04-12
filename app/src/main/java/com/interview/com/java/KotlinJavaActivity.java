package com.interview.com.java;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class KotlinJavaActivity extends AppCompatActivity {
    private Button butn;

    private void click() {
        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        butn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

    }

    //Netty、Mina  socket框架

    class ServiceSocket {
        public void init() throws IOException {
            //创建ServerSocket
            ServerSocket serverSocket = new ServerSocket(8688);
            //监听
            Socket socket = serverSocket.accept();
            System.out.println("server start listen");
            //输入流
            InputStream is = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);
            String content = null;
            StringBuffer sb = new StringBuffer();
            while ((content = br.readLine()) != null) {
                sb.append(content);
            }
            System.out.println("server receiver: " + sb.toString());

            socket.shutdownInput();

            br.close();
            reader.close();
            is.close();

            socket.close();
            serverSocket.close();
        }
    }

    class ServiceClient {
        public void init() throws IOException {
            Socket socket = new Socket("ip", 8080);
            //2. 输出流
            OutputStream os = socket.getOutputStream();
            //3. 发送数据
            os.write("Hello world".getBytes());
            System.out.println("send message");
            os.flush();
            socket.shutdownOutput();
            os.close();
            socket.close();
        }
    }
}
