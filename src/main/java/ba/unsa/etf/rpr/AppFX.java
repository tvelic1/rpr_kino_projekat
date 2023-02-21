package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.FilmoviManager;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.domain.filmovi;



import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.*;



public class AppFX {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option addfilms = new Option("f","add-film",false, "Adding new film to filmovi database");
    private static final Option addCategory = new Option("c","add-category",false, "Adding new category to filmovi database");
    private static final Option getfilms = new Option("getF", "get-films",false, "Printing all films from filmovi database");
    private static final Option getCategories = new Option("getC", "get-categories",false, "Printing all categories from filmovi database");
    private static final Option categoryDefinition = new Option(null, "category",false, "Defining category for next added film");

    private static final Option ocjenaDefinition=new Option(null,"ocjena",false,"define ocjena");

    private static final Option imeDefinition=new Option(null,"ime",false,"define name");
    private static final Option trajanjeDefinition=new Option(null,"trajanje",false,"define trajanje");

    /**
     *
     * @param options
     *
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar rprkino.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addfilms);
        options.addOption(addCategory);
        options.addOption(getfilms);
        options.addOption(getCategories);
        options.addOption(categoryDefinition);
        options.addOption(ocjenaDefinition);
        options.addOption(imeDefinition);
        options.addOption(trajanjeDefinition);
        return options;
    }

    public static vrstafilma searchThroughCategories(List<vrstafilma> listOfCategories, String categoryName) {

        vrstafilma category = null;
        category = listOfCategories.stream().filter(cat -> cat.getZanr().toLowerCase().equals(categoryName.toLowerCase())).findAny().get();
        return category;

    }


    /**
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);


        if((cl.hasOption(addfilms.getOpt()) || cl.hasOption(addfilms.getLongOpt())) && cl.hasOption((categoryDefinition.getLongOpt())) && cl.hasOption(ocjenaDefinition.getLongOpt())&&cl.hasOption(trajanjeDefinition.getLongOpt())){
            FilmoviManager filmManager = new FilmoviManager();
            CategoryManager categoryManager = new CategoryManager();
            vrstafilma category = null;
            try {
                category = searchThroughCategories(categoryManager.getAll(), cl.getArgList().get(1));
            }catch(Exception e) {
                System.out.println("There is no category in the list! Try again.");
                System.exit(1);
            }

            filmovi film = new filmovi();
            film.setId_vrsta_filma(category);
            film.setIme(cl.getArgList().get(0));
            film.setOcjena(cl.getArgList().get(2));
            film.setTrajanje(Integer.parseInt(cl.getArgList().get(3)));
            filmManager.add(film);
            System.out.println("You successfully added movie to database!");

        } else if(cl.hasOption(getfilms.getOpt()) || cl.hasOption(getfilms.getLongOpt())){
            FilmoviManager filmManager = new FilmoviManager();
            filmManager.getAll().forEach(q -> System.out.println(q.getIme()));

        } else if(cl.hasOption(addCategory.getOpt()) || cl.hasOption(addCategory.getLongOpt())){
            try {
                CategoryManager categoryManager = new CategoryManager();
                vrstafilma cat = new vrstafilma();
                cat.setZanr(cl.getArgList().get(0));
                categoryManager.add(cat);
                System.out.println("Category has been added successfully");

            }catch(Exception e) {
                System.out.println("There is already category with same name in database! Try again");
                System.exit(1);

            }

        } else if(cl.hasOption(getCategories.getOpt()) || cl.hasOption(getCategories.getLongOpt())){
            CategoryManager categoryManager = new CategoryManager();
            categoryManager.getAll().forEach(c -> System.out.println(c.getZanr()));

        } else {
            printFormattedOptions(options);
            System.exit(-1);

        }

    }
}