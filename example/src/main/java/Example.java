import org.bytedeco.javacpp.*;
import static java.lang.Math.*;
import static org.bytedeco.javacpp.v4l2.*;

public class Example 
{

    public static void main( String[] args )
    {
        Loader.load(v4l2.class);

        System.out.println( "Hello aa aaa  World!" );
        int dev=0;
        int flag= v4l2_open("/dev/video0", 0);
        System.out.println(dev);
        System.out.println(flag);
        flag=v4l2_close(flag);
        System.out.println(flag);

        v4l2_get_control(0, 0);

    }
}
