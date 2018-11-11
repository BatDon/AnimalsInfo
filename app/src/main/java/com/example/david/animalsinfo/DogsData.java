package com.example.david.animalsinfo;
public class DogsData {

    public com.example.david.animalsinfo.DogsAddData[] getInfo() {
        com.example.david.animalsinfo.DogsAddData[] data = new com.example.david.animalsinfo.DogsAddData[20];

        //iterates through rows and sets data
        for (int i = 0; i < 20; i++) {
            com.example.david.animalsinfo.DogsAddData row = new com.example.david.animalsinfo.DogsAddData();
            //iterates through columns
            row.dogName = ("dog " + i + 1);
            row.dogWeight = i * 4 + " pounds";
            row.dogHeight = i * 2 + " inches";
            row.dogLifespan = i * 3 + " years";

//creates row with data
            data[i] = row;
        }
        return data;
    }
}
