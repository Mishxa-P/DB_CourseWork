package com.coursework.services;

import com.coursework.dto.Role;
import com.coursework.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import javax.sql.DataSource;
import java.sql.*;

public class UserDetailServiceImpl implements UserDetailsService
{
    @Autowired
    DataSource dataSource;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserbyUsername(username);

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null)
        {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.roles(user.getRole().toString());
        } else
        {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }

    private User findUserbyUsername(String username)
    {
        User user = new User();
        String role = "GUEST";
        try {

            Connection connection = dataSource.getConnection();


            String call = "{call Get_role(?)}";

            try(CallableStatement statement = connection.prepareCall(call))
            {
                statement.setString(1, username);

                ResultSet resultSet =  statement.executeQuery();
                while(resultSet.next())
                {
                    user.setPassword(resultSet.getString("password"));
                    role = resultSet.getString("role");
                }
                connection.close();
                if (role.equals("ADMIN"))
                {
                    user.setRole(Role.ADMIN);
                } else if (role.equals("MANAGER"))
                {
                    user.setRole(Role.MANAGER);
                } else
                {
                    user.setRole(Role.GUEST);
                }
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

}