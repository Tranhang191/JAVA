package phdhtl.bomoncntt.tranhang.model;

public class bai1 {
 private float a;
 private float b;

 public bai1(float a, float b) {
  this.a = a;
  this.b = b;
 }

 public float getA() {
  return a;
 }

 public void setA(float a) {
  this.a = a;
 }

 public float getB() {
  return b;
 }

 public void setB(float b) {
  this.b = b;
 }
public String giaiPT() {
    if (a == 0)
        if (b == 0){
            return "PT vô số nghiệm";

        }else{
                return "PT vô nghiệm";
            }

   else {
            return ""+(-b/a);  //ép kiểu
        }
   }
}
