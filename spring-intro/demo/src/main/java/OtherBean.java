public class OtherBean {
    private HolaMundo hm;


    public void start(){
        System.out.println("\n---"+hm.getMensaje()+"\n---");
    }

    public HolaMundo getHm() {
        return hm;
    }

    public void setHm(HolaMundo hm) {
        this.hm = hm;
    }

    
}
