/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.pokrik;

import java.util.Random;

/**
 *
 * @author nanka
 */
public class BuraniPokrik extends WrapperPokrik{

    public BuraniPokrik(Pokrik X) {
        super(X);
    }

    @Override
    public void pokrik() {
        String retazec="*******!";
        Random r=new Random();
        r.ints();
        int count1=r.nextInt(7)+1;
        int count2=r.nextInt(7)+1;
        
        StringBuilder sb=new StringBuilder();
        for (int i=0; i<count1; i++){
            sb.append(retazec);
        }
        System.out.println(sb.toString());
        
        wrapped.pokrik();
        
        sb=new StringBuilder();
        for (int i=0; i<count2; i++){
            sb.append(retazec);
        }
        System.out.println(sb.toString());
        
    }
    
}
