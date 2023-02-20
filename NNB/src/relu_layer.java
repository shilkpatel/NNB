public class relu_layer extends layer{
    public relu_layer(int num_inputs, int num_output, char activation)
    {
        super(num_inputs, num_output, activation);
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
}
