
class PatternNotExhaustive extends Exception{
    public PatternNotExhaustive(String error){
        super(error);
    }   
}  

class RangeNoteNull extends Exception{
    public RangeNoteNull(){
        super("");
    }   
}  