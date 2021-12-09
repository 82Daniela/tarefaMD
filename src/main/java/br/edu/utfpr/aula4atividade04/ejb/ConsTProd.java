/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.aula4atividade04.ejb;


import br.edu.utfpr.aula4atividade04.model.Usuario;
import java.util.ArrayList;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author default
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "java/Topico"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Topic")
})
public class ConsTProd implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        System.out.println("(Topico) Mensagem recebida pelo " + this.getClass().getSimpleName());
        try {
            ObjectMessage tm = (ObjectMessage) msg;
            System.out.println(tm.getClass().getSimpleName());
            System.out.println("fazendo cast");
            ArrayList<Usuario> pontoUsuarioList;
            pontoUsuarioList = (ArrayList<Usuario>) tm.getObject();
            System.out.println("Sou objeto:"+ tm.getObject());
            for(Usuario u : pontoUsuarioList){
                System.out.println(u.getNome());
            }
        } catch (Exception e) {
            System.out.println("ERRO");
            System.out.println(e.getMessage());
        }
    }

}

