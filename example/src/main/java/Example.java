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

        //Init input
        int fd= v4l2_open("/dev/video0", 02);
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
        fmt_pix.pixelformat(V4L2_PIX_FMT_YUV422P);
        fmt_pix.width(640);//v4l2loopback-ctl set-fps 25 /dev/video0
        fmt_pix.height(480);
        
        int min = fmt_pix.width() * 2;
        if (fmt_pix.bytesperline() < min){
                fmt_pix.bytesperline(min);
        }
        min = fmt_pix.bytesperline() * fmt_pix.height();
        if (fmt_pix.sizeimage() < min){
            fmt_pix.sizeimage(min);
            }
            
        format.fmt_pix(fmt_pix);
        System.out.println(v4l2_ioctl(fd, VIDIOC_S_FMT, format));      
        //format.fmt.pix.width = 800;
        //format.fmt.pix.height = 600;
        System.out.println(v4l2_ioctl(fd, VIDIOC_S_FMT, format));
        System.out.print("Format: ");
        System.out.println(format);
        
        System.out.print("fmt_pix: ");
        System.out.println(fmt_pix.sizeimage());
        v4l2_requestbuffers bufrequest= new v4l2_requestbuffers();
        bufrequest.type(V4L2_BUF_TYPE_VIDEO_CAPTURE);
        bufrequest.memory(V4L2_MEMORY_MMAP);
        bufrequest.count(1);
        System.out.println(v4l2_ioctl(fd, VIDIOC_REQBUFS, bufrequest));
        
        
        System.out.print("bufrequest: ");
        System.out.println(bufrequest);
        
        Pointer usrPtr= new Pointer();
        usrPtr.malloc(fmt_pix.sizeimage());
        
        v4l2_buffer bufferinfo = new v4l2_buffer();
        Pointer buffer = new Pointer();
        bufferinfo.type(V4L2_BUF_TYPE_VIDEO_CAPTURE);
        bufferinfo.memory(V4L2_MEMORY_MMAP);
        bufferinfo.index(0);
        bufferinfo.length(fmt_pix.sizeimage());
        System.out.print("Allcate buffer");
        System.out.println(v4l2_ioctl(fd, VIDIOC_QUERYBUF, bufferinfo));
        
        System.out.print("bufferinfo.length() ");
        System.out.println(bufferinfo.length());
        
        System.out.print("bufferinfo.physicalBytes() ");
        System.out.println(bufferinfo.physicalBytes());
        
        System.out.print("bufferinfo.bytesused() ");
        System.out.println(bufferinfo.bytesused());   

        
        boolean output=true;
        int fd_out=0;
        if (output){
            System.out.println("open write device");
          	fd_out = v4l2_open("/dev/video3", 02);
            System.out.print(fd_out);
            
            System.out.println("Opened");

            
            v4l2_format format_out=new v4l2_format();
            format_out.type(V4L2_BUF_TYPE_VIDEO_OUTPUT);// = V4L2_BUF_TYPE_VIDEO_CAPTURE;
            v4l2_ioctl(fd_out, VIDIOC_G_FMT, format_out);
            v4l2_pix_format fmt_pix_out=new v4l2_pix_format(); //V4L2_PIX_FMT_MJPEG
            fmt_pix_out.pixelformat(V4L2_PIX_FMT_YUV422P);
            fmt_pix_out.width(640);
            fmt_pix_out.height(480);
            min = fmt_pix_out.width() * 2;
            if (fmt_pix_out.bytesperline() < min){
                fmt_pix_out.bytesperline(min);
            }
            min = fmt_pix_out.bytesperline() * fmt_pix_out.height();
            if (fmt_pix_out.sizeimage() < min){
                fmt_pix_out.sizeimage(min);
            }
            format_out.fmt_pix(fmt_pix_out);
            v4l2_ioctl(fd_out, VIDIOC_S_FMT, format_out);

        
        };
         
        
//         Pointer buffer_start = v4l2_mmap(
//             NULL.setNull(),
//             bufferinfo.length(),
//             1 | 2,
//             4,
//             fd,
//             bufferinfo.m_offset());
//             
        //NULL.capacity() ;
        //buffer_start.put(bufferv4l2_mmap);
       // System.out.println(buffer_start);
       // buffer_start.fill(bufferinfo.length()); 
//         System.out.print("bufferinfo.totalBytes() before");
//         System.out.println(bufferinfo.totalBytes());
//         //bufferinfo.fill(bufferinfo.length());
         //bufferinfo.memset(sp, 0, bufferinfo.length());
         //bufferinfo.put(sp);
//         System.out.print("bufferinfo.totalBytes() after");
//         System.out.println(bufferinfo.totalBytes());
        //buffer_start.fill(bufferinfo.length());


        
        
//         System.out.print("queue buffer: ");
//         System.out.println(v4l2_ioctl(fd, VIDIOC_QBUF, bufferinfo));
//         // Activate streaming
//         IntPointer type_i = new IntPointer(bufferinfo.type());
//                 System.out.println(type_i);
// 
//         int type =V4L2_BUF_TYPE_VIDEO_CAPTURE;
//         System.out.print("Start Streaming ");
//         System.out.println(v4l2_ioctl(fd, VIDIOC_STREAMON, 1));
//         
//         System.out.print("deque buffer ");
//         System.out.println(v4l2_ioctl(fd, VIDIOC_DQBUF, bufferinfo));
//         

        BytePointer bff =new BytePointer(bufferinfo.length());
        System.out.print("bff.totalBytes() ");
        System.out.println(bff.totalBytes());
        
        long res =0 ;
        res=v4l2_read(fd, bff, bufferinfo.length());
        if (output){
                System.out.println("write to device");
                 v4l2_write(fd_out, bff, bufferinfo.length());
         }
         
        for (int i = 0; i < 300; i++) {
            v4l2_read(fd, bff, bufferinfo.length());
            v4l2_write(fd_out, bff, bufferinfo.length());

         }

//          System.out.print("res ");
//         System.out.println(res);
// 
//          byte[] arr = new byte[bufferinfo.length()];
//          bff.get(arr);
//          System.out.println(arr);
// //         
//                             System.out.print("arr.length ");
//          System.out.println(arr.length);
// //         
//          try {
//              BufferedImage imag=ImageIO.read(new ByteArrayInputStream(arr));
//             System.out.print("imag ");
//             System.out.println(imag);
//             
//             ImageIO.write(imag, "jpg", new File("./","snap.jpg"));
//  
//          } catch (IOException e) {
//              throw new RuntimeException(e);
//          }
         

        flag=v4l2_close(fd);
        v4l2_close(fd_out);

        System.out.println(flag);

    }
}
