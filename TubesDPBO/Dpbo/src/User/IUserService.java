/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package User;

import java.util.List;

public interface IUserService {
    boolean registerUser(User user);
    User login(String email, String password);
 
}

