/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.edu.utfpr.aula4atividade04.ejb;

import javax.ejb.Stateless;

/**
 *
 * @author daniela
 */
@Stateless
public class EjbCalculadora {
    
    public boolean verificarSoma(int valor1, int valor2, int resultado){
        if(valor1+valor2==resultado)
            return true;
        return false;
    }
    
    
}
