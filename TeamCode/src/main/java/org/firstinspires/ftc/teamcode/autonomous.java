package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name="autonomous", group="Test")
public class autonomous extends automethods {
    HardwarePushbot robot = new HardwarePushbot();// Use a Pushbot's hardware
    OpenCvWebcam webcam;
    BarcodeDeterminationPipeline pipeline;
    BarcodeDeterminationPipeline.barcodePosition barcodePos;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        robot.autoinit(hardwareMap);


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);
        pipeline = new BarcodeDeterminationPipeline();
        webcam.setPipeline(pipeline);
        webcam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        while(!isStarted( ) && !isStopRequested()){
            telemetry.addData("Analysis",pipeline.getAnalysis());
            telemetry.update();

            sleep(50);
        }

////////////////////////////////////ROBOT  START////////////////////////////////////////////////////

/*
        encoderDrive(.5, 80, 6);
        strafeRight(.5,-90,6);
        setLevel(2);*/

        barcodePos = BarcodeDeterminationPipeline.barcodePosition.CENTER;


////////////////////////////////
//
        if(barcodePos == BarcodeDeterminationPipeline.barcodePosition.LEFT){
            startturn(.1,1);


        }
        if(barcodePos == BarcodeDeterminationPipeline.barcodePosition.CENTER )

        {
            startturn(.1,1);

        }
        if(barcodePos == BarcodeDeterminationPipeline.barcodePosition.RIGHT){


            startturn(.1,1);





        }
    }
}
