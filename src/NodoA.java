public class NodoA {
    private int info;
    private NodoA padre;
    private NodoA der;
    private NodoA izq;

    public NodoA(){
        
    }

    public NodoA(int info){
        this.info = info;
    }

    public NodoA(int info, NodoA padre){
        this.info = info;
        this.padre = padre;
    }
    
    public int getInfo(){
        return this.info;
    }

    public void setIzq(NodoA nodo){
        this.izq = nodo;
    }

    public void setDer(NodoA nodo){
        this.der = nodo;
    }

    public void setPadre(NodoA padre){
        this.padre = padre;
    }

    public NodoA getPadre(){
        return this.padre;
    }

    public void getInfo(int info){
        this.info = info;
    }

    public NodoA getIzq(){
        return this.izq;
    }

    public NodoA getDer(){
        return this.der;
    }

    @Override
    public String toString() {
        return ""+info;
    }
}