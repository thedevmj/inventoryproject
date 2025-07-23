/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Random;

/**
 *
 * @author Junaid Mansuri
 */
public class otpgeneration {
    public String generateotp(int length){
    
    String numbers="0123456789";
    Random rand=new Random();
    StringBuilder otp=new StringBuilder();
    for(int i=0;i<length;i++){
    
    otp.append(numbers.charAt(rand.nextInt(numbers.length())));
    
    }
    return otp.toString();
    }
    
}
