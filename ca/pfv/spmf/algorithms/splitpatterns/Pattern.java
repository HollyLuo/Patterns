package pfv.spmf.algorithms.splitpatterns;

import java.util.ArrayList;
import java.util.List;

import pfv.spmf.algorithms.splitpatterns.cycle.Cycle;
import pfv.spmf.algorithms.splitpatterns.cycle.InternalCycle;
//import pfv.spmf.algorithms.splitpatterns.cycle.Vertex;
//import pfv.spmf.algorithms.splitpatterns.SplitBehaviorChain;
public class Pattern {
	 private int pattern_name;
	 private List<String> trace; //从出发节点到当前节点的轨迹
	 private int weight;
	 private InternalCycle internalCycle;
	 boolean hasInternalCycle ;
	 private List<String> newTrace;
	 
	 
	 public Pattern() {
	     this.trace = new ArrayList<>();//路径
	     this.weight=0;
	     internalCycle = new InternalCycle();
	     hasInternalCycle = false;
	     this.newTrace = new ArrayList<>();
	}
	
	 public List<String> getTrace() {
	    return this.trace;	
	 }
	 public void setTrace(List<String> trace) {
			this.trace = trace;	
		 }
	 
	 public List<String> getNewTrace() {
			 return this.newTrace;	
	 }
	 public void setNewTrace(List<String> newTrace) {
			this.newTrace = newTrace;	
		 }
	 
	 public int getWeight() {
	    return this.weight;	
	 }
	
	 public void setWeight(int weight) {
		  this.weight = weight;	
	 }

	public int getPatternName() {
		return pattern_name;
	}

	public void setPatternName(int pattern_name) {
		this.pattern_name = pattern_name;
	}
	
	public void foundInternalCycle(ArrayList<Cycle> cycleList){
		
//		String patt1.clone()patt2.replace(" ", "");
	    for(Cycle cycle: cycleList){
	    	ArrayList<String> cycleTrace = cycle.getTrace();
	    	if(!trace.equals(cycleTrace)){
	    	    hasInternalCycle = findInternalCycleFromPattern(cycle);
	    	    if(hasInternalCycle){
	    	    	this.internalCycle.printInternalCycle();
//	    	    	List<String> newTrace = removeInternalCycle();
	    	    }
	    	   
//	    	    findInternalCycleFromPattern(trace,cycle);
	    	    
	    	}
	    }
	}
	private static String convertToString(ArrayList<String> cycleTrace) {
		String stringCycle = "";
		for(int i=0;i<cycleTrace.size();i++){
			if(i==cycleTrace.size()-1){
				stringCycle +=cycleTrace.get(i);
			}else{
				stringCycle +=cycleTrace.get(i) + ",";
			}
		}
		return stringCycle;
	}
	
	private boolean findInternalCycleFromPattern(Cycle cycle) {
		String patt1 = trace.toString().replace(" ", "");
		String cycleChain= convertToString(cycle.getTrace());
		
        int num = 0;
//        System.out.println("Pattern:" + oriString);
//        System.out.println("Cycle:"+ sToFind);
        String newString = patt1;
        int index = 0;
        boolean hasInternalCycle=false;
        while (newString.contains(cycleChain)) {
        	if(patt1.indexOf(cycleChain) == 1){
        		index = patt1.indexOf(cycleChain);
        	}else{
        		index = (patt1.indexOf(cycleChain)+1)/2;
			}
      	
//        	System.out.println("start index: "+ index);
//        	System.out.println("indexOf: "+ oriString.indexOf(sToFind));
        	newString = newString.substring(newString.indexOf(cycleChain) + cycleChain.length());
            num ++;
            hasInternalCycle = true;
        }
        
        if(hasInternalCycle){
        	this.internalCycle.setSerialNumber(1);
        	this.internalCycle.setCycleStart(index);
        	this.internalCycle.setTrace(cycle.getTrace());
        	this.internalCycle.setWeight(num);
        }
        return hasInternalCycle;
    }
	
	public void removeInternalCycle() {
//		List<String> newTrace = new ArrayList<String>();
		if(hasInternalCycle){	
//			System.out.println("lodTrace222: "+ this.trace);
			for(int i=0;i<trace.size();i++){
				if(i<internalCycle.getCycleStart()-1 || i>=internalCycle.getCycleEnd()-1){
					this.newTrace.add(trace.get(i));
				}
			}
		}else {
			this.newTrace.addAll(trace);
		}
//		System.out.println("newTrace: "+ newTrace);
//		System.out.println("lodTrace: "+ this.trace);
		
	}
	
//	private static int findInternalCycleFromPattern(List<String> trace,Cycle cycle) {
//        int num = 0;
//        List<String> pattern_trace = trace;
//        List<String> cycle_trace = cycle.getTrace();
//        if(pattern_trace!=cycle_trace){
//        	System.out.println(pattern_trace.containsAll(cycle_trace));
//        }
//        
//        return num;
//    }
	
	
	
	public void printPattern() {
		System.out.println("Pattern: "+ this.trace);
		System.out.println("Weight: " + this.weight);
		
	}
	   

}
