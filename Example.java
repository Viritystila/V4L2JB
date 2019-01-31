/* Start reading here */

import org.bytedeco.javacpp.*;
import static java.lang.Math.*;
//import static org.bytedeco.javacpp.v4l2.*;

public class Example {
    public static void main(String args[]) {
        //Loader.load(v4l2.class);
        v4l2_open("/dev/video0)
        //DoublePointer signal = new DoublePointer(2 * NUM_POINTS);
        //DoublePointer result = new DoublePointer(2 * NUM_POINTS);

        //fftw_plan plan = fftw_plan_dft_1d(NUM_POINTS, signal, result,
          //                                FFTW_FORWARD, (int)FFTW_ESTIMATE);

        //acquire_from_somewhere(signal);
        //fftw_execute(plan);
        //do_something_with(result);

        //fftw_destroy_plan(plan);
    }
}
