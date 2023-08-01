package com.example.oo_vko10;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

public class UserStorage {
    private ArrayList<User> users = new ArrayList<User>();
    private static UserStorage single_instance = null;
    public UserStorage() {}

    public static synchronized UserStorage getInstance() {
        if(single_instance == null)
            single_instance = new UserStorage();
        return single_instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(int id) {
        users.remove(id);
    } //optional

    public int getListSize() { return users.size(); } //optional

    public ArrayList<User> getUsers() {
        return users;
    }


    //load users from a file
    public void loadUsers(Context context) {
        try {
            ObjectInputStream ois = new ObjectInputStream(context.openFileInput("users.data"));
            users = (ArrayList<User>) ois.readObject();
            ois.close();
            System.out.println("File read successful!");
        } catch (FileNotFoundException e) {
            System.out.println("File read failed!: "+ e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File read failed!: " +e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("File read failed!: " +e);
            e.printStackTrace();
        }
    }


    //save users to file
    public void saveUsers(Context context) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput("users.data", Context.MODE_PRIVATE));
            oos.writeObject(users);

        } catch (FileNotFoundException e) {
            System.out.println("Saving users failed!: "+ e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Saving users failed!: " +e);
        } finally {
        }
    }


    //Implement comparators here
    static Comparator<User> getLastNameComparator(String direction) {
        if(direction.equals("ascending")) {
            return new Comparator<User>() {
                @Override
                public int compare(User userA, User userB) {
                    return (userA.getLastName()).compareTo(userB.getLastName());
                    //return userA.getLastName().compareToIgnoreCase(userB.getLastName()); //
                }
            };
        } else if(direction.equals("descending")) {
            return new Comparator<User>() {
                @Override
                public int compare(User userA, User userB) {
                    return (userB.getLastName()).compareTo(userA.getLastName());
                    //return userB.getLastName().compareToIgnoreCase(userA.getLastName());
                }
            };
        } else {
            return null;
        }
    }


}
