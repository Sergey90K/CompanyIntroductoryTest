package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static ArrayList<Integer> readData(String s) {
        ArrayList<Integer> data = new ArrayList<>();
        try {
            File myFile = new File(s);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextInt()) {
                data.add(myScanner.nextInt());
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception thrown : " + e);
        } catch (ClassCastException e) {
            System.out.println("Exception thrown : " + e);
        } catch (NoSuchElementException e) {
            System.out.println("Exception thrown : " + e);
        }
        return data;
    }

    private static int maxValue(ArrayList<Integer> a) {
        return Collections.max(a);
    }

    private static int minValue(ArrayList<Integer> a) {
        return Collections.min(a);
    }

    private static double medianValue(ArrayList<Integer> a) {
        ArrayList<Integer> sorted = new ArrayList<>(a);
        // or  ArrayList<Integer> sorted = (ArrayList<Integer>) a.clone();
        Collections.sort(sorted);
        if (sorted.size() % 2 == 0) {
            return (double) (sorted.get((sorted.size() - 1) / 2) + sorted.get(sorted.size() / 2)) * 0.5;
        } else {
            return (double) sorted.get(sorted.size() / 2);
        }
    }

    private static double meanValue(ArrayList<Integer> a) {
        long sum = 0;
        for (Integer d : a) {
            sum += d;
        }
        return (double) sum / (double) a.size();
    }

    private static ArrayList<Integer> consistency(ArrayList<Integer> a, Boolean max) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> midlResult = new ArrayList<>(a.get(0));
        Integer element = a.get(0);
        Boolean firstElement = true;
        for (Integer i : a) {
            if (firstElement) {
                firstElement = false;
                continue;
            } else if (max ? element < i : element > i) {
                midlResult.add(i);
                element = i;
            } else if (result.size() > midlResult.size()) {
                midlResult.clear();
            } else {
                result.clear();
                result = new ArrayList<>(midlResult);
            }
        }
        return result;
    }

    private static ArrayList<Integer> maxConsistency(ArrayList<Integer> a) {
        return consistency(a, true);
    }

    private static ArrayList<Integer> minConsistency(ArrayList<Integer> a) {
        return consistency(a, false);
    }

    public static void main(String[] args) {
        // read data
        ArrayList<Integer> data = readData("10m.txt");
        //1
        System.out.println("Max value " + maxValue(data));
        //2
        System.out.println("Min value " + minValue(data));
        //3
        System.out.println("Median value " + medianValue(data));
        //4
        System.out.println("Mid average " + meanValue(data));
        //5
        System.out.println("The largest increasing sequence " + maxConsistency(data));
        //6
        System.out.println("The largest decreasing sequence " + minConsistency(data));
    }
}
