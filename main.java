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

    HashMap<Integer, String> attrsLabelId = new HashMap<Integer, String>();
    HashMap<String, Integer> postLabelId = new HashMap<String, Integer>();

    int numAttrs = attrsList.length;
    for(int i = 0; i < numAttrs; i++) {
      attrsLabelId.put(i, attrsList[i]);
    }
    //Create a 2D array list to store the table,
    //  where the first dimension is the index of each attributes
    //        the second demension stores the datas of the current attribute
    ArrayList<Attribute> attrTable = new ArrayList<Attribute>();

    Attribute attrNode;
    //Initialize the attrTable
    for(int i = 0; i < attrsList.length; i++) {
      attrNode = new Attribute(attrsList[i]);
      attrTable.add(attrNode);
    }

    int indexOfPosteriors = 0;
    int numOfPostLabels = 2;
    String rowData;
    int numRows = 0;
    String[] labels;
    int replacePostLabel = 0;
    String posteriorLabel = "";

    LabelNode postProb = new LabelNode(attrsList[indexOfPosteriors], numOfPostLabels);
    while(sc.hasNext()) {
      //read row in data table
      rowData = sc.nextLine();
      // parse the value for each attribute
      labels = rowData.split("\t");
      // Go through all attributes value present in this row data
      for(int i = 0; i < labels.length; i++) {
        // System.out.println(i);
        //The currrent attribute value is for the posterior
        if(i == indexOfPosteriors) {
          posteriorLabel = labels[i];
          //A replacement value is not yet assign to this label
          if(postLabelId.get(posteriorLabel) == null) {
            //Assign a replacement label in Integer
            postLabelId.put(posteriorLabel, replacePostLabel);
            replacePostLabel++;
          }

          postProb.add(postLabelId.get(posteriorLabel));
          continue;
        }

        //if the label is not recorded for this attribute
        if(!attrTable.get(i).contains(labels[i])) {
          // System.out.println("New label recorded for Attribute: " + attrsLabelId.get(i) + "\t New Label: " + labels[i]);
          attrTable.get(i).addLabel(labels[i], numOfPostLabels);
          // if(attrTable.get(i).contains(labels[i])) {
          //   System.out.println("Label has been added");
          // } else {
          //   System.out.println("Label not added");
          // }
        }

        attrTable.get(i).add(labels[i], postLabelId.get(posteriorLabel));

        // System.out.println("Saving for Attribute: " + attrsLabelId.get(i));
      }
      numRows++;
    }

    ArrayList<LabelNode> Labels;
    int[] values;
    for(int i = 0; i < numAttrs; i++) {
      if(i == indexOfPosteriors) {
        System.out.println("Attribute Name: Match");
        values = postProb.getLabelValues();
        for(int h = 0; h < numOfPostLabels; h++) {
          System.out.println(h + ": " + values[h] + "\t");
        }
        continue;
      }
      Labels = attrTable.get(i).getLabels();
      System.out.println("Attribute Name: " + attrTable.get(i).getName());
      for(int j = 0; j < Labels.size(); j++) {
        values = Labels.get(j).getLabelValues();
        System.out.println("Label: " + Labels.get(j).getName());
        for(int h = 0; h < numOfPostLabels; h++) {
          System.out.println(h + ": " + values[h] + "\t");
        }
        System.out.println();
      }
    }
    System.out.println("\n\nComputing IG:\n\n");
    double entropy = 0, part_one, part_two, part_three, IC, IG;
    double total_sum = 0;
    values= postProb.getLabelValues();
    for(int i = 0; i < numOfPostLabels; i++) {
      total_sum += values[i];
    }
    for(int i = 0; i < numOfPostLabels; i++) {
      part_one = values[i]/total_sum;
      entropy += (-1) * (part_one)*(Math.log(part_one)/Math.log(2));
    }

    System.out.println("Entropy:" +  entropy);

    double remainder;
    double sum;
    for(int i = 1; i < numAttrs; i++) {
      if(i == indexOfPosteriors) {
        continue;
      }
      Labels = attrTable.get(i).getLabels();
      System.out.println("Attribute Name: " + attrTable.get(i).getName());

      IG = 0; IC = 0;
      for(int j = 0; j < Labels.size(); j++) {
        values = Labels.get(j).getLabelValues();
        sum = 0;
        part_one = 0; part_two = 0; part_three = 0;
        for(int h = 0; h < numOfPostLabels; h++) {
          sum += values[h];
        }
        System.out.println("Sum: " + sum);
        System.out.println("Total Sum: " + total_sum);
        part_one = sum/total_sum;
        System.out.println("Part_One = " + sum + " / " + total_sum );
        System.out.print("Part_Three = ");
        for(int h = 0; h < numOfPostLabels; h++) {
          part_two = values[h]/sum;
          if(part_two == 0) {
            continue;
          }
          part_three += (-1) * (part_two) * (Math.log(part_two)/Math.log(2));
          System.out.print("(-1) * " + part_two + " * " + "(Math.log("+part_two+")/(Math.log(2))) +");
        }
        System.out.println();
        IC += (part_one)*(part_three);
        System.out.println("Part " + j + " IC: " + (part_one*part_three));
        System.out.println();
      }
      IG = entropy - IC;
      System.out.println("IG: " + entropy + " - " + IC);
      System.out.println("IG: " + IG);
      System.out.println("________________________________");
    }
	}
}

