package ggc.core;

public class SimpleProduct extends Product{
    
    SimpleProduct(String id){
        super(id);
    }

    @Override
    public String toString() {
        return getID();
    }
    
}
