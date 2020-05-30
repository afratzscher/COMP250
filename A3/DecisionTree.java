// Name: Anne-Sophie Fratzscher 
// ID: 260705446
import java.io.Serializable;
import java.util.ArrayList;
import java.text.*;
import java.lang.Math;

public class DecisionTree implements Serializable {

	DTNode rootDTNode;
	int minSizeDatalist; //minimum number of datapoints that should be present in the dataset so as to initiate a split
	//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
	public static final long serialVersionUID = 343L;
	public DecisionTree(ArrayList<Datum> datalist , int min) {
		minSizeDatalist = min;
		rootDTNode = (new DTNode()).fillDTNode(datalist);
	}

	class DTNode implements Serializable{
		//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
		public static final long serialVersionUID = 438L;
		boolean leaf;
		int label = -1;      // only defined if node is a leaf
		int attribute; // only defined if node is not a leaf
		double threshold;  // only defined if node is not a leaf



		DTNode left, right; //the left and right child of a particular node. (null if leaf)

		DTNode() {
			leaf = true;
			threshold = Double.MAX_VALUE;
		}



		// this method takes in a datalist (ArrayList of type datum) and a minSizeInClassification (int) and returns
		// the calling DTNode object as the root of a decision tree trained using the datapoints present in the
		// datalist variable
		// Also, KEEP IN MIND that the left and right child of the node correspond to "less than" and "greater than or equal to" threshold
		DTNode fillDTNode(ArrayList<Datum> datalist) {

			//ADD CODE HERE
			if (datalist.size() >= minSizeDatalist) {
				int labelOne = datalist.get(0).y;
				int counter = 0;
				for (Datum data : datalist) {
					if (data.y == labelOne) {
						counter++;
					}					
				}
				if (counter == datalist.size()) {
					DTNode node = new DTNode();
					node.label = labelOne;
					return node;
				}
				else {
					// best attribute test question
					double best_avg_entropy = Double.MAX_VALUE;
					int best_attr = -1;
					double best_threshold = -1;
					ArrayList<Datum> lessThanBest = new ArrayList<Datum>();
					ArrayList<Datum> greaterThanBest = new ArrayList<Datum>();
	
	
					for (int i = 0; i <= 1; i++) {
						for (Datum data : datalist) {
							ArrayList<Datum> lessThan = new ArrayList<Datum>();
							ArrayList<Datum> greaterThan = new ArrayList<Datum>();
							for(Datum dataToCompare : datalist) {
								if (dataToCompare.x[i] < data.x[i]) {
									lessThan.add(dataToCompare);								
								}
								else {
									greaterThan.add(dataToCompare);
								}
							}
							double lessThanEntropy = DecisionTree.this.calcEntropy(lessThan);
							double greaterThanEntropy = DecisionTree.this.calcEntropy(greaterThan);
							double weightLessThan = (double) lessThan.size() /datalist.size();
							double weightGreaterThan = (double) greaterThan.size() / datalist.size();
							double current_avg_entropy = (lessThanEntropy*weightLessThan) + (greaterThanEntropy*weightGreaterThan);	
	
							if (best_avg_entropy > current_avg_entropy){
								best_avg_entropy = current_avg_entropy;
								best_attr = i;
								best_threshold = data.x[i];		
								lessThanBest = lessThan;
								greaterThanBest = greaterThan;
							}
						}
					}
	
					// create new node and store attribute test in that node (attribute and threshold)
					DTNode node = new DTNode();
					node.attribute = best_attr;
					node.threshold = best_threshold;
					node.leaf = false;
	
					// split data items into 2 subsets, data 1 and data2
					node.left = fillDTNode(lessThanBest);
					node.right = fillDTNode(greaterThanBest);
					return node;
				}
			}

			DTNode one = new DTNode();
			// default is already a leaf
			one.label = this.findMajority(datalist);
			return one;
		}



