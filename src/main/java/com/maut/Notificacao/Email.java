package com.maut.Notificacao;

import java.util.Properties;
import javax.mail.Session;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import com.maut.Cliente;

public class Email implements Notificacao {
    private String fromEmail;
    private String fromSenha;
    private String fromNome = null;
    private Cliente cliente;
    private SimpleEmail email;

    public Email(Cliente cliente, String fromEmail, String fromSenha) {
        this.cliente = cliente;
        this.fromEmail = fromEmail;
        this.fromSenha = fromSenha;

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(
            props, new DefaultAuthenticator(this.fromEmail, this.fromSenha)
        );

        this.email = new SimpleEmail();
        this.email.setMailSession(session);
    }

    public Email(Cliente cliente, String email, String senha, String nome) {
        this(cliente, email, senha);
        this.fromNome = nome;
    }

    public void enviaNotificacao(String operacao, double valor) {
        String destinatario = this.cliente.getEmail();
        String assunto = operacao;
        String mensagem = "A operação foi realizada com valor: " + valor;

        email.setSubject(assunto);

        try {
            if (this.fromNome == null) {
                email.setFrom(this.fromEmail);
            } else {
                email.setFrom(this.fromEmail, this.fromNome);
            }

            email.setMsg(mensagem);
            email.addTo(destinatario);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}