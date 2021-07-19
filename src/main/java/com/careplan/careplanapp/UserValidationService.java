/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.careplan.careplanapp;

/**
 *
 * @author Tiago
 */
public class UserValidationService {
    public boolean isUserValid(String user, String password){
        if(user.equals("teste") && password.equals("123")){
            return true;
        }
        
        return false;
    }
}
