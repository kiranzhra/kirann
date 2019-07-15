package fyp.app.aluminportal.model;

public class user {
    String username;
    String phone;
    String address;
    int passingyear;
    String studentType;

    public user(String userUID) {
        this.userUID = userUID;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    private String userUID;

    public user() {
    }

    public user(String username, String phone, String address, int passingyear, String studentType) {
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.passingyear = passingyear;
        this.studentType = studentType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPassingyear() {
        return passingyear;
    }

    public void setPassingyear(int passingyear) {
        this.passingyear = passingyear;
    }
}

//public class user {
//    String username;
//    String phone; String address; int passingyear; String studentType;
//    private String profileImageUrl;
//    private String conversations;
//    private String userUID;
//
//    public user() {
//    }
//
//    public user(String username, String phone, String address, int passingyear, String studentType, String profileImageUrl, String conversations, String userUID) {
//        this.username = username;
//        this.phone = phone;
//        this.address = address;
//        this.passingyear = passingyear;
//        this.studentType = studentType;
//        this.profileImageUrl = profileImageUrl;
//        this.conversations = conversations;
//        this.userUID = userUID;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public int getPassingyear() {
//        return passingyear;
//    }
//
//    public void setPassingyear(int passingyear) {
//        this.passingyear = passingyear;
//    }
//
//    public String getStudentType() {
//        return studentType;
//    }
//
//    public void setStudentType(String studentType) {
//        this.studentType = studentType;
//    }
//
//    public String getProfileImageUrl() {
//        return profileImageUrl;
//    }
//
//    public void setProfileImageUrl(String profileImageUrl) {
//        this.profileImageUrl = profileImageUrl;
//    }
//
//    public String getConversations() {
//        return conversations;
//    }
//
//    public void setConversations(String conversations) {
//        this.conversations = conversations;
//    }
//
//    public String getUserUID() {
//        return userUID;
//    }
//
//    public void setUserUID(String userUID) {
//        this.userUID = userUID;
//    }
//
//
//}
