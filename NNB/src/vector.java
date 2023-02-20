import java.util.Random;
public class vector
{
    static  Random rand = new Random();
    double[] v;

    public vector(int number_input)
    {
        v= new double[number_input];
        for(int i =0;i<v.length;i++)
        {
            v[i] = (rand.nextDouble()*2)-1;
        }
    }
    public vector(double[] a)
    {
        v=a;
    }
    public vector(vector a)
    {
        v=a.v;
    }


    public double geti(int i)
    {
        return v[i];
    }

    public void seti(int i,double a)
    {
        v[i] = a;
    }

    public static double dot(vector a,vector b)
    {
        double ans=0;
        for(int i =0;i<a.v.length;i++)
        {
            ans += a.geti(i)*b.geti(i);
        }
        return ans;
    }
}
