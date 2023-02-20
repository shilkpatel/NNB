import java.util.Arrays;

public class Learning
{
    static double learning_rate = 0.1;
    static double h = 0.1;
    // data set is [data points] [inputs] [expected outputs]
    // this outputs the nudges to all the vectors in the form of a network
    public static network delta(network a,vector[][] dataset)
    {
        network delta_v = new network(a.structure);
        // for every element in the dataset
        for(int i=0;i< dataset.length;i++)
        {
            // for every layer in the network
            for(int j = 0;j<a.net.length;j++)
            {
                layer current_layer= a.net[j];
                //for each vector
                for(int k=0;k<current_layer.layers.length;k++)
                {
                    vector current_vector = current_layer.layers[k];
                    // for each element in the vector
                    for(int l =0;l<current_vector.v.length;l++)
                    {
                        // calculate the mse for the original vector
                        // calculate the mse for the vector +h onto the element of the vector
                        // do (f(x+h) - f(x))/h
                        vector network_output = a.out(dataset[i][0]);
                        double initial_mse = network.MSE(network_output,dataset[i][1]);
                        double inital_x =current_vector.geti(l);
                        current_vector.seti(l,inital_x+h);

                        vector augmented_output = a.out(dataset[i][0]);
                        double augmented_mse = network.MSE(augmented_output,dataset[i][1]);

                        double gradient = (augmented_mse-initial_mse)/h;
                        double nudge = gradient*learning_rate*-1;
                        delta_v.net[j].layers[k].v[l] = nudge;
                        current_vector.seti(l,inital_x);

                    }
                }

            }
        }
        return delta_v;
        // we input a vector and then test to see how much it changes when we change each of the weights
        // this is then the gradient and then we times it by the learning rate
    }

}
