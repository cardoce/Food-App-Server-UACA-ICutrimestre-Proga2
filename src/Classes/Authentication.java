/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Interfaces.AuthInterface;
import java.util.ArrayList;



/**
 *
 * @author fernandocardoce
 */
public class Authentication implements AuthInterface {
    CSVReader csvReader = new CSVReader();
    ArrayList<String[]> users = csvReader.readCSVData("/Assests/user_file.csv");
    
    @Override
    public boolean logIn(String username, String password) {
        boolean canLogin = false;
        for (int i = 0; users.size() > i; i++) {
            if (username.equals(users.get(i)[0]) && password.equals(users.get(i)[1])) {
                System.out.println("User "+users.get(i)[0]);
                 System.out.println("pass"+users.get(i)[1]);
                canLogin = true;
            }
        }
        
        return canLogin;
    }
    
}
