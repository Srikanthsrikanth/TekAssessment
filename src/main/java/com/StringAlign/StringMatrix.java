package com.StringAlign;
/**
 * @author Srikanth Sarikonda.
 *Aligns String with the specified format.
 */
public class StringMatrix {

	public static void main(String args[]){
		String[][] string3x3Array  = {
				{"Title1","Title2", "Title3"},
				{"a", "b", "c"},
				{"10000", "200", "30000"}
				};
		int[][] widthMapMatrix = {{0,0,0}, {0,0,0}, {0,0,0}};
		
		for(int i=0; i<string3x3Array.length; i++)
			for(int j=0; j<string3x3Array[i].length; j++){
				widthMapMatrix[j][i] = string3x3Array[i][j].length();
			}
		
		StringBuilder fieldValue = new StringBuilder();
		for(int i=0; i<string3x3Array.length; i++){
			int fieldLength = getMaxFromArray(widthMapMatrix[i]);
			System.out.print("\n |");
			for(int j=0; j<string3x3Array[i].length; j++){
				int len = fieldLength - string3x3Array[i][j].length();
				for(int k=0;k<len/2 && len != 0;k++)
					fieldValue.append(' ');
				fieldValue.append(string3x3Array[i][j]);
				for(int k=0;k<=len/2 && len != 0;k++)
					fieldValue.append(' ');
				fieldValue.append("|");
				System.out.print(fieldValue.toString());
				fieldValue = new StringBuilder("");
			}
		}
		
}

	private static int getMaxFromArray(int[] array) {
		int max = array[0];

		for (int i = 0; i < array.length; i++) {
		    if (array[i] > max) {
		        max = array[i];
		    }
		}
		
		return max;
	}

		
}