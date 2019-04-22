public class CompararFloats {

    public boolean mayor(float a, float b){
        float e = 0.01f;
        if (Math.abs(a-b) < e){
            return true;
        }else{
            if(a>b){
                return true;
            }else{
                return false;
            }
        }


    }
}
