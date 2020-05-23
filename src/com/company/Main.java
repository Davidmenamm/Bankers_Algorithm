package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int nProcesses = Integer.parseInt(args[0]);
        int nResTypes = Integer.parseInt(args[1]);
        int [] totalAvailable = new int[nResTypes];
        int [][] max = new int [nProcesses][nResTypes];
        int [][] allocate = new int [nProcesses][nResTypes];


        // welcome msg
        System.out.print("Welcome to the Banker's Algorithm, please follow the instructions to test your sequence.\n\n");

        // input ammount per resource type
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total number of instances AVAILABLE for each of the (" + nResTypes + ") resource types. Separated by spaces.\n");
        String strTotAvail= sc.nextLine();

        String [] totAvailSplit = strTotAvail.split(" ");
        for( int i=0; i<nResTypes; i++ ){
            totalAvailable[i] = Integer.parseInt(totAvailSplit[i]);
        }

        // input maximum number of instances of each resource type per process
        System.out.print("Enter the maximum number of instances of each type of resource (" + nResTypes +
                        ") " + "that each process (" + nProcesses + ") can request.\n");
        System.out.print("Fill the MAX matrix by row, in other words the information needed for each process line by line.\n");

        for(int i=0; i<nProcesses; i++){
            String strMaxLine = sc.nextLine();
            String [] maxLineSplit = strMaxLine.split(" ");

            for(int j=0; j<nResTypes; j++){
                max[i][j] = Integer.parseInt(maxLineSplit[j]);
            }
        }

        // Number of instances of each type of resource, actually assigned to each process
        System.out.print("Enter the number of instances of each type of resource (" + nResTypes  +
                    ") " + "actually assigned to each process(" + nProcesses + ").\n");
        System.out.print("Fill the ALLOCATION matrix by row, in other words the information needed for each process line by line.\n");

        for(int i=0; i<nProcesses; i++){
            String strMaxLine = sc.nextLine();
            String [] maxLineSplit = strMaxLine.split(" ");

            for(int j=0; j<nResTypes; j++){
                allocate[i][j] = Integer.parseInt(maxLineSplit[j]);

            }
        }

        /*
        int nProcessors = 5;
        int nResTypes = 3;
        int [] totalAvailable = new int []{
                10, 5, 7
        };

        int[][] p0MaxAlloc = {
                {7, 5, 3}, {0, 1, 0}
        };
        int[][] p1MaxAlloc = {
                {3, 2, 2}, {2, 0, 0}
        };
        int[][] p2MaxAlloc = {
                {9, 0, 2}, {3, 0, 2}
        };
        int[][] p3MaxAlloc = {
                {2, 2, 2}, {2, 1, 1}
        };
        int[][] p4MaxAlloc = {
                {4, 3, 3}, {0, 0, 2}
        };

        int [][] max = new int[][]{
                p1MaxAlloc[0],
                p3MaxAlloc[0],
                p4MaxAlloc[0],
                p2MaxAlloc[0],
                p0MaxAlloc[0]
        };
        int [][] allocation = new int[][]{
                p1MaxAlloc[1],
                p3MaxAlloc[1],
                p4MaxAlloc[1],
                p2MaxAlloc[1],
                p0MaxAlloc[1]
        };
        */



        BankersAlgorithm ba = new BankersAlgorithm(nProcesses, nResTypes, totalAvailable, max, allocate);

        String revise = ba.safetyCheck();
        System.out.println(ba.print());
        System.out.println(revise);

    }
}
