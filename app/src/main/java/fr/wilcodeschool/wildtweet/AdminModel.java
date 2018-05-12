package fr.wilcodeschool.wildtweet;

/**
 * Created by wilder on 12/05/18.
 */

public class AdminModel extends UserModel {


    private int accessLevel = 0;

    public AdminModel(String username, String password){
        super(username,password);
    }

    public AdminModel(String username, String password, int accessLevel) {
        super(username, password);
        this.accessLevel = accessLevel;
    }

    @Override
    public void login() {
        //TODO : se connecter au paneau d'administration
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
