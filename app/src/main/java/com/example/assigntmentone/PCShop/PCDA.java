package com.example.assigntmentone.PCShop;

import java.util.ArrayList;

public class PCDA {
    public ArrayList<PC> pcpart = new ArrayList<>();

    public PCDA() {
        pcpart.add(new PC("Nvidia", "Gtx 1660 Super", "GPU", 2000, 10));
        pcpart.add(new PC("Nvidia", "Rtx 2090", "GPU", 3000, 5));
        pcpart.add(new PC("Nvidia", "Rtx 4070 Super", "GPU", 4500, 8));
        pcpart.add(new PC("Nvidia", "Rtx 5080", "GPU", 6000, 4));
        pcpart.add(new PC("Nvidia", "Rtx 5090", "GPU", 8000, 2));
        pcpart.add(new PC("Intel", "i5 12500k", "CPU", 2000, 10));
        pcpart.add(new PC("Intel", "i7 12700k", "CPU", 3000, 5));
        pcpart.add(new PC("Intel", "i9 12900k", "CPU", 4500, 8));
        pcpart.add(new PC("AMD", "Ryzen 5 5600x", "CPU", 6000, 4));
        pcpart.add(new PC("AMD", "Ryzen 7 5800x", "CPU", 8000, 2));
        pcpart.add(new PC("AMD", "Ryzen 9 5900x", "CPU", 10000, 1));
        pcpart.add(new PC("AMD", "Radeon RX 6900 XT", "GPU", 2500, 10));
        pcpart.add(new PC("AMD", "Radeon RX 6800 XT", "GPU", 2000, 20));
        pcpart.add(new PC("AMD", "Radeon RX 6700 XT", "GPU", 1900, 30));
        pcpart.add(new PC("Seagate", "1tb ", "HDD", 200, 100));
        pcpart.add(new PC("Seagate", "1tb", "SSD", 350, 30));
        pcpart.add(new PC("Seagate", "2tb", "SSD", 500, 20));
        pcpart.add(new PC("Sandisk", "Ultra 1tb", "HDD", 200, 100));
        pcpart.add(new PC("Kingston", "16gb 3000hz", "RAM", 200, 100));
        pcpart.add(new PC("Kingston", "32gb 3000hz", "RAM", 400, 100));

    }

    public ArrayList<PC> getAllPcParts() {
        return pcpart;
    }

    public String[] getCategories() {
        return new String[]{"Any", "GPU", "CPU", "HDD", "SSD", "RAM"};
    }
}
