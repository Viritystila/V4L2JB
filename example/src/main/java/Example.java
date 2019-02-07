import org.bytedeco.javacpp.*;
import static java.lang.Math.*;
import static org.bytedeco.javacpp.v4l2.*;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import java.io.IOException;
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
        System.out.println(v4l2_ioctl(fd, VIDIOC_QUERYCAP, cap));
        //System.out.println(VIDIOC_QUERYCAP);

        System.out.print("Version: ");
        System.out.println(cap.version());
        
        System.out.print("card: ");
        System.out.println(cap.card());
        
        System.out.print("device caps: ");
        System.out.println(cap.device_caps());
        
        System.out.print("V4L2_CAP_VIDEO_CAPTURE: ");
        System.out.println(cap.capabilities() & V4L2_CAP_VIDEO_CAPTURE);

        
        v4l2_format format=new v4l2_format();
        format.type(V4L2_BUF_TYPE_VIDEO_CAPTURE);// = V4L2_BUF_TYPE_VIDEO_CAPTURE;
        v4l2_pix_format fmt_pix=new v4l2_pix_format(); //V4L2_PIX_FMT_MJPEG
        fmt_pix.pixelformat(V4L2_PIX_FMT_MJPEG);
        fmt_pix.width(640);
        fmt_pix.height(480);
        format.fmt_pix(fmt_pix);
        System.out.println(v4l2_ioctl(fd, VIDIOC_S_FMT, format));      
        //format.fmt.pix.width = 800;
        //format.fmt.pix.height = 600;
        System.out.println(v4l2_ioctl(fd, VIDIOC_S_FMT, format));
        System.out.print("Format: ");
        System.out.println(format);
        
        v4l2_requestbuffers bufrequest= new v4l2_requestbuffers();
        bufrequest.type(V4L2_BUF_TYPE_VIDEO_CAPTURE);
        bufrequest.memory(V4L2_MEMORY_MMAP);
        bufrequest.count(1);
        System.out.println(v4l2_ioctl(fd, VIDIOC_REQBUFS, bufrequest));
        
        
        System.out.print("bufrequest: ");
        System.out.println(bufrequest);
        
        v4l2_buffer bufferinfo = new v4l2_buffer();
        Pointer buffer = new Pointer();
        bufferinfo.fill(bufferinfo.length());
        bufferinfo.type(V4L2_BUF_TYPE_VIDEO_CAPTURE);
        bufferinfo.memory(V4L2_MEMORY_MMAP);
        bufferinfo.index(0);
        System.out.print("Allcate buffer");
        System.out.println(v4l2_ioctl(fd, VIDIOC_QUERYBUF, bufferinfo));
        
        System.out.print("bufferinfo.length() ");
        System.out.println(bufferinfo.length());
        
        System.out.print("bufferinfo.physicalBytes() ");
        System.out.println(bufferinfo.physicalBytes());
        
        System.out.print("bufferinfo.bytesused() ");
        System.out.println(bufferinfo.bytesused());   
        
//         Pointer buffer_start = new Pointer();
/*        Pointer bufferv4l2_mmap = v4l2_mmap(
            buffer_start,
            bufferinfo.length(),
            1 | 2,
            4,
            fd,
            bufferinfo.m_offset());
      */      
        //buffer_start.put(bufferv4l2_mmap);
        
        //System.out.print("buffer_start.length() ");
        //System.out.println(buffer_start);
        //buffer_start.fill(bufferinfo.length());

        
//         System.out.print("queue buffer: ");
//         System.out.println(v4l2_ioctl(fd, VIDIOC_QBUF, bufferinfo));
//         // Activate streaming
//         IntPointer type_i = new IntPointer(bufferinfo.type());
//                 System.out.println(type_i);
// 
//         //Pointer type =(Pointer)type_i;
//         System.out.print("Start Streaming ");
//         System.out.println(v4l2_ioctl(fd, VIDIOC_STREAMON, type_i));
//         
//         System.out.print("deque buffer ");
//         System.out.println(v4l2_ioctl(fd, VIDIOC_DQBUF, bufferinfo));
//         
        
        //long v4l2_read(int fd, Pointer buffer, @Cast("size_t") long n);
        //Pointer buffer =new Pointer();
        //buffer.capacity(bufferinfo.length());
                ByteBuffer dbt = bufferinfo.asByteBuffer();

                System.out.print("bufferinfo.asByteBuffer() ");
        System.out.println(bufferinfo.asByteBuffer());
            dbt.rewind();
        byte[] arr = new byte[dbt.remaining()];
        dbt.get(arr);
        System.out.println(arr);
        
                     System.out.print("arr.length ");
        System.out.println(arr.length);
        
        long res =0 ;
        res=v4l2_read(fd, bufferinfo, 2);
        System.out.print("res ");
        System.out.println(res);
        
        System.out.print("bufferinfo.memory() ");
        System.out.println(bufferinfo.memory());
        
                System.out.print("bufferinfo.physicalBytes() ");
        System.out.println(bufferinfo.physicalBytes());
        
        dbt = bufferinfo.asByteBuffer();
        System.out.print("bufferinfo.asByteBuffer() ");
        System.out.println(bufferinfo.asByteBuffer());
        
                System.out.print("bufferinfo.bytesused() ");
        System.out.println(bufferinfo.bytesused());   
        
        
        dbt.rewind();
        arr = new byte[dbt.remaining()];
        dbt.get(arr);
        System.out.println(arr);
        
                           System.out.print("arr.length ");
        System.out.println(arr.length);
        
        try {
            BufferedImage imag=ImageIO.read(new ByteArrayInputStream(arr));
            ImageIO.write(imag, "jpg", new File("./","snap.jpg"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //BufferedImage imag=ImageIO.read(new ByteArrayInputStream(arr));

        //ByteArrayInputStream bis = new ByteArrayInputStream(arr);
        //BufferedImage bImage2 = ImageIO.read(bis);
        //ImageIO.write(bImage2, "jpg", new File("output.jpg") );
        //System.out.println("image created");
        

        //ioctl(fd, VIDIOC_QBUF, &bufferinfo)
        flag=v4l2_close(fd);
        System.out.println(flag);

    }
}
