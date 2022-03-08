package phdhtl.bomoncntt.th2.model;

public class PTB2 {
    private float a;
    private float b;
    private float c;

    public PTB2(float a, float b, float c) {
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

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }
    public String giaiPTB2()
    {
        if(a==0){
            //bx+c = 0
            PTB1 b1=new PTB1(b,c);
            return b1.giaiPT();
        }else {
            float d=b*b-4*a*c;
            if (d<0){
                return "PT B2 Vô nghiệm";
            }else if (d==0){
                return  "PT có nghiệm x1=x2="+(-b/(2*a));
            }else {
                return "x1="+((-b+Math.sqrt(d))/(2*a))+"x2"+((-b-Math.sqrt(d)/(2*a)));
            }
        }
    }
}
