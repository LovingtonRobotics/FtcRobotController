package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.BarcodePositionDetector;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

@Autonomous(name="autonomousBlue2", group="Test")
public class autonomousBlue2 extends automethods {
    HardwarePushbot robot = new HardwarePushbot();// Use a Pushbot's hardware


    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
     //   robot.autoinit(hardwareMap);



       /* int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);
        //phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        webcam.setPipeline(pipeline);

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.
        webcam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
            }
        });
*/


        waitForStart();

////////////////////////////////////ROBOT  START////////////////////////////////////////////////////


        strafeRight(.5,120,6);
        imuHold(1);
        startturn(1,4);
        strafeRight(.5, -180,8);

        //  rpos = returnRingPosition(3);


////////////////////////////////
//
      /*  if(rpos == RingPipeline.RingPosition.NONE){
            encoderDrive(.55, 2, 5);



        }
        if(rpos == RingPipeline.RingPosition.ONE )

        {
            encoderDrive(.55, 10, 5);

        }
        if(rpos == RingPipeline.RingPosition.NONE)

        {
            encoderDrive(.55, 20, 5);



        }*/


    }}
