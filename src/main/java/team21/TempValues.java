package team21;

import java.util.Scanner;

/**
 * Created by Christ Xu on 2015-03-06.
 */
public class TempValues


        public void main(String[] args)
        {
            Main app = new Main();
            LocationList locations = app.getLocations();
            Configuration config = app.getConfig();
            Scanner read = new Scanner(System.in);

            //search location
            String input = read.nextLine();

            ////////STUCK!


            //convert to the offical name
            String name = "";
            CurrentTermParser ltParse = new CurrentTermParser(name);


            //Access Current Data
            Location local = locations.searchList(name);
            if (local != null)
            {
                CurrentTerm[] ct = local.getCurrentTerm();
                 double temp;
                 double tempMin;
                 double tempMax;
                 double windSpeed;
                 int windDirection;
                 double pressure;

                //convert data to usable form (icon and description is already string, so extraction is all that's needed)
                String highLow = tempMin + "\t "+ tempMax;
                String currentTemp = temp;
                String wind = "Wind: " +windSpeed  + " "+ windDirection;
                String air = "Air Pressure: " + pressure;
                String iconID;
                String description;



            }

        }

}




