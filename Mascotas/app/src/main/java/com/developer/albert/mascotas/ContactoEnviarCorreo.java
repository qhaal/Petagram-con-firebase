package com.developer.albert.mascotas;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactoEnviarCorreo extends AppCompatActivity {
    String correo;
    String contraseña;

    EditText edtNombre;
    EditText edtCorreo;
    EditText edtMensaje;

    Button btnEnviarCorreo;
    Session sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correo_contacto);

        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        edtMensaje = (EditText) findViewById(R.id.edtMensaje);

        btnEnviarCorreo = (Button) findViewById(R.id.btnEnviarCorreo);

        // =================================================================
        correo ="scarfalberth@gmail.com";
        contraseña ="enormously";

        btnEnviarCorreo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Properties propiedades = new Properties();
                propiedades.put("mail.smtp.host","smtp.gmail.com");
                propiedades.put("mail.smtp.socketFactory.port","465");
                propiedades.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                propiedades.put("mail.smtp.auth","true");
                propiedades.put("mail.smtp.port","465");

                try {
                    sesion = Session.getDefaultInstance(propiedades, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo,contraseña);
                        }
                    });
                    if(sesion!=null)
                    {
                        Message mensaje = new MimeMessage(sesion);
                        mensaje.setFrom(new InternetAddress(correo));
                        mensaje.setSubject("Correo de Prueba");
                        mensaje.setRecipients(Message.RecipientType.TO,InternetAddress.parse(edtCorreo.getText().toString()));
                        mensaje.setContent(edtMensaje.getText().toString(),"text/html; charset=utf-8");
                        Transport.send(mensaje);
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });



    }
}
