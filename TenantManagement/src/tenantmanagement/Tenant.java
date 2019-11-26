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
public class Tenant {
    private long tenantID;
    private String tenantName;
    private int tenantRoomIndex;
    
    /**
     * The constructor to create Tenant object
     * @param ID : Identify Number of tenant
     * @param name : Name of tenant
     */
    public Tenant(long ID, String name) {
        this.tenantID = ID;
        this.tenantName = name;
    }

    /**
     * Return the ID number of Tenant
     * @return long
     */
    public long getID() {
        return tenantID;
    }

    /**
     * Return the name of Tenant
     * @return String
     */
    public String getName() {
        return tenantName;
    }

    /**
     * Get the registered Room's index
     * @return int
     */
    public int getTenantRoomIndex() {
        return tenantRoomIndex;
    }

     /**
      * Update the name of Tenant
      * @param tenantName : String
      */
    public void updateTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    /**
     * Register room for tenant
     * @param room : 
     * @return 
     */
    public boolean registerRoom(Room room) {
        if (room.rent(true)) {
            this.tenantRoomIndex = room.getRoomIndex();
            return true;
        } else {
            return false;
        }
    }
}
