package service;

import pojo.User;

public interface UserService {

    /**
     * user registration
     * @param user
     * @return
     * @author Haoran Qi
     * @date
     */
    public void registerUser(User user);

    /**
     * User login
     * @param user
     * @return
     * @author Haoran Qi
     * @date
     */
    public User login(User user);

    /**
     * check if username is valid
     * @param username
     * @return return true:username exists | false:username is valid
     * @author Haoran Qi
     * @date
     */
    public boolean existUser(String username);

}
