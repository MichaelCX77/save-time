package save.time.session;

import save.time.entity.User;

public class UserSession {
  private static User user;
  
  public UserSession(User user) {
    UserSession.user = user;
  }
  
  public static User getUser() {
    return user;
  }
  
  public static void setUser(User user) {
    UserSession.user = user;
  }
}
