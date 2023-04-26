package com.example.project5cs213;

/**
 * This class is responsible for containing the information related to each
 * individual item in our Donuts recycler view
 * @author Christian Osma, Liam Smith
 */
public class DonutTypeModel {
    String type;
    String flavor;
    int image;

    /**
     * Public constructor for creating a new model object given the donut's
     * type, flavor, and image
     * @param type Donut type
     * @param flavor Donut flavor
     * @param image integer representing the image to be displayed
     */
    public DonutTypeModel(String type, String flavor, int image) {
        this.type = type;
        this.flavor = flavor;
        this.image = image;
    }

    /**
     * Getter method for getting the donut type
     * @return String representing the donut type
     */
    public String getType(){
        return this.type;
    }

    /**
     * Setter method for changing the donut's type
     * @param type Donut type
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * Getter method for getting the donut flavor
     * @return String representing the donut flavor
     */
    public String getFlavor(){
        return this.flavor;
    }

    /**
     * Setter method for changing the donut flavor to the inputted value
     * @param flavor Donut flavor
     */
    public void setFlavor(String flavor){
        this.flavor = flavor;
    }

    /**
     * Getter method for getting the donut image
     * @return integer representing the image
     */
    public int getImage() {
        return image;
    }

    /**
     * Setter method for changing the donut image
     * @param image New donut image
     */
    public void setImage(int image) {
        this.image = image;
    }

}
