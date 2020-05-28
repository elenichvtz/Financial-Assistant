package com.example.finassistant.ui.user;

import com.example.finassistant.dao.UserDAO;
import com.example.finassistant.domain.User;
import com.example.finassistant.memorydao.UserDAOMemory;

public class UserPresenter {

    private UserView view;
    private User user = new User();
    private UserDAO userDAO;

    public UserPresenter(UserView view){
        this.view = view;
        userDAO = new UserDAOMemory();
    }

    public void setUser(User user){
        this.user = user;
        updateView();
    }

    private void updateView() {
        view.setUserId(user.getId());
        view.setEmail(user.getEmail());
        view.setPassword(user.getPassword());
    }

    public void start(){
        view.setPresenter(this);
        updateView();
        view.open();
    }

    public void save(){
        user.setId(view.getUserId());
        user.setEmail(view.getEmail());
        user.setPassword(view.getPassword());
        view.close();
    }

    public void cancel(){
        view.close();
    }
}
