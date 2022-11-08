import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * A class that functions a scheduler for the TimeSlots
 * @author Viral Mehta
 */
public class Schedule implements Serializable{
    /**
     * A array list of TimeSlot object like show timing list.
     */
    private ArrayList<TimeSlot> timeslotarr;
    /**
     * Creates the Schedule object
     */
    public Schedule(){
        this.timeslotarr=new ArrayList<TimeSlot>();
    }
    /**
     * Adds the time Slot to the array list. 
     * However checks if there is a scheduling problem,
     * like conflicting timings.
     * @param slot A user wants to adds particular TimeSlot
     * @return True if the slot is added to the array list else will show false
     */
    public boolean add(TimeSlot slot){
        int arrSize= this.timeslotarr.size();
        
        if (arrSize==0){
            this.timeslotarr.add(slot);
            System.out.println("Sucessfully Added");
            return true;
        }
        else{
            //compare with all the things in list
            for(int i=0; i<arrSize; i++){
                if(slot.getStartTime().after(this.timeslotarr.get(i).getStartTime())&& 
                slot.getStartTime().before(this.timeslotarr.get(i).getEndTime())){
                    return false;
                }

                if(slot.getEndTime().after(this.timeslotarr.get(i).getStartTime())&& 
                slot.getEndTime().before(this.timeslotarr.get(i).getEndTime())){
                    return false;
                }
            }

            this.timeslotarr.add(slot);
            this.update();
            return true;

        }
    }
    /**
     * Sorts the array list in ascending slot's starting time.
     */
    public void update(){
        //sort
        this.timeslotarr.sort(Comparator.comparing(o -> o.getStartTime()));
    }
    /**
     * Removes a particular TimeSlot from the array list
     * @param slot TimeSlot that needs to removed
     * @return True or False if removal was successful
     */
    public boolean remove(TimeSlot slot){
        for(int i=0; i<this.timeslotarr.size();i++){
            if(this.timeslotarr.get(i).equals(slot)){
                this.timeslotarr.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     * Gets the array list of TimeSlot
     * @return Array list of obj TimeSlot
     */
    public ArrayList<TimeSlot> getList(){
        return this.timeslotarr;
    }

}