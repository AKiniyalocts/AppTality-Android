package com.apps.rightmeow.apptality.model;

import com.apps.rightmeow.apptality.AppTalityApp;
import io.realm.annotations.PrimaryKey;
import java.util.List;

/**
 * Created by AKiniyalocts on 4/21/15.
 */
public class Roster{

  public List<Characters> characters;

  public static class Characters{
    @PrimaryKey
    public String name;
    public Fatality[] fatalities;

    public String getFormattedFatalities(){
      StringBuilder sb = new StringBuilder();

      for(Fatality fatality: fatalities)
        sb.append(fatality.toString());

      return sb.toString();
    }

    public static class Fatality{
      public String name;
      public String code;
      public String location;
      private boolean isPlaystation;


      public String getFormattedCode() {
        String[] returnArray;

        isPlaystation = AppTalityApp.getIsPlaystation();
        String[] splitCode = code.split("\\.");
        System.out.print(splitCode.length + "length");

        returnArray = new String[splitCode.length];

        for (int i = 0; i < splitCode.length; i++) {
          String c = splitCode[i];


            if (c.equals("square")) {

              if(isPlaystation)
                returnArray[i] = "\u2B1C";
              else
                returnArray[i] = "x";

            } else if (c.equals("triangle")) {

              if(isPlaystation)
                returnArray[i] = "\u25B3";
              else
                returnArray[i] = "Y";

            } else if (c.equals("circle")) {

              if(isPlaystation)
                returnArray[i] = "\u25EF";
              else
                returnArray[i] = "B";

            } else if (c.equals("x")) {
              if(isPlaystation)
                returnArray[i] = "x";
              else
                returnArray[i] = "A";
            }
              else if(c.equals("up")){
              returnArray[i] = "\u2191";
            } else if(c.equals("dn")){
              returnArray[i] = "\u2193";
            } else if(c.equals("fwd")){
              returnArray[i] = "\u2192";
            }
            else if(c.equals("bk")) {
              returnArray[i] = "\u2190";
            }
            else if(c.equals("R2"))
              returnArray[i] = "RT";


          }

        String parsedCode = "";
        for(String letter: returnArray)
          parsedCode += " " + letter;

        return parsedCode;

      }
      @Override public String toString() {

        return
            name + "\n" +
            "Combo: " + code + "\n" +
            "Loc: " + location + "\n";
      }
    }
  }
}
