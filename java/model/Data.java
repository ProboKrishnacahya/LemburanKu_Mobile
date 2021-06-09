package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {

    private int id;
    private String jenis_hari, tanggal, keterangan;
    private int jumlah_jam, total_upah;

    public Data() {
        this.id = 0;
        this.jenis_hari = "";
        this.tanggal = "";
        this.keterangan = "";
        this.jumlah_jam = 0;
        this.total_upah = 0;
    }

    public Data(String jenis_hari, String tanggal, String keterangan, int jumlah_jam, int total_upah) {
        this.id = 0;
        this.jenis_hari = jenis_hari;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.jumlah_jam = jumlah_jam;
        this.total_upah = total_upah;
    }

    public Data(int id, String jenis_hari, String tanggal, String keterangan, int jumlah_jam, int total_upah) {
        this.id = id;
        this.jenis_hari = jenis_hari;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.jumlah_jam = jumlah_jam;
        this.total_upah = total_upah;
    }

    protected Data(Parcel in) {
        id = in.readInt();
        jenis_hari = in.readString();
        tanggal = in.readString();
        keterangan = in.readString();
        jumlah_jam = in.readInt();
        total_upah = in.readInt();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJenis_hari() {
        return jenis_hari;
    }

    public void setJenis_hari(String jenis_hari) {
        this.jenis_hari = jenis_hari;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getJumlah_jam() {
        return jumlah_jam;
    }

    public void setJumlah_jam(int jumlah_jam) {
        this.jumlah_jam = jumlah_jam;
    }

    public int getTotal_upah() {
        return total_upah;
    }

    public void setTotal_upah(int total_upah) {
        this.total_upah = total_upah;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(jenis_hari);
        dest.writeString(tanggal);
        dest.writeString(keterangan);
        dest.writeInt(jumlah_jam);
        dest.writeInt(total_upah);
    }
}
