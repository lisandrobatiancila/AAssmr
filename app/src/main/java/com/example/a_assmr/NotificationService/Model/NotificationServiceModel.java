package com.example.a_assmr.NotificationService.Model;

public class NotificationServiceModel {
    private String userfname, NOTIF_TYP, ASSMPTN, info3, email;

    public NotificationServiceModel(String userfname, String NOTIF_TYP, String ASSMPTN, String info3, String email) {
        this.userfname = userfname;
        this.NOTIF_TYP = NOTIF_TYP;
        this.ASSMPTN = ASSMPTN;
        this.info3 = info3;
        this.email = email;
    }

    public String getUserfname() {
        return userfname;
    }

    public void setUserfname(String userfname) {
        this.userfname = userfname;
    }

    public String getNOTIF_TYP() {
        return NOTIF_TYP;
    }

    public void setNOTIF_TYP(String NOTIF_TYP) {
        this.NOTIF_TYP = NOTIF_TYP;
    }

    public String getASSMPTN() {
        return ASSMPTN;
    }

    public void setASSMPTN(String ASSMPTN) {
        this.ASSMPTN = ASSMPTN;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}