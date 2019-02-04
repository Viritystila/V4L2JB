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
        int fd= v4l2_open("/dev/video0", 02);
        System.out.println(dev);
        System.out.println(fd);

        System.out.println(V4L2_FBUF_CAP_EXTERNOVERLAY);
        System.out.println(V4L2_STD_SECAM_LC);
        int flag=v4l2_get_control(fd,0);
        System.out.println(flag);
        
        v4l2_capability cap=new v4l2_capability();
        System.out.println(cap.version());
        System.out.println(v4l2_ioctl(fd, VIDIOC_QUERYCAP));
        System.out.println(VIDIOC_QUERYCAP);
        flag=v4l2_close(fd);
        System.out.println(flag);

    }
}
