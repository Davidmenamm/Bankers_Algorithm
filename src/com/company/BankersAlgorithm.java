
// Bankers Algorithm class, prevents a deadlock by
// checking safety and allocates if its a safe state
package com.company;

import java.util.Arrays;



public class BankersAlgorithm {

    // Data Members

    private int nProcessors;
    private int nResTypes;
    private int [] totalAvailable;
    private int [] currentlyAvailable;
    private int [][] max;
    private int [][] allocation;
    private int [][] need;


    // Constructor

    public BankersAlgorithm(int nProcessors, int nResTypes, int[] totalAvailable, int[][] max, int[][] allocation) {
        this.nProcessors = nProcessors;
        this.nResTypes = nResTypes;
        this.max = max;
        this.allocation = allocation;
        this.totalAvailable = totalAvailable;
        this.need = new int [nProcessors][nResTypes];
        this.currentlyAvailable = new int [nResTypes];

        // fill need
        for( int i=0; i < nResTypes; i++){
            for( int j=0; j < nProcessors; j++){
                this.need[j][i] = this.max[j][i] - this.allocation[j][i];
            }
        }

        // fill currently available
        for( int i=0; i < nResTypes; i++){
            int count = 0;
            for( int j=0; j < nProcessors; j++){
                count += this.allocation[j][i];
            }
            this.currentlyAvailable[i] = this.totalAvailable[i] - count;
        }

    }

    // Getter and Setter
    public int getnProcessors() {
        return nProcessors;
    }
    public void setnProcessors(int nProcessors) {
        this.nProcessors = nProcessors;
    }

    public int getnResTypes() {
        return nResTypes;
    }

    public void setnResTypes(int nResTypes) {
        this.nResTypes = nResTypes;
    }

    public int[] getCurrentlyAvailable() {
        return currentlyAvailable;
    }

    public void setCurrentlyAvailable(int[] currentlyAvailable) {
        this.currentlyAvailable = currentlyAvailable;
    }

    public int[] getTotalAvailable() {
        return totalAvailable;
    }

    public void setTotalAvailable(int[] totalAvailable) {
        this.totalAvailable = totalAvailable;
    }

    public int[][] getMax() {
        return max;
    }

    public void setMax(int[][] max) {
        this.max = max;
    }

    public int[][] getAllocation() {
        return allocation;
    }

    public void setAllocation(int[][] allocation) {
        this.allocation = allocation;
    }

    public int[][] getNeed() {
        return need;
    }

    public void setNeed(int[][] need) {
        this.need = need;
    }

    // Methods

    // Applies the safety algorithm to avoid a deadlock
    String safetyCheck(){

        int numResTyp = getnResTypes();
        int numProc = getnProcessors();
        int  safe = 0; // check that all resources are in safe state
        String result = "";

        int count1 = 0; // for testing

        boolean breakNested = false; // to break nested loop when necesary

        // run the safety algorithm for each resource type
        for( int i = 0; i<numResTyp && !breakNested; i++){

            int count2 = 0; // for testing

            int [] work = getCurrentlyAvailable(); // currently available of that resource
                                                // + ammount of that resource in previous processes
            boolean [] finish = new boolean [numProc]; // all init to false

            // go through all processes and check their safety
            for( int j=0; ; j++) { //j<numProc; j++) {

                if ((finish[j%numProc] == false) && (need[j%numProc][i] <= work[i])) {
                    finish[j%numProc] = true;
                    work[i] = work[i] + allocation[j%numProc][i];

                }
                // in case in one process need > work or all need <= work
                else {
                    int count = 0;
                    for (int check = 0; check < numProc; check++) {
                        if (finish[check] == true){
                            count += 1;
                        }


                    }
                    if (count == numProc) {
                        // when safe state for a resource
                        // need to check all though
                        safe += 1;

                        break;
                    } else {
                        // when its an unsafe state

                        breakNested = true; // escape the outer loop also
                        break;
                    }

                }

                count2++;
            }

            count1++;
        }

        // return corresponding msg
        if( safe == numResTyp ){
            result = "Safe Sequence!";
        }
        else{
            result = "Unsafe Sequence!!";
        }

        return result;
    }

    // print the data structures
    String print(){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("\nPrint:\n");
        strBuilder.append("\tAllocation:\n");
        for(int i=0; i<getnProcessors(); i++){
            strBuilder.append( "\t" + Arrays.toString( getAllocation()[i] ) + "\n");
        }

        strBuilder.append("\n\tNeed:\n");
        for(int i=0; i<getnProcessors(); i++){
            strBuilder.append( "\t" + Arrays.toString( getNeed()[i] ) + "\n");
        }

        strBuilder.append("\n\tMax:\n");
        for(int i=0; i<getnProcessors(); i++){
            strBuilder.append( "\t" +Arrays.toString( getMax()[i] ) + "\n");
        }

        strBuilder.append("\n\tCurrently Available:\n");
        strBuilder.append("\t" + Arrays.toString( getCurrentlyAvailable()) + "\n");

        return  strBuilder.toString();
    }



}
