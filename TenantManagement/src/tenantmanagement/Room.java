/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenantmanagement;

/**
 *
 * @author Thuan.Truong
 */
public class Room {
    private int roomIndex;
    private String roomDescription;
    private long roomPrice;
    private boolean isRent;

    /**
     * The constructor to create Room object
     * @param index : index of Room
     * @param Description : Online room's description
     * @param price : price for rent
     */
    public Room (int index, String Description, long price) {
        this.roomIndex = index;
        this.roomDescription = Description;
        this.roomPrice = price;
        this.isRent = false;
    }

    /**
     * Return the index of room
     * @return int
     */
    public int getRoomIndex() {
        return roomIndex;
    }

    /**
     * Return description of room
     * @return  String
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * Return rent price of room
     * @return long
     */
    public long getRoomPrice() {
        return roomPrice;
    }

    /**
     * Get the rent status of room
     * @return boolean
     */
    public boolean isRent() {
        return isRent;
    }

     /**
     * Get the rent status of room
     * @return boolean
     */
    public boolean rent(boolean rentStatus) {
        if (isRent) {
            isRent &= rentStatus;
            return false;
        } else {
            isRent = rentStatus;
            return isRent;
            
        }
    }
}
