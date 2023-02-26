public class step_layer extends layer{
    public step_layer(int num_inputs,int num_outputs,char activation)
    {
        super(num_inputs,num_outputs,activation);
    }


    public vector compute(vector input)
    {
        int lenlayers= layers.length;
        double[] ans = new double[lenlayers];
        for(int i=0;i<lenlayers;i++)
        {
            ans[i]=step_activation(vector.dot(input,layers[i])+bias.v[i]);
        }
        return new vector(ans);
    }
}
