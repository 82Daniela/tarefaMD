/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.edu.utfpr.aula4atividade04.jsf;

import br.edu.utfpr.aula4atividade04.model.Usuario;
import br.edu.utfpr.aula4atividade04.ejb.EjbCalculadora;
import br.edu.utfpr.aula4atividade04.ejb.EjbPontoUsuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author daniela
 */
@Named(value = "jsfPontoUsuario")
@SessionScoped
public class JsfPontoUsuario implements Serializable {
    
  
    @EJB
    private EjbPontoUsuario ejbPontoUsuario;

    @EJB
    private EjbCalculadora ejbCalcular;

    public JsfPontoUsuario(){
        
    }
    int min_val = 10;
    int max_val = 100;
    double randomNum = Math.random() * (max_val - min_val);
    double randomNum2 = Math.random() * (max_val - min_val);
    @Setter
    @Getter
    int valor1 = (int) randomNum;
    @Setter
    @Getter
    int valor2 = (int) randomNum;
    @Getter
    @Setter
    String resultado;
    @Getter
    @Setter
    String nome = "";
    @Getter
    @Setter
    String msg;
    @Getter
    @Setter
    String msg2 = "";
    @Getter
    @Setter
    boolean verificaResultado;
    @Getter
    @Setter
    private Usuario usuario;
    
   


    public void add() {

        if (ejbPontoUsuario.verificarNomeEmBranco(this.nome)) {
            msg2 = "Nome em branco! Digite um nome válido!";
        } else {
            if (ejbPontoUsuario.verificarUsuarioCadastrado(this.nome)) {
                msg2 = "Usuário já cadastrado! Digite outro nome!";
            } else {
                ejbPontoUsuario.addUsuario(this.nome);
                msg2 = "";
            }

        }

    }

    public void verificar() {

        if (nome== null || ejbPontoUsuario.verificarNomeEmBranco(this.nome)) {
            msg = "Cadastre um usuario!";
            return;
        } else {
            usuario = ejbPontoUsuario.getUsuarioByName(this.nome);
            if (resultado.isBlank()) {
                resultado = Integer.toString(0);
                msg = "VALOR EM BRANCO!!! TENTE NOVAMENTE!!!";
            } else {
                verificaResultado = ejbCalcular.verificarSoma(valor1, valor2, Integer.parseInt(resultado));
                ejbPontoUsuario.verificar(usuario.getNome(), verificaResultado);
                if (verificaResultado) {
                    msg = "ACERTOU!!!";
                    randomNum = Math.random() * (max_val - min_val);
                    randomNum2 = Math.random() * (max_val - min_val);
                    valor1 = (int) randomNum;
                    valor2 = (int) randomNum2;
                } else {
                    msg = "VALOR INCORRETO!!! TENTE NOVAMENTE!!!";

                }
            }

        }

        msg2 = "";
        resultado = "";

    }

    public ArrayList<Usuario> getAll() {
        return ejbPontoUsuario.getListaPontoUsuario();
    }
    
   
}
