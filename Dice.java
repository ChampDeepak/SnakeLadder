import java.util.Random;

public class Dice 
{
    private static Dice instance; 
    private Dice() {};
    
    public static Dice getInstance()
    {
        if(instance==null)
        {
                synchronized(Dice.class){
                if(instance==null){
                    instance = new Dice(); 
                }
            }
        }
        return instance; 
    }

    public int roll(){
        int max = 6;
        int min = 1;
        Random randumNum = new Random(); 
        return randumNum.nextInt((max-min)+1)+min; 
    }
}
