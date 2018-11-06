package net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * POJO de los datos que mantendremos. Implementaremos Parcelable para la transmisión de información mediante
 * bundle
 */

public class Correo implements Parcelable {
    private String from;
    private String subject;
    private String Body;

    public Correo(String from, String subject, String body) {
        this.from = from;
        this.subject = subject;
        Body = body;
    }
//********************Getter y setters
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }
//****************Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.from);
        dest.writeString(this.subject);
        dest.writeString(this.Body);
    }

    protected Correo(Parcel in) {
        this.from = in.readString();
        this.subject = in.readString();
        this.Body = in.readString();
    }

    public static final Creator<Correo> CREATOR = new Creator<Correo>() {
        @Override
        public Correo createFromParcel(Parcel source) {
            return new Correo(source);
        }

        @Override
        public Correo[] newArray(int size) {
            return new Correo[size];
        }
    };
}
