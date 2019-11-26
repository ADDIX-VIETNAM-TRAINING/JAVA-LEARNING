/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenantmanagement;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Thuan.Truong
 */
public class TenantManagement {
    public static long START_ID = 249000000;
    public static List roomList = new ArrayList();
    public static Map<Long,Tenant> tenatList = new Hashtable<Long,Tenant>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* Create room for rent data */
        roomList.add(new Room(0, "Not for rent", 0));
        roomList.add(new Room(1, "Near main gate", 3000000));
        roomList.add(new Room(2, "Near main gate", 3000000));
        roomList.add(new Room(3, "Near back yard", 2500000));
        roomList.add(new Room(4, "Near back yard", 2500000));
        roomList.add(new Room(5, "Last coner", 2000000));

        /* Create tenant data */
        addTenant(START_ID + 1, "Nguyen Van A", (Room) roomList.get(4));
        addTenant(START_ID + 2, "Nguyen Van B", (Room) roomList.get(3));
        addTenant(START_ID + 3, "Nguyen Van C", (Room) roomList.get(2));
        addTenant(START_ID + 4, "Nguyen Van D", (Room) roomList.get(1));
        addTenant(START_ID + 5, "Nguyen Van E", (Room) roomList.get(0));
        
        printHeader();
        printManagementInfo();
        printBottom();
        
        /* Modify Tenant 3 */
        editTenant(START_ID + 3, "Nguyen Thi F", (Room) roomList.get(2));
        printHeader();
        printManagementInfo();
        printBottom();

        /* Delete Tenant 3 */
        deleteTenant(START_ID + 3);
        printHeader();
        printManagementInfo();
        printBottom();
    }

    /**
     * Add new tenant
     * @param id : ID number of tenant
     * @param Name : name of tenant
     * @param room : room object be rent
     */
    public static void addTenant (long id, String Name, Room room) {
        Tenant tenant = new Tenant(id, Name); 
        tenant.registerRoom(room);
        tenatList.put(tenant.getID(), tenant);
    }

    /**
     * Delete the tenant
     * @param id : id number of tenant
     */
    public static void deleteTenant(long id) {
        Tenant tenant = (Tenant) tenatList.remove(id);
        if (tenant != null) {
            Room oldRoom = (Room) roomList.get(tenant.getTenantRoomIndex());
            oldRoom.rent(false);
        }
    }

    /**
     * Modify the tenant information (change name or change room)
     * @param id : id of Tenant
     * @param NewName : new name of tenant (if not change fill the previous name)
     * @param newRoom : new room of tenant (if not change fill the previous room)
     */
    public static void editTenant(long id, String NewName, Room newRoom) {
        Tenant tenant = (Tenant) tenatList.get(id);
        if (tenant != null) {
            Room oldRoom = (Room) roomList.get(tenant.getTenantRoomIndex());
            tenant.updateTenantName(NewName);
            if (!oldRoom.equals(newRoom)) {
                oldRoom.rent(false);
                tenant.registerRoom(newRoom);
            }
        }   
    }

    public static void printHeader() {
        System.out.printf("%s %20s %2s %20s %s\n",
                    "Tenant ID",
                    "Tenant Name",
                    "Room No",
                    "Room Description",
                    "Room Price");
    }
    
    public static void printBottom() {
        System.out.printf("-----------------------------\n");
    }
 
    /**
     * Print the tenant information with specified ID
     * @param id : ID number of tenant
     */
    public static void printTenantInfo(long id) {
        Tenant tenant = (Tenant) tenatList.get(id);
        if (tenant != null) {
            Room rentRoom = (Room) roomList.get(tenant.getTenantRoomIndex());
            System.out.printf("%20d %20s %2d %20s %d\n",
                    tenant.getID(), 
                    tenant.getName(), 
                    rentRoom.getRoomIndex(), 
                    rentRoom.getRoomDescription(),
                    rentRoom.getRoomPrice());
        } else {
            System.out.printf("%d %20s %2s %20s %s\n",
                    START_ID + id, 
                    "-",
                    "-", 
                    "-",
                    "-");
        }
    }
    
    /**
     * Print all tenants information
     */
    public static void printManagementInfo() {
//        for (int i = 0; i < tenatList.size(); i++) {
//            printTenantInfo(START_ID + i);
//        }
        for (Map.Entry<Long,Tenant> entry : tenatList.entrySet()) {
            printTenantInfo(entry.getKey());
        }
    }
}
