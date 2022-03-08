package phdhtl.bomoncntt.th2_k60_spinner_listview.model;

public class SinhVien {
    private String masv;
    private String tensv;
    private String gt;
    private String lop;

    public SinhVien(){

    }

    public SinhVien(String masv, String tensv, String gt, String lop) {

        this.masv = masv;
        this.tensv = tensv;
        this.gt = gt;
        this.lop = lop;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    @Override
    public String toString() {
        return masv+"-"+tensv+"-"+gt+"-"+lop;
    }
}
