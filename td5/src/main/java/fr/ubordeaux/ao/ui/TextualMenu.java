package fr.ubordeaux.ao.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import fr.ubordeaux.ao.domain.model.Catalog;
import fr.ubordeaux.ao.domain.model.Reference;
import fr.ubordeaux.ao.domain.type.Price;
import fr.ubordeaux.ao.infrastructure.inmemory.CatalogImpl;

public class TextualMenu {
    private BufferedReader in;
    private PrintWriter out;
    private Catalog catalog;

    protected TextualMenu(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
        initCollection();
    }

    protected TextualMenu(InputStream in, PrintStream out) {
    	Charset cs = Charset.forName("UTF-8");
        this.in = new BufferedReader(new InputStreamReader(in, cs));
        this.out = new PrintWriter(new OutputStreamWriter(out, cs), true);
        initCollection();
    }

    private void initCollection() {
        catalog = new CatalogImpl();
    }

    protected void handleUserInstructions() throws IOException {
        boolean end = false;
        while (!end) {
            out.println("(1) Add new Reference to Catalog,  (2) exit");
            out.println("Your choice 1-2:");
            String choice = null;
            while(choice == null) {
            	choice = in.readLine();
            }
            switch (choice) {
                case "1" : createReferenceAndAddItToCatalog();
                            break;
                case "2" : end = true;
                            break;
                default :
                			      break;
            }
        }
    }

    private void createReferenceAndAddItToCatalog() throws IOException {
        out.println("You ask to create a new reference "
                + "and add it to the root catalog!");
        out.println("Reference id (any string, must be unique) : ");
        String refId = null;
        while(refId == null) {
        	refId = in.readLine();
        }
        out.println("Reference name : ");
        String refName = null;
        while(refName == null) {
        	refName = in.readLine();
        }
        out.println("Reference description : ");
        String refDescription = null;
        while(refDescription == null) {
        	refDescription = in.readLine();
        }
        out.println("Price : ");
        String price = null;
        while(price == null) {
        	price = in.readLine();
        }
        Price refPrice = new Price(Integer.parseInt(price));
        Reference reference = new Reference(refId,
                                            refName,
                                            refDescription,
                                            refPrice);

        catalog.addReference(reference);
        out.println("Reference ("
                   + refId
                   + ") has been created and added to the catalog !");
    }

}
