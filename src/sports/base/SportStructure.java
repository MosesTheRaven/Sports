package sports.base;

import sports.io.OutputStrategy;

public class SportStructure {
    SportItem root;
    
    public SportStructure(SportItem sportItem){
        root=sportItem;
    }

    public void print() {
        this.root.print(0);
    }
    
    public void setOutputStrategy(OutputStrategy outputStrategy) {
        this.root.setOutputStrategy(outputStrategy);
    }
}
