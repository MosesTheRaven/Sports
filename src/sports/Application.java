package sports;

import Subject.NotEnoughMoneyException;
import Subject.Owner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import sports.base.factory.DefaultSportClubFactory;
import sports.bridgeExport.Exporter;
import sports.bridgeExport.ExporterJSON;
import sports.bridgeExport.ExporterXML;
import sports.command.MatchCommand;
import sports.command.PenalizationCommand;
import sports.competition.ClubNotInCompetitionException;
import sports.competition.Competition;
import sports.base.SportOrganization;
import sports.base.SportClub;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import sports.competition.Memento;
import sports.competition.UnauthorizedOwnerException;

public class Application {

    private static void createExample() throws FileNotFoundException, IOException, ClassNotFoundException{     
        
    	SportOrganization nhl = new SportOrganization("NHL", 77);
        SportClub Buffalo = new SportClub("Buffalo Sabres", 7, "Let's go Buffalo!");
        SportClub Washington = new SportClub("Washington Capitals", 6, "Washington, Washington, better than Feri Remington!"); 
        
        
        nhl.addSuborganization(Washington);
        nhl.addSuborganization(Buffalo);
        
        SportOrganization hokejSlovensko = new SportOrganization("Hokej Slovensko", 6);
        SportClub Nitra = new SportClub("HK NITRA", 15.0, "Gol Nitra gol! Bielomodry dame gol! Potom dame este jeden a rozbijeme stadion!");
        SportClub Detva = new SportClub("HC Detva", 14.0, "Detva, Detva, Detva!");
        SportClub Dukla = new SportClub("HK Dukla Trenčín", 12.4 , "Prid, oooom Marian Hossa, domov!");
        
        hokejSlovensko.addSuborganization(new SportOrganization("muži", 1, Arrays.asList(
            new SportOrganization("Tipsport riškolintner Extraliga", 10.7, Arrays.asList(
                Nitra,
                Detva,
                Dukla
            ))
        )));
        
        SportOrganization hockeyWorld = new SportOrganization("World of ice hockey", 500);
        hockeyWorld.addSuborganization(hokejSlovensko);
        hockeyWorld.addSuborganization(nhl);
        
        System.out.println("--- BRIDGE DEMONSTRATION ---");
        Nitra.setExporter(new ExporterJSON());
        Nitra.export();
        Nitra.setExporter(new ExporterXML());
        Nitra.export();
        
        hockeyWorld.setExporter(new ExporterJSON());
        hockeyWorld.export();
        hockeyWorld.setExporter(new ExporterXML());
        hockeyWorld.export();
        
        System.out.println("--- KUKNITE SI JAKE SUBORY TO POVYTVARALO ;-) ---");
        
    }

    private static void myExample(){
        
    //vytvaranie organizacie a sportovych klubov a sutaze
        SportOrganization PL_clubs = new SportOrganization("Premier League", 600);
        SportClub ManchesterUnited = new SportClub("Manchester United", 100);
        SportClub Liverpool = new SportClub("Liverpool", 1);
        SportClub Chelsea = new SportClub("Chelsea", 99);
        SportClub Arsenal = new SportClub("Arsenal", 65);
        PL_clubs.addSuborganization(Arsenal);
        PL_clubs.addSuborganization(ManchesterUnited);
        PL_clubs.addSuborganization(Chelsea);
        PL_clubs.addSuborganization(Liverpool);
        Competition PremierLeague = new Competition(PL_clubs, new DefaultSportClubFactory());
        
    //vypis mojej sutaze PL
        PremierLeague.printClubs();
        
    //vytvaranie majitela, pridelenie nejakych klubov - aj takych, na ktore nema
        Owner Blazers = new Owner("Blazer Family", 32000);
        try {
            Blazers.state.buyClub(ManchesterUnited, 15000); // lebo skutocne vlastnia manchester 
            Blazers.state.buyClub(Chelsea, 35000); // predrazena Chelsea...
        } catch (NotEnoughMoneyException ex) {
            System.err.println(ex.toString());
        }
        try {
            Blazers.state.buyClub(Liverpool, 0); // 0 lebo liverpool je bezcenny
        } catch (NotEnoughMoneyException ex) {
            System.err.println(ex.toString());
        }
            
        Owner Abramovich = new Owner("Abramovich", 65000);
        try{
            Abramovich.state.buyClub(Chelsea, 35000);
        } catch(NotEnoughMoneyException ex) {
            System.err.println(ex.toString());
        }
        System.out.println("\n========================================\n");
        
    // pridavame majitelov do sutaze
        try {
            PremierLeague.registerObserver(Blazers);
        } catch (UnauthorizedOwnerException ex) {
            System.err.println(ex.toString());
        }
        try {
            PremierLeague.registerObserver(Abramovich);
        } catch (UnauthorizedOwnerException ex) {
            System.err.println(ex.toString());
        }
        
        System.out.println("Vypis majitelov pred zaciatkom sezony:\n" + Blazers.toString() + "\n" + Abramovich.toString());
    
        PremierLeague.newSeason();
        
        System.out.println("Vypis majitelov po zaciatku sezony:\n" + Blazers.toString() + "\n" + Abramovich.toString());
    
        System.out.println("\n========================================\n");
        
        System.out.println("Vypis majitelov pred zapasmi:\n" + Blazers.toString() + "\n" + Abramovich.toString());
    
        try {
            PremierLeague.addCommand(new MatchCommand(ManchesterUnited, Chelsea, 6, 0)); // znamy zapas z roku 1960
        } catch (ClubNotInCompetitionException ex) {
            System.err.println(ex.toString());
        }
        try {
            PremierLeague.addCommand(new MatchCommand(ManchesterUnited, Arsenal, 8, 2)); // znamy zapas z roku 2011
        } catch (ClubNotInCompetitionException ex) {
            System.err.println(ex.toString());
        }
        try {
            PremierLeague.addCommand(new MatchCommand(Chelsea, Arsenal, 0, 0)); // znamy zapas z roku 1960
        } catch (ClubNotInCompetitionException ex) {
            System.err.println(ex.toString());
        }
        PremierLeague.executeCommands();
        
        System.out.println("\nVypis majitelov po zapasmi:\n" + Blazers.toString() + "\n" + Abramovich.toString());
    

    //pridame Command zapasu
        try {
            PremierLeague.addCommand(new MatchCommand(ManchesterUnited, Chelsea, 6, 0)); // znamy zapas z roku 1960
        } catch (ClubNotInCompetitionException ex) {
            System.err.println(ex.toString());
        }
        
        
        
    }
    
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {        
    	myExample();
        //vynimku sa prepisuju do inych riadkov - to je v poriadku, nie je to chyba programu ale skor "pomalost" vypisovania v porovnani s pocitanim - ASI :D
    }
    
}