		//This is a helper method. Given a datalist, this method returns the label that has the most
		// occurences. In case of a tie it returns the label with the smallest value (numerically) involved in the tie.
		int findMajority(ArrayList<Datum> datalist)
		{
			int l = datalist.get(0).x.length;
			int [] votes = new int[l];

			//loop through the data and count the occurrences of datapoints of each label
			for (Datum data : datalist)
			{
				votes[data.y]+=1;
			}
			int max = -1;
			int max_index = -1;
			//find the label with the max occurrences
			for (int i = 0 ; i < l ;i++)
			{
				if (max<votes[i])
				{
					max = votes[i];
					max_index = i;
				}
			}
			return max_index;
		}




		// This method takes in a datapoint (excluding the label) in the form of an array of type double (Datum.x) and
		// returns its corresponding label, as determined by the decision tree
		int classifyAtNode(double[] xQuery) {
			
			//ADD CODE HERE
			if (this.leaf) {
				return this.label;			
			}
			else {
				if(xQuery[this.attribute] < this.threshold) {
					return this.left.classifyAtNode(xQuery);
				}
				else {
					return this.right.classifyAtNode(xQuery);
				}
			}
			
			/*if node is a leaf then
			return the label of that node i.e. classify;
			else
			test the data item using question stored at that (internal) node, and determine which child node to go to, based on the answer ;
			return CLASSIFY(child, datum); end
			*/
		}


		//given another DTNode object, this method checks if the tree rooted at the calling DTNode is equal to the tree rooted
		//at DTNode object passed as the parameter
		public boolean equals(Object dt2)
		{
			//ADD CODE HERE
			if (dt2 instanceof DTNode) {
				if (((this.leaf && ((DTNode)dt2).leaf)) || ((!this.leaf && !((DTNode)dt2).leaf))){
					if (this.leaf != true) {
						if (((this.attribute) == (((DTNode) dt2).attribute)) && ((this.threshold)== (((DTNode) dt2).threshold))) {
							if ((this.left).equals(((DTNode) dt2).left) && (this.right).equals(((DTNode) dt2).right)) {
								return true;
							}			
						}	
					}
					else {
						if ((this.label) == ((DTNode) dt2).label) {
							return true;
						}
					}

				}
			}
			return false;
		}
	}



	//Given a dataset, this retuns the entropy of the dataset
	double calcEntropy(ArrayList<Datum> datalist)
	{
		double entropy = 0;
		double px = 0;
		float [] counter= new float[2];
		if (datalist.size()==0)
			return 0;
		double num0 = 0.00000001,num1 = 0.000000001;

		//calculates the number of points belonging to each of the labels
		for (Datum d : datalist)
		{
			counter[d.y]+=1;
		}
		//calculates the entropy using the formula specified in the document
		for (int i = 0 ; i< counter.length ; i++)
		{
			if (counter[i]>0)
			{
				px = counter[i]/datalist.size();
				entropy -= (px*Math.log(px)/Math.log(2));
			}
		}

		return entropy;
	}


	// given a datapoint (without the label) calls the DTNode.classifyAtNode() on the rootnode of the calling DecisionTree object
	int classify(double[] xQuery ) {
		DTNode node = this.rootDTNode;
		return node.classifyAtNode( xQuery );
	}

	// Checks the performance of a DecisionTree on a dataset
	//  This method is provided in case you would like to compare your
	//results with the reference values provided in the PDF in the Data
	//section of the PDF

	String checkPerformance( ArrayList<Datum> datalist)
	{
		DecimalFormat df = new DecimalFormat("0.000");
		float total = datalist.size();
		float count = 0;

		for (int s = 0 ; s < datalist.size() ; s++) {
			double[] x = datalist.get(s).x;
			int result = datalist.get(s).y;
			if (classify(x) != result) {
				count = count + 1;
			}
		}

		return df.format((count/total));
	}


	//Given two DecisionTree objects, this method checks if both the trees are equal by
	//calling onto the DTNode.equals() method
	public static boolean equals(DecisionTree dt1,  DecisionTree dt2)
	{
		boolean flag = true;
		flag = dt1.rootDTNode.equals(dt2.rootDTNode);
		return flag;
	}

}
