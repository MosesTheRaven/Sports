package sports.competition;

import Subject.Owner;

public class UnauthorizedOwnerException extends Exception{
    Owner owner;
    public UnauthorizedOwnerException(Owner owner) {
        this.owner = owner;
    }
    
    @Override
    public String toString(){
        return "Nepovolená snaha pridať majitela, ktory nema klub v sutazi: " + this.owner.getName();
    }
}
