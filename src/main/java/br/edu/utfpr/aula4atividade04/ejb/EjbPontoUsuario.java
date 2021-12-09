/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package br.edu.utfpr.aula4atividade04.ejb;


import br.edu.utfpr.aula4atividade04.model.Usuario;
import java.util.ArrayList;
import javax.ejb.Stateful;

/**
 *
 * @author daniela
 */
@Stateful
public class EjbPontoUsuario {

    private ArrayList<Usuario> listaUsuario;


    public EjbPontoUsuario() {
        listaUsuario = new ArrayList<Usuario>();
        
    }

    public void addUsuario(String nome) {
        Usuario usuario = null;

        boolean verificaCadastro = verificarUsuarioCadastrado(nome);

        if (!verificaCadastro) {
            usuario = new Usuario();
            usuario.setNome(nome);
            listaUsuario.add(usuario);

        }
       
    }
    
    public boolean verificarNomeEmBranco(String nome){
        boolean verifica = false;
        if(nome.isBlank())
            verifica=true;
        return verifica;
    }

    public boolean verificarUsuarioCadastrado(String nome) {

        for (Usuario u : listaUsuario) {
            if (u.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }

        return false;
    }

    public void verificar(String nome, boolean verificaResultado) {

        for (Usuario usuario : listaUsuario) {
            if (nome.equalsIgnoreCase(usuario.getNome())) {
                if (verificaResultado) {
                    usuario.setPontuacao(usuario.getPontuacao()+1);
                    return;
                } else {
                    return;
                }

            }
        }

        Usuario u = new Usuario();
        if (verificaResultado) {
            u.setNome(nome);
           u.setPontuacao(u.getPontuacao()+1);
            listaUsuario.add(u);
        } else {
            u.setNome(nome);
            listaUsuario.add(u);
        }

    }

    public Usuario getUsuarioByName(String nome) {
        for (Usuario u : listaUsuario) {
            if (u.getNome().equalsIgnoreCase(nome)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<Usuario> getListaPontoUsuario() {
        return listaUsuario;
    }

}
