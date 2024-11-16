public class ABB {
    private NodoA raiz;
    
    public ABB(){

    }

    public ABB(int... elements){
        for (int element : elements) {
            this.add(element);
        }
    }

    public NodoA getRaiz(){
        return this.raiz;
    }

    public boolean add(int n){
        return add(raiz, n);
    }

    private boolean add(NodoA r, int n) {
        if (raiz == null) {
            raiz = new NodoA(n);
            return true;
        }else{
            //va del lao izquierdo
            if (n < r.getInfo()) {
                if (r.getIzq() == null) {//el lado izquierdo esta vacio
                    r.setIzq(new NodoA(n, r));
                    balancear(r.getIzq());
                    
                    return true;    
                }else{
                    return add(r.getIzq(), n);
                }
                
            }
            //va del lado derecho
            if (n > r.getInfo()) {
                if (r.getDer() == null) {
                    r.setDer(new NodoA(n, r));
                    balancear(r.getDer());

                    return true;
                }else{
                    return add(r.getDer(), n);
                }
            }
        }
        
        return false;
    }

    public int equilibrio(NodoA nodo){
        return this.altura(nodo.getDer())- this.altura(nodo.getIzq());
    }

    /**
     * Rotacion simple derecha
     * @nodo nodo raiz que presenta el desequilibro
     */
    public void RSD(NodoA nodo){
        NodoA nuevaRaiz = nodo.getIzq();
        NodoA nuevaRaizDer = nuevaRaiz.getDer();

        nuevaRaiz.setDer(nodo);
        nodo.setIzq(nuevaRaizDer);

        if (nuevaRaizDer != null) {
            nuevaRaizDer.setPadre(nodo);
        }

        if (nodo.getPadre() != null) {
            
            if (nodo.getPadre().getIzq() == nodo) {
                nodo.getPadre().setIzq(nuevaRaiz);
            }else{
                nodo.getPadre().setDer(nuevaRaiz);
            }
            
            nuevaRaiz.setPadre(nodo.getPadre());
            nodo.setPadre(nuevaRaiz);
        }else{
            nodo.setPadre(nuevaRaiz);
            nuevaRaiz.setPadre(null);
            this.raiz = nuevaRaiz;
        }
        
    }

    /**
     * Rotacion simple izquierda
     * @nodo nodo raiz que presenta el desequilibro
     */
    public void RSI(NodoA nodo){
        NodoA nuevaRaiz = nodo.getDer();
        NodoA nuevaRaizIzq = nuevaRaiz.getIzq();

        nuevaRaiz.setIzq(nodo);
        nodo.setDer(nuevaRaizIzq);

        if (nuevaRaizIzq != null) {
            nuevaRaizIzq.setPadre(nodo);
        }

        if (nodo.getPadre() != null) {

            if (nodo.getPadre().getIzq() == nodo) {
                nodo.getPadre().setIzq(nuevaRaiz);
            }else{
                nodo.getPadre().setDer(nuevaRaiz);
            }

            nuevaRaiz.setPadre(nodo.getPadre());
            nodo.setPadre(nuevaRaiz);
        }else{
            nodo.setPadre(nuevaRaiz);
            nuevaRaiz.setPadre(null);
            this.raiz = nuevaRaiz;
        }
    }

    /**
     * rotacion doble izquierda
     * @param nodo nodo que presenta el desbalance
     */
    public void RDI(NodoA nodo){
        NodoA nodoToMove = nodo.getIzq().getDer();

        //primera rotacion
        nodo.getIzq().setDer(nodoToMove.getIzq());
        nodoToMove.setIzq(nodo.getIzq());
        nodo.getIzq().setPadre(nodoToMove);
        nodo.setIzq(nodoToMove);
        nodoToMove.setPadre(nodo);

        //segunda rotacion
        NodoA nodoToMoveDer = nodoToMove.getDer();
        nodoToMove.setDer(nodo);

        if (nodo.getPadre() == null) {
            nodoToMove.setPadre(null);
            this.raiz = nodoToMove;
        }else{
            nodoToMove.setPadre(nodo.getPadre());
            if (nodo.getPadre().getIzq() == nodo) {
                nodo.getPadre().setIzq(nodoToMove);
            }else{
                nodo.getPadre().setDer(nodoToMove);
            }
        }

        if (nodoToMoveDer != null) {
            nodoToMoveDer.setPadre(nodo);
        }
        
        nodo.setPadre(nodoToMove);
        nodo.setIzq(nodoToMoveDer);
    }

    public void RDD(NodoA nodo){
        NodoA nodoToMove = nodo.getDer().getIzq();

        //primera rotacion
        nodo.getDer().setIzq(nodoToMove.getDer());
        nodoToMove.setDer(nodo.getDer());
        nodo.getDer().setPadre(nodoToMove);
        nodoToMove.setPadre(nodo);
        nodo.setDer(nodoToMove);

        //segunda rotacion
        NodoA nodoToMoveIzq = nodoToMove.getIzq();
        nodoToMove.setIzq(nodo);
        nodo.setDer(nodoToMoveIzq);

        if (nodo.getPadre() == null) {
            nodoToMove.setPadre(null);
            this.raiz = nodoToMove;
        }else{
            nodoToMove.setPadre(nodo.getPadre());
            
            if (nodo.getPadre().getIzq() == nodo) {
                nodo.getPadre().setIzq(nodoToMove);
            }else{
                nodo.getPadre().setDer(nodoToMove);
            }
        }

        if (nodoToMoveIzq != null) {
            nodoToMoveIzq.setPadre(nodo);
        }

        nodo.setPadre(nodoToMove);
        nodo.setDer(nodoToMoveIzq);

    }

    public void balancear(NodoA nodo){
        if (nodo != null) {
            if (nodo.getPadre() != null) {
                int equilibrioPadre = this.equilibrio(nodo.getPadre());
                int equilibrioActual = this.equilibrio(nodo);

                if (equilibrioPadre < -1 && equilibrioActual < 0) {
                    this.RSD(nodo.getPadre());
                }
                else if (equilibrioPadre > 1 && equilibrioActual > 0) {
                    this.RSI(nodo.getPadre());
                }
                else if (equilibrioPadre < -1 && equilibrioActual > 0) {
                    this.RDI(nodo.getPadre());
                }
                else if (equilibrioPadre > 1 && equilibrioActual < 0) {
                    this.RDD(nodo.getPadre());
                }
                balancear(nodo.getPadre()); 
            }
        }else{
            return;
        }
    }

    @Override
    public String toString() {
        return ""+raiz;
    }

    public String inOrden() {
        return IRD(raiz);
    }

    private String IRD(NodoA n){
        return (n != null) ? IRD(n.getIzq()) + n.getInfo() + IRD(n.getDer()) : " ";
    }

    public String postOrden() {
        return IDR(raiz);
    }

    public String preOrden() {
        return RID(raiz);
    }

    private String RID(NodoA n){
        return (n != null) ? " " +n.getInfo()+ " " + RID(n.getIzq()) + RID(n.getDer()): ""; //raiz izquierda derecho
    }

    private String IDR(NodoA n){
        return (n != null) ? IRD(n.getIzq()) + IRD(n.getDer()) + n.getInfo() : " ";
    }

    public int size() {
        return this.size(raiz);
    }

    public int size(NodoA r){
        if (r != null) {
            return size(r.getIzq()) + size(r.getDer()) + 1;
        }else{
            return 0;
        }
    }

    public int altura(){
        return this.altura(raiz);
    }

    private int altura(NodoA r) {
        if (r != null) {
            return Math.max(altura(r.getIzq()), altura(r.getDer()))+1;
        }else{
            return 0;
        }
    }

    public int hoja() {
        return hoja(raiz);
    }

    public int hoja(NodoA r) {
        // Si el nodo es nulo, no es una hoja, entonces retornamos 0
        if (r == null) {
            return 0;
        }
        
        // Si el nodo no tiene hijos (tanto izquierdo como derecho son nulos), es una hoja
        if (r.getIzq() == null && r.getDer() == null) {
            return 1;
        }
        
        // Si no es hoja, hacemos la llamada recursiva en los hijos izquierdo y derecho
        return hoja(r.getIzq()) + hoja(r.getDer());
    }

    public NodoA find(int num){
        return this.find(raiz, num);
    }

    private NodoA find(NodoA nodo, int num){
        if (nodo != null) {
            if (nodo.getInfo() == num) {
                return nodo;
            }else{
                if (num > nodo.getInfo()) {
                    return find(nodo.getDer(), num);
                }else{
                    return find(nodo.getIzq(), num);
                }
            }
        }else{
            return null;
        }
    }
}
