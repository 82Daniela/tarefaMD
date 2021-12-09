/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.aula4atividade04.jsf;


import br.edu.utfpr.aula4atividade04.ejb.EjbPontoUsuario;

import br.edu.utfpr.aula4atividade04.model.Usuario;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

/**
 *
 * @author default
 */
@Named(value = "jsfProdutor")
@RequestScoped
public class JsfProdutor {

    @EJB
    private EjbPontoUsuario ejbPontoUsuario;

    public JsfProdutor() {
    }

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java/Topico")
    private Topic topico;

    public void send() {
        try {
            ArrayList<Usuario> pontoUsuarioLista = new ArrayList<>();
            pontoUsuarioLista = ejbPontoUsuario.getListaPontoUsuario();
            for(Usuario u : pontoUsuarioLista){
                System.out.println(u.getNome());
            }
            Connection conn = connectionFactory.createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            ObjectMessage om = session.createObjectMessage();

            om.setObject(pontoUsuarioLista);
            JMSContext context = connectionFactory.createContext();

            context.createProducer().send(topico, om);
        } catch (Exception e) {
            System.out.println("ERRO");
            System.out.println(e.getMessage());
        }
    }

}