// class IG {
//   public IG() {
//
//   }
//   double computeIC(int[] valueCounts) {
//     int attrlValCount = 0;
//     for(int i = 0; i < valueCounts.length; i++) {
//       attrValCount++;
//     }
//     double ICResult = 0.0f;
//     double k;
//     for(int i = 0; i < valueCounts.length; i++) {
//       k = valueCounts[i]/attrValCount;
//       ICResult = (-1) * (k)*Math.log(k);
//     }
//     return ICResult;
//   }
//   double computeIG(int[] valueCounts, int totalCount) {
//
//     double IGResult = this.entropy - this.computeRemainder(valueCounts, totalCount);
//     return IGResult;
//   }
//   double computeRemainder(int[] valueCounts, int totalCount) {
//     double remainderResult = 0.0f;
//     for(int i = 0; i < valueCounts; i++) {
//       remainderResult +=
//     }
//   }
// }

class Attribute {
  String attrName;
  ArrayList<LabelNode> Labels;

  Attribute(String attrName) {
    Labels = new ArrayList<LabelNode>();
    this.attrName = attrName;
  }
  boolean hasLabel(String label) {
    return Labels.contains(label);
  }
  void addLabel(String label, int numOfPostLabels) {
    LabelNode lNode = new LabelNode(label, numOfPostLabels);
    Labels.add(lNode);
  }
  void add(String labelName, int postLabelId) {
    int idx = Labels.indexOf(new LabelNode(labelName, postLabelId));
    // System.out.println("idx: " + idx);
    Labels.get(idx).add(postLabelId);
  }
  String getName() {
    return this.attrName;
  }
  boolean contains(String labelName) {
    return Labels.contains(new LabelNode(labelName, 1));
  }
  ArrayList<LabelNode> getLabels() {
    return this.Labels;
  }
}

class LabelNode {
  String labelName;
  int[] value;
  LabelNode(String labelName, int numOfPostLabels) {
    this.labelName = labelName;
    value = new int[numOfPostLabels];
  }
  void add(int postLabelId) {
    value[postLabelId]++;
  }

  String getLabelName() {
    return this.labelName;
  }

  int[] getLabelValues() {
    return this.value;
  }
  String getName() {
    return this.labelName;
  }
  int getValue(int postLabelId) {
    return this.value[postLabelId];
  }
  @Override
  public int hashCode() {
    return this.labelName.hashCode();
  }
  @Override
  public boolean equals(Object o) {
    return ((LabelNode)o).getLabelName().equals(this.labelName);
  }
}
