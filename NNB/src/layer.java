import java.lang.Math;
public class layer
{
    vector[] layers;
    char a;
    


    public layer(int num_inputs,int num_output, char activation)
    {
        layers = new vector[num_output];
        for(int i =0;i<num_output;i++)
        {
            layers[i] = new vector(num_inputs);
        }
        a=activation;
    }
    public vector compute(vector input)
    {
        int lenlayers = layers.length;
        double[] ans = new double[lenlayers];
        for(int i =0;i<lenlayers;i++)
        {
            ans[i]=relu(vector.dot(input,layers[i]));
        }
        return new vector(ans);
    }
    public static double relu(double a)
    {
        if(a>0)
        {
            return a;
        }
        return 0;
    }
    public  static double derivative_relu(double a)
    {
        if (a>0)
        {
            return 1;
        }
        return 0;
    }
    public static double sigmoid(double a)
    {
        return 1/(1-Math.pow(Math.E,-1));
    }

    public String serialize()
    {
        String a = "";
        for(vector b:layers)
        {
            for(double c: b.v)
            {
                a+=Double.toString(c)+",";
            }
        }
        return  a.substring(0, a.length() - 1);

    }
}
