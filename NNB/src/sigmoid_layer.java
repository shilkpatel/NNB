public class sigmoid_layer extends layer
{

    public sigmoid_layer(int num_inputs, int num_output, char activation)
    {
        super(num_inputs, num_output, activation);
    }
    public vector compute(vector input)
    {
        int lenlayers = layers.length;
        double[] ans = new double[lenlayers];
        for(int i =0;i<lenlayers;i++)
        {
            ans[i]=sigmoid(vector.dot(input,layers[i])+bias.v[i]);
        }
        return new vector(ans);
    }
}
