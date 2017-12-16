import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

import javax.management.AttributeList;

public class main {

	public static void main(String[] args) {
		Scanner sc = null;
    String dataFile = args[0];
    try {
      sc = new Scanner(new File(dataFile));
    } catch (Exception e) {
			e.printStackTrace();
    }

    String attributes = sc.nextLine();
    System.out.println(attributes);
    String[] attrsList = attributes.split("\t");
    System.out.println(attrsList.length);

    HashMap<Integer, String> attrsLabel= new HashMap<Integer, String>();

    int numAttrs = attrsList.length;
    //Create a 2D array list to store the table,
    //  where the first dimension is the index of each attributes
    //        the second demension stores the datas of the current attribute
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    for(int i = 0; i < numAttrs; i++) {
      attrsLabel.put(i, attrsList[i]);
      table.add(new ArrayList<String>(5000));
    }

    int indexOfPosteriors = 0;
    String rowData;
    int numRows = 0;
    String[] attrsVal;
    while(sc.hasNext()) {
      rowData = sc.nextLine();
      attrsVal = rowData.split("\t");
      for(int i = 0; i < attrsVal.length; i++) {
        table.get(i).add(attrsVal[i]);
      }
      numRows++;
    }

	}
}

// class IG {
//   public IG() {
//
//   }
//   float computeIC(int[] valueCounts) {
//     int attrlValCount = 0;
//     for(int i = 0; i < valueCounts.length; i++) {
//       attrValCount++;
//     }
//     float ICResult = 0.0f;
//     float k;
//     for(int i = 0; i < valueCounts.length; i++) {
//       k = valueCounts[i]/attrValCount;
//       ICResult = (-1) * (k)*Math.log(k);
//     }
//     return ICResult;
//   }
//   float computeIG(int[] valueCounts, int totalCount) {
//
//     float IGResult = this.entropy - this.computeRemainder(valueCounts, totalCount);
//     return IGResult;
//   }
//   float computeRemainder(int[] valueCounts, int totalCount) {
//     float remainderResult = 0.0f;
//     for(int i = 0; i < valueCounts; i++) {
//       remainderResult +=
//     }
//   }
// }
//
// class Attribute {
//   String attrName;
//   ArrayList<LabelNode> Labels;
//
//   Attribute(String attrName) {
//     Labels = new ArrayList<LabelNode>();
//     this.attrName = attrName;
//   }
//   void addLabel(String label) {
//     LabelNode ln = new LabelNode(label);
//     Labels.add(ln);
//   }
// }
//
// class LabelNode() {
//   String labelName;
//   ArrayList<int> value;
//   LabelNode(String labelName, )
// }
