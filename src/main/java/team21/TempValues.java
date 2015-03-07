package team21;

import java.util.Scanner;

/**
 * Created by Christ Xu on 2015-03-06.
 */
public class TempValues
{

    public void main(String[] args)
    {
        Main app = new Main();
        LocationList locations = app.getLocations();
        Configuration config = app.getConfig();
        Scanner read = new Scanner(System.in);

        //search location (user input)
        String input = read.nextLine();

        //convert to the offical name
        CurrentTermParser ctParse = new CurrentTermParser(input);
        String officalName; ///NEED HELP?

        //Access Current Data (as long as you have the "official" name of the location)
        Location local = locations.searchList(officalName);
        if (local != null)
        {
            //convert data to usable form (icon and description is already string, so extraction is all that's needed)

            //Current Term
            String ctHighLow = local.getCurrentTerm().data[0].getTempMin() + "\t " + local.getCurrentTerm().data[0].getTempMax();
            String ctCurrentTemp = Double.toString(local.getCurrentTerm().data[0].getTemp());
            String ctWind = "Wind: " + local.getCurrentTerm().data[0].getWindSpeed() + " " + local.getCurrentTerm().data[0].getWindDirection();
            String ctAir = "Air Pressure: " + local.getCurrentTerm().data[0].getPressure();
            String ctIconID = local.getCurrentTerm().data[0].getIconID();
            String ctDescription = local.getCurrentTerm().data[0].getDescription();

            //Long Term
            String[] ltHighLow = new String[5];
            String[] ltCurrentTemp = new String[5];
            String[] ltIconID = new String[5];
            String[] ltDescription = new String[5];

            // in each array of 5, store the respective information.
            for (int i = 0; i < 5; i++)
            {
                ltHighLow[i] = local.getLongTerm().data[i].getTempMin() + "\t " + local.getLongTerm().data[i].getTempMax();
                ltCurrentTemp[i] = Double.toString(local.getLongTerm().data[i].getTemp());
                ltIconID[i] = local.getLongTerm().data[i].getIconID();
                ltDescription[i] = local.getLongTerm().data[i].getDescription();
            }


            //Short Term
            String[] stCurrentTemp = new String[8];
            String[] stIconID = new String[8];
            String[] stDescription = new String[8];

            // in each array of 5, store the respective information.
            for (int i = 0; i < 5; i++)
            {
                stCurrentTemp[i] = Double.toString(local.getShortTerm().data[i].getTemp());
                stIconID[i] = local.getLongTerm().data[i].getIconID();
                stDescription[i] = local.getLongTerm().data[i].getDescription();
            }

            //Mars data ?


        }

    }

}




