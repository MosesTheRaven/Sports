package sports.base;

import java.io.Serializable;

import sports.bridgeExport.Exporter;
import sports.filter.FilterStrategy;
import java.util.Iterator;
import java.util.NoSuchElementException;
import sports.io.DefaultOutputStrategy;
import sports.io.OutputStrategy;
import sports.visitor.SportItemVisitor;


public abstract class SportItem implements Iterable<SportItem>, Serializable{
    protected String name;
    protected double rating;
    protected Exporter exporter;
    
    OutputStrategy outputStrategy = new DefaultOutputStrategy();
    
    public SportItem(String name, double rating){
        this.name=name;
        this.rating=rating;
    }
    
    public SportItem(){        
    }
    
    public void print(int level) {
        if (level > 0) {
            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }
            System.out.print("--> ");
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.outputStrategy.formatOutput(this.name, this.rating);
    }
    
    public OutputStrategy getOutputStrategy() {
        return outputStrategy;
    }
    public void setOutputStrategy(OutputStrategy outputStrategy) {
        this.outputStrategy = outputStrategy;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private class FilterSportItemIterator implements Iterator<SportItem> {

        Iterator<SportItem> innerIterator;
        FilterStrategy filter;
        boolean haveNext=false;
        SportItem myNext=null;
        
        public FilterSportItemIterator(Iterator<SportItem> innerIterator, FilterStrategy filter) {
            this.innerIterator = innerIterator;
            this.filter = filter;
        }
                
        @Override
        public boolean hasNext() {
            if (haveNext) return true;
            while (innerIterator.hasNext()){
                myNext=innerIterator.next();
                if (filter.test(myNext)) {
                    haveNext=true;
                    return true;
                }
            }
            return false;
        }

        @Override
        public SportItem next() {
            if (!haveNext) throw new NoSuchElementException();
            haveNext=false;
            return myNext;
        }
    
    }
    
    public Iterator<SportItem> iterator(FilterStrategy filter) {
        return new FilterSportItemIterator(iterator(), filter);
    }
    
    public abstract void acceptVisitor(SportItemVisitor visitor);
    
    public void setExporter(Exporter ex) {
    	exporter = ex;
    }
    
    public void export() {
    	exporter.export(this);
    }
}
