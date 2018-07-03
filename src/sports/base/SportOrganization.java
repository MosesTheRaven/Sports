package sports.base;

import sports.io.OutputStrategy;
import sports.visitor.SportItemVisitor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import sports.base.factory.SportClubFactory;

public class SportOrganization extends SportItem {

    private List<SportItem> suborganizations;
    private Comparator<SportItem> comparator = new PointComparator();

    class SportOrganizationIterator implements Iterator<SportItem> {
        private boolean uzVypisane=false;
        private Iterator<SportItem> it= null;
        private Iterator<SportItem> suborgIt=suborganizations.iterator();
        
        @Override
        public boolean hasNext() {
            if (!uzVypisane) return true;
            if ((it==null)||(!it.hasNext())) {
                if (!suborgIt.hasNext()) return false;
                it=suborgIt.next().iterator();
            }     
            return it.hasNext();
        }

        @Override
        public SportItem next() {
            if (!uzVypisane) {
                uzVypisane=true;
                return SportOrganization.this;
            }
            return it.next();
        }

    }
    
    @Override
    public Iterator<SportItem> iterator() {
        return new SportOrganizationIterator();
    }
    
    private static class PointComparator implements Comparator<SportItem> {
        @Override
        public int compare(SportItem si1, SportItem si2) {
            if (si2.rating>si1.rating) return 1;
            else if (si1.rating>si2.rating) return -1;
            else return 0;
        }
    }
    
    public SportOrganization(String name, double points) {
        super(name, points);
        suborganizations=new ArrayList<>();
    }
    
    public SportOrganization(String name, double points, List<SportItem> suborganizations) {
        super(name, points);
        this.suborganizations = new ArrayList<>(suborganizations);
        this.suborganizations.sort(comparator);
    }

    
    public void addSuborganization(SportItem sportItem){
        suborganizations.add(sportItem);
        suborganizations.sort(comparator);
    }
    
    @Override
    public void print(int level) {
        super.print(level);
        for (SportItem si : suborganizations) {
            si.print(level + 1);
        }
    }
 
    @Override
    public void setOutputStrategy(OutputStrategy outputStrategy) {
        super.setOutputStrategy(outputStrategy);
        for (SportItem  si: suborganizations) {
            si.setOutputStrategy(outputStrategy);
        }
    }
    
    public List<SportItem> getSubOrganizations(){
        return suborganizations;
    }
    
    public void acceptVisitor(SportItemVisitor visitor) {
    	visitor.visit(this);
    }
    
}
