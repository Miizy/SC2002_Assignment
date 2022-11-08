import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Schedule implements Serializable{

    private ArrayList<TimeSlot> timeslotarr;
    
    public Schedule(){
        this.timeslotarr=new ArrayList<TimeSlot>();
    }

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

    public void update(){
        //sort
        this.timeslotarr.sort(Comparator.comparing(o -> o.getStartTime()));
    }

    public boolean remove(TimeSlot slot){
        for(int i=0; i<this.timeslotarr.size();i++){
            if(this.timeslotarr.get(i).equals(slot)){
                this.timeslotarr.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<TimeSlot> getList(){
        return this.timeslotarr;
    }

}