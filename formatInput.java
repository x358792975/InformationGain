import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.management.AttributeList;

public class formatInput {

	public static void main(String[] args) {
		Scanner sc = null;
    BufferedWriter bw = null;

    String dataFile = args[0];
    String newDataFile = args[1];
    try {
      sc = new Scanner(new File(dataFile));
      FileWriter fw = new FileWriter(newDataFile);
      bw = new BufferedWriter(fw);
    } catch (Exception e) {
			e.printStackTrace();
    }


    try {
      String attributes, rowData;
      String[] labels;

      attributes = sc.nextLine();
      bw.write(attributes);
      bw.newLine();
      while(sc.hasNext()) {
        rowData = sc.nextLine();
        // parse the value for each attribute
        labels = rowData.split("\t");

        //match
        bw.write(labels[0] + "\t");
        //samerace
        bw.write(labels[1] + "\t");
        //age
        bw.write((Float.parseFloat(labels[2])>=25f?1:0) + "\t");
        //race
        bw.write((Float.parseFloat(labels[3])>=5f?1:0));
        //sinc_o
        bw.write((Float.parseFloat(labels[4])>=5f?1:0) + "\t");
        //attr_o
        bw.write((Float.parseFloat(labels[5])>=5f?1:0) + "\t");
        //intel_o
        bw.write((Float.parseFloat(labels[6])>=5f?1:0) + "\t");
        //fun_o
        bw.write((Float.parseFloat(labels[7])>=5f?1:0) + "\t");
        //amb_o
        bw.write((Float.parseFloat(labels[8])>=5f?1:0) + "\t");
        //shar_o
        bw.write((Float.parseFloat(labels[9])>=5f?1:0) + "\t");
				//like_o
				bw.write((Float.parseFloat(labels[9])>=5f?1:0) + "\t");

        bw.newLine();
        bw.flush();
      }
      sc.close();
      bw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
