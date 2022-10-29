import java.util.ArrayList;
import java.util.Comparator;
public class Schedule {

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
            //compare with sorted list check until a date with a larger magnitude appears
            //Check if no boundary error with the later slot and before slot. 
            //insert between by shifting

            for(int i =0; i<arrSize; i++){
                if(this.timeslotarr.get(i).getStartTime().after(slot.getStartTime())){
                    //check if the duration fits with after
                    if(slot.getStartTime().before(this.timeslotarr.get(i).getStartTime()) && slot.getEndTime().before(this.timeslotarr.get(i).getStartTime())){
                        if(i>=1){
                            //compare the length of the movie with before too
                            if(slot.getStartTime().after(this.timeslotarr.get(i-1).getStartTime()) && slot.getEndTime().after(this.timeslotarr.get(i-1).getStartTime())){
                                this.timeslotarr.add(slot);
                                this.update();
                                System.out.println("Sucessfully Added");
                                return true;
                            }
                        }      
                    }
                }
            }
        }

        return false;

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
