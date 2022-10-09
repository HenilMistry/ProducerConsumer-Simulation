package BoundedBuffer.Buffers;

import BoundedBuffer.Crops.Crop;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    protected static List<Crop> cropList;
    protected static int STORAGE_CAPACITY;

    public Warehouse(int c) {
        STORAGE_CAPACITY = c;
        cropList = new ArrayList<>(STORAGE_CAPACITY);
    }

    public Warehouse() {
        this(10);
    }

    synchronized public void printCrops() {
        System.out.println("Items in warehouse : ");
        for (Crop c : cropList) {
            System.out.print(c+"  ");
        }
        System.out.println("\n---------------------------------------------------------------------------------");
    }

    public void storeCrop(Crop crop) {
        synchronized (cropList) {
            if(cropList.size() < STORAGE_CAPACITY) {
                cropList.add(crop);
                System.out.println(">> Farmer has delivered "+crop+" to warehouse.");
            } else {
                System.out.println(">> Warehouse is full! Cannot store "+crop);
            }
        }
    }

    public Crop getCrop(Crop crop) {
        synchronized (cropList) {
            if(!cropList.isEmpty()) {

                System.out.println(">>>> Customer wants : "+crop);
                System.out.println(">>>> By customer's eye site first crop is : "+cropList.get(0));
                if(cropList.get(0).toString().compareTo(crop.toString())==0) {
                    Crop requiredCrop = cropList.get(0);
                    cropList.remove(0);
                    return requiredCrop;
                } else if (cropList.get(0).toString().compareTo("EOP")==0) {
                    Crop endCrop = cropList.get(0);
                    cropList.remove(0);
                    return endCrop;
                } else {
                    System.out.println(">>>> Cannot find you the "+crop+" from warehouse!");
                    this.printCrops();
                    return new Crop(1000, 5000,"ERROR_CROP");
                }

            } else {

                System.out.println(">>>> Warehouse is empty! Cannot get you "+crop);
                return new Crop(1000, 5000,"ERROR_CROP");
            }
        }
    }
}
