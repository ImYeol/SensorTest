package thealphalabs.sensortest;

/**
 * Created by yeol on 16. 1. 21.
 */
public class LowpassFilter {
    float[] output;

    public LowpassFilter() {

    }

    // LowPass Filter
    public  float[] lowPass(float[] input, float alpha) {
        if (output == null){
            output= input;
            return output;
        }

        for (int i = 0; i < input.length; i++) {
            output[i] = output[i] + alpha * (input[i] - output[i]);
        }
        return output;
    }

}